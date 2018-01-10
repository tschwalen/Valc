
import java.util.List;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.LinkedList;

public class Valc {

	final static int BUFFER_SIZE = 1024;

	public static void main(String[] args){

		String program = "";

		if(args.length > 0){
			try{
				program = getStringFromFile(args[0]);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			//System.out.println(program);
			interpret(program);
		}

	}

	private static void interpret(String sourceCode){
		List<Token> tokens = Lexer.lexicalAnalysis(sourceCode);
		/*
		for(Token t : tokens){
			System.out.println(t.toString());
		}
		*/


		Node program = Parser.parseTokens(Lexer.lexicalAnalysis(sourceCode));

		//program.printAst();

		String output = program.eval().toString();
		System.out.println(output);
	}

	private static String getStringFromFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(path);
		InputStreamReader input = new InputStreamReader(stream, Charset.defaultCharset());

		Reader reader = new BufferedReader(input);
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[BUFFER_SIZE];
		int read;
		while((read = reader.read(buffer, 0, buffer.length)) > 0){
			builder.append(buffer, 0, read);
		}
		return builder.toString();
	}


}