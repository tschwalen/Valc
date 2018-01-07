
import java.util.List;

public class Valc {

	public static void main(String[] args){

		String program = "a := 4; \nb := a<1,2,3>;";
		System.out.println(program);
		interpret(program);
	}

	private static void interpret(String sourceCode){
		List<Token> tokens = Lexer.lexicalAnalysis(sourceCode);
		for(Token t : tokens){
			System.out.println(t.toString());
		}


		Node program = Parser.parseTokens(Lexer.lexicalAnalysis(sourceCode));

		//program.printAst();

		String output = program.eval().toString();
		System.out.println(output);
	}


}