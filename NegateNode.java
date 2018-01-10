
public class NegateNode extends Node{

	Node operand;

	public NegateNode(Node operand){
		this.operand = operand;
	}

	public Object eval(){
		Object temp = operand.eval();
		if(temp instanceof Double){
			return -((Double) temp);
		}
		else if(temp instanceof Vector){
			return ((Vector) temp).scale(-1.0);
		}

		throw new IllegalStateException("Something went wrong in Unary Minus.");
	}

	public int getType(){
		return -1;
	}

	public void printAst(){
		System.out.println("Unary Negation: " + operand.toString());
	}

}