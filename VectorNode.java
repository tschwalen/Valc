
public class VectorNode extends Node {

	Vector value;

	public VectorNode(Vector value){
		this.value = value;
	}

	public int getType(){
		return 0x78705;
	}

	public Object eval(){
		return value;
	}

	public void printAst(){
		System.out.println("VectorNode : " + value.toString());
	}

}