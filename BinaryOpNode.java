
public class BinaryOpNode extends Node implements Constants{
	Node left;
	Node right;
	Token op;

	public BinaryOpNode(Node left, Node right, Token op){
		this.left = left;
		this.right = right;
		this.op = op;
	}

	public int getType(){
		return 0xfed77;
	}

	public Object eval(){
		Object leftVal = left.eval();
		Object rightVal = right.eval();

		// make sure both operands are indeed vectors, since variables
		// can store both vector and scalar values.
		if(leftVal instanceof Vector && rightVal instanceof Vector){
			return binOp((Vector) leftVal, (Vector)rightVal, op);
		}

		System.out.println("Runtime error: incorrect type for vector operation");
		System.exit(0);
		return null;
	}

	// it might be unnecesary to pass the arguments but I do it here for style's sake
	private Object binOp(Vector left, Vector right, Token op){
		switch(op.type){
			case PLUS: 
				return left.add(right);

			case MINUS:
				return left.subtract(right);

			case DOT: 
				return left.dotProduct(right);

			case CROSS: 
				return left.crossProduct(right);

			case PROJ: 
				return left.projOnto(right);

			case COMP: 
				return left.compOnto(right);


			default:
				System.out.println("Unknown operator: " + NAMES[op.type]);
				System.exit(0);
		}
		return null;
	}

	public void printAst(){
		System.out.println("BinaryOpNode{");
		System.out.print("\tLeft: ");
		left.printAst();
		System.out.print("\tRight: ");
		left.printAst();
		System.out.println("}");

	}

}