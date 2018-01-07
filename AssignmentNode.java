
import java.util.HashMap;

public class AssignmentNode extends Node{

	public String keyword;
	public Node expr;
	public HashMap<String, Object> table;

	public AssignmentNode(String keyword, Node expr, HashMap<String, Object> table){
		this.keyword = keyword;
		this.expr = expr;
		this.table = table;
	}

	public int getType(){
		return 1;
	}

	public Object eval(){
		Object value = expr.eval();
		table.put(keyword, value);
		return value;
	}

	public void printAst(){
		System.out.println("Assignment {");
		System.out.println("\t" + keyword);
		expr.printAst();
		System.out.println("}");
	}

}