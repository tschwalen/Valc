
public class EchoNode extends Node {

	Node arg;

	public EchoNode(Node argument){
		arg = argument;
	}

	public Object eval(){
		System.out.println(arg.eval().toString());
		return "";
	}

	public void printAst(){
		System.out.println("ECHONODE--");
	}

	public int getType(){
		return 0x7fffbf;
	}
}