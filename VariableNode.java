import java.util.HashMap;

public class VariableNode extends Node{

	public String keyword;
	public HashMap<String, Object> table;

	public VariableNode(String keyword, HashMap<String, Object> table){
		this.keyword = keyword;
		this.table = table;
	}

	public int getType(){
		return 0xf00f;
	}

	public Object eval(){
		Object result = table.get(keyword);
		if(result == null){
			System.out.println("Cannot find symbol: " + keyword);
			System.exit(0);
		}
		return result;
	}

	public void printAst(){
		System.out.println("VariableNode : " + keyword);
	}


}