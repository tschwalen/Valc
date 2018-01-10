
// this is the worst part of the program. I hate how java enums work and I hate static imports, so in order
// to get things working I've resorted to the worst version of the constant interface antipattern. I don't
// know if this makes me a real programmer or a quiche-eater.

public interface Constants {
	final static int KEYWORD = 0;
	final static int VECTOR = 1;
	final static int SCALAR = 2;
	final static int PLUS = 3;
	final static int MINUS = 4;
	final static int CROSS = 5;
	final static int DOT = 6;
	final static int PROJ = 7;
	final static int COMP = 8;
	final static int ASSIGN = 9;
	final static int SEMICOLON = 10;
	final static int PIPE = 11;
	final static int EOF = 12;
	final static int ECHO = 13;

	
	final static String[] NAMES = {
		"KEYWORD",
		"VECTOR",
		"SCALAR",
		"PLUS",
		"MINUS",
		"CROSS",
		"DOT",
		"PROJ",
		"COMP",
		"ASSIGN",
		"SEMICOLON",
		"PIPE",
		"EOF",
		"ECHO"
	};	

}