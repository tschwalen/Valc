
import java.util.List;
import java.util.ArrayList;


/*
	Grammar:
	
	NOTE: Many of these rules can be expanded to take an expression instead of terminal values

	<root> ::= <statement>*
	<statement> ::= (<assignment> | <expression> | <echo>) ";"

	<echo> ::= "echo" <expression>

	<assignment> ::= KEYWORD ":=" <expression>
	<expression> ::= <unary-op> | <binary-exp> | <mag-exp> | <terminal>
	<terminal> ::= VECTOR | SCALAR | KEYWORD
	<unary-exp> ::= <negation> | <scalar-mult>

	<negation> ::= "-" <terminal>

	<scalar-mult> ::= (SCALAR | KEYWORD) (VECTOR | KEYWORD)

	<binary-exp> ::= (VECTOR | KEYWORD) <binary-op> (VECTOR | KEYWORD)
	<binary-op> ::= "+" | "-" | "x" | "." | "proj" | "comp" 
	<mag-exp> ::= "|" <terminal> "|"
	
	TODO:
		add unary minus
		extend the grammar to be more general
		add rules for output (echo), branching, and looping


*/

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class Parser implements Constants{

	//private Node rootNode;
	private static List<Token> tokenList;
	private static int pos;
	private static HashMap<String, Object> symbolTable;

	public static Node parseTokens(List<Token> tokens){
		symbolTable = new HashMap<>();
		tokenList = tokens;
		pos = 0;

		Node rootNode = root();

		return rootNode;

	}

	// TOKEN READING/HANDLING METHODS

	private static boolean hasNextToken(){
		return pos < tokenList.size();
	}

	private static void eat(int t){
		if(!hasNextToken()){
			errorMsg("Error: Reached end of file while parsing.");
		}
		if(t != tokenList.get(pos).type){
			parseError("eat()",t, tokenList.get(pos).type);
		}
		pos++;
	}

	private static Token currentToken(){
		return lookAhead(0);
	}

	private static Token lookAhead(){
		return lookAhead(1);
	}

	private static Token lookAhead(int offset){
		if(offset + pos < tokenList.size()){
			return tokenList.get(pos + offset);
		}
		return new Token(EOF, "EOF");
	}


	//////////////////////////////////////////////////////
	// GRAMMAR PROCESSING METHODS THAT RETURN AST NODES //
	//////////////////////////////////////////////////////

	private static Node root(){
		RootNode rootNode = new RootNode();

		while(hasNextToken()){
			rootNode.statements.add(statement());
		}
		return rootNode;
	}

	private static Node statement(){
		Token curr = currentToken();
		Node result = null;
		if(curr.hasType(KEYWORD) && lookAhead().hasType(ASSIGN)){
			result = assignment();
		}
		else if(curr.hasType(ECHO)){
			result = echoStatement();
		}
		else{
			result = expression();
		}
		eat(SEMICOLON);
		return result;
	}

	private static Node echoStatement(){
		eat(ECHO);
		Node argument = expression();
		return new EchoNode(argument);
	}

	private static Node assignment(){
		String keyword = currentToken().contents.toString();
		eat(KEYWORD);
		eat(ASSIGN);
		Node expr = expression();
		return new AssignmentNode(keyword, expr, symbolTable);
	}

	private static Node expression(){
		Token curr = currentToken();
		if(curr.hasType(PIPE)){
			return magnitudeExpression();
		}
		if(curr.hasType(MINUS)){
			return unaryMinus();
		}
		else if(curr.hasType(SCALAR) || curr.hasType(VECTOR) || curr.hasType(KEYWORD)){


			
			if(lookAhead().hasType(VECTOR) || lookAhead().hasType(KEYWORD)){
				// our only unary operation has to do with vectors, so if our next token is a vector we know what to do
				if(curr.hasType(VECTOR)){
					parseError("expression(), unary", VECTOR);
				}
				Node scale = terminal();
				// the previous token is eaten by terminal(), and we've ensured that the next token is a vector or keyword
				
				Node vector = terminal();
				
				return new ScaleOpNode(scale, vector);

			}
			else if(lookAhead().hasType(SEMICOLON)){
				// a terminal value is simply followed by a semicolon
				return terminal();
			}
			else{
				// the only other grammatically correct pattern left is a binary operation
				if(curr.hasType(SCALAR)) parseError("expression(), binary", SCALAR);

				Node left = terminal();
				if(!isBinaryOperator(currentToken())) errorMsg("Expected binary operator");
				Token op = currentToken();
				pos++;
				Node right = terminal();
				return new BinaryOpNode(left, right, op);
			}
		}
		parseError("expression()",curr.type);
		return null;
	}


	// read a unary minus expression - constant folding can be done here too
	private static Node unaryMinus(){
		eat(MINUS);
		Node operand = terminal();
		return new NegateNode(operand);
	}

	// consider constant folding
	private static Node terminal(){
		Token curr = currentToken();
		if(curr.hasType(SCALAR)){
			eat(SCALAR);
			return new ScalarNode((Double)curr.value());
		}
		else if(curr.hasType(VECTOR)){
			eat(VECTOR);
			return new VectorNode((Vector) curr.value());
		}
		else if(curr.hasType(KEYWORD)){
			eat(KEYWORD);
			return new VariableNode((String) curr.value(), symbolTable);
		}
		return null;
	}

	// we can definitely do some constant folding here
	private static Node magnitudeExpression(){
		eat(PIPE);
		Node value = terminal(); // terminal eats, so pos has been incremented
		eat(PIPE);
		return new MagNode(value);
	}

	// utility methods

	private static boolean isBinaryOperator(Token t){
		return PLUS <= t.type && t.type <= COMP;
	}

	private static void parseError(String where, int expected, int encountered){
		System.out.println("At " + where);
		System.out.println("Parse Error: " + NAMES[expected] + " expected, but " + NAMES[encountered] + " encountered.");
		System.exit(0);
	}

	private static void parseError(String where, int unexpected){
		System.out.println("At " + where);
		System.out.println("Parse Error, Unexpected Token: " + NAMES[unexpected]);
		System.exit(0);
	}

	private static void errorMsg(String message){
		System.out.println(message);
		System.exit(0);
	}

}