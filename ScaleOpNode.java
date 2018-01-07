
public class ScaleOpNode extends Node {

	Node scalarVal;
	Node vectorVal;

	public ScaleOpNode(Node scalarVal, Node vectorVal){
		this.scalarVal = scalarVal;
		this.vectorVal = vectorVal;
	}

	public int getType(){
		return 0x12345;
	}

	public Object eval(){
		double scalar = (Double) scalarVal.eval();
		Vector vector = (Vector) vectorVal.eval();
		return vector.scale(scalar);
	}

	public void printAst(){
		System.out.println("ScaleOpNode{");
		System.out.print("\t");
		scalarVal.printAst();
		System.out.print("\t");
		vectorVal.printAst();
		System.out.println("}");

	}
}