public class ScalarNode extends Node{

	Double value;

	public ScalarNode(double value){
		this.value = value;
	}

	public int getType(){
		return 0x101010;
	}

	public Object eval(){
		return value;
	}

	public void printAst(){
		System.out.println("ScalarNode : " + value);
	}
}