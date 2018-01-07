
public class MagNode extends Node implements Constants {
	Node value;

	public MagNode(Node value){
		this.value = value;
	}

	public int getType(){
		return 0x13E70;
	}


	// returns the magnitude of a vector and the absolute value of a scalar
	public Object eval(){
		Object result = value.eval();
		if(result instanceof Vector){
			return ((Vector) result).magnitude();
		}
		return Math.abs((Double) result);
	}

	public void printAst(){
		System.out.println("MagNode{");
		value.printAst();
		System.out.println("}");

	}

}