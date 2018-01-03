
import java.util.List;
import java.util.ArrayList;

public class Lexer implements Constants{

	static int pos;
	static String str;

	public static void main(String[] args){
		String example = "abc := <0, 1, 0> . <14, 12, 15>;";
		List<Token> list = lexicalAnalysis(example);
		for(Token t : list){
			System.out.println(t.toString());
		}
	}

	public static List<Token> lexicalAnalysis(String input){
		str = input;
		pos = 0;

		ArrayList<Token> tokens = new ArrayList<>();

		while(pos < input.length()){
			char c = input.charAt(pos);

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
			else if(c == ':'){
				eat(':');
				eat('=');
				tokens.add(new Token(ASSIGN, ":="));
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
		return str.charAt(pos);
	}


	// maybe rewrite with stringbuilder
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

		System.out.println(raw);
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