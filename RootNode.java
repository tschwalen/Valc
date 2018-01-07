import java.util.LinkedList;
import java.util.List;

public class RootNode extends Node {

	List<Node> statements;

	// CHANGE THIS
	public int getType(){
		return 0;
	}

	public RootNode(){
		statements = new LinkedList<>();
	}

	public Object eval(){
		Object result  = null;
		for(Node node : statements){
			result = node.eval();
		}
		return result;
	}

	public void printAst(){
		System.out.println("RootNode {");
		for(Node node : statements){
			System.out.print("\t");
			node.printAst();
		}
		System.out.println("}");
	}

}