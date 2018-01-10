
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
		Object scalar = scalarVal.eval();
		Object vector = vectorVal.eval();

		if(scalar instanceof Double && vector instanceof Vector){
			return ((Vector) vector).scale((Double) scalar);
		}

		throw new IllegalStateException("Undefined Behavior for adjacent terminals.");
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