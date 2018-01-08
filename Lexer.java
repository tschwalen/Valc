
import java.util.List;
import java.util.ArrayList;

// TODO: add negative number OR unary minus support

// Performs lexical analysis

public class Lexer implements Constants{

	static int pos;
	static String str;

	public static void main(String[] args){
		String example = "a := <5, 12, 3>;\nb := 4.5<3, 3, 6>;\na proj b;\n";
		System.out.println(example);
		List<Token> list = lexicalAnalysis(example);
		for(Token t : list){
			System.out.println(t.toString());
		}
	}

	// main loop method
	public static List<Token> lexicalAnalysis(String input){
		str = input;
		pos = 0;

		ArrayList<Token> tokens = new ArrayList<>();

		while(pos < input.length()){
			char c = input.charAt(pos);

			// skip all whitespace
			if(Character.isWhitespace(c)){
				pos++;
			}
			else if(isLetter(c)){
		
				String string = readKeyword();
				int type = KEYWORD;

				switch(string){
					case "x": type = CROSS; break;
					case "proj": type = PROJ; break;
					case "comp": type = COMP; break;
				}

				tokens.add(new Token(type, string));	
				
			}
			else if(isNumber(c)){
				tokens.add(new Token(SCALAR, readNumber()));
			}
			else if(c == '<'){
				tokens.add(new Token(VECTOR, readVector()));
			}
			else if(c == '+'){
				eat('+');
				tokens.add(new Token(PLUS, "+"));
			}
			else if(c == '-'){
				eat('-');
				tokens.add(new Token(MINUS, "-"));
			}
			else if(c == '.'){
				eat('.');
				tokens.add(new Token(DOT, "."));
			}
			else if(c == ';'){
				eat(';');
				tokens.add(new Token(SEMICOLON, ";"));
			}
			else if(c == '|'){
				eat('|');
				tokens.add(new Token(PIPE, "|"));
			}
			else if(c == ':'){
				eat(':');
				eat('=');
				tokens.add(new Token(ASSIGN, ":="));
			}
			else if(c == '$'){
				readComment();
			}
		}
		return tokens;
	}

	private static void eat(char c){
		if(str.charAt(pos) != c){
			throw new IllegalStateException("Expected " + c + ", encountered " + str.charAt(pos));
		}
		pos++;
	}

	private static char currentChar(){
		if(pos < str.length()){
			return str.charAt(pos);
		}
		return '\0';		
	}

	// not tested yet
	private static void readComment(){
		eat('$');
		if(currentChar() == '-'){
			eat('-');
			while(currentChar() != '-' && str.charAt(pos + 1) != '$'){
				pos++;
			}
			eat('-');
			// last $ gets "eaten" by the main loop increment
			
		}
		else{
			while(currentChar() != '\n'){
				pos++;	
			}
		}
		
	}


	// maybe rewrite with stringbuilder, need to modify code to accept 
	// negative number literals
	private static Double readNumber(){
		String raw = "";
		
		while(isNumber(currentChar())){
			raw = raw + currentChar();
			pos++;
		}

		if(currentChar() == '.'){
			eat('.');
			raw = raw + '.';
			while(isNumber(currentChar())){
				raw = raw + currentChar();
				pos++;
			}
			return Double.parseDouble(raw);
		}

		return (double)Integer.parseInt(raw);
	}

	
	private static String readKeyword(){
		StringBuilder keyword = new StringBuilder();

		while(isLetter(currentChar())){
			keyword.append(currentChar());
			pos++;
		}
		return keyword.toString();
	}

	private static void eatWhitespace(){
		while(pos < str.length() && Character.isWhitespace(currentChar())){
			pos++;
		}
	}

	private static Vector readVector(){
		ArrayList<Double> cont = new ArrayList<>();
		eat('<');
		while(currentChar() != '>'){

			eatWhitespace();
			cont.add(readNumber());
			eatWhitespace();

			if(currentChar() == ','){
				eat(',');
			}
		}
		eat('>');

		return new Vector(cont);
	}

	private static boolean isLetter(char c){
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
	}

	private static boolean isNumber(char c){
		return '0' <= c && c <= '9';
	}


}