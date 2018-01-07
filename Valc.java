
import java.util.List;

public class Valc {

	public static void main(String[] args){

		String program = "a := <3,4,3>; \nb := <2,0,6>; \nc := b - a;";
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