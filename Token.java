

public class Token implements Constants{
	int type;
	Object contents;

	public Token(int type, Object contents){
		this.type = type;
		this.contents = contents;
		
	}

	public String toString(){
		return NAMES[type] + ", " + contents.toString();
	}
}