import java.util.List;
import java.util.ArrayList;

public class Vector{

	private List<Double> cont;

	public Vector(List<Double> cont){
		this.cont = cont;
	}

	public int size(){
		return cont.size();
	}

	public double get(int index){
		return cont.get(index);
	}

	public Vector scale(double scalar){

		List<Double> newCont = new ArrayList<>(size());

		int index = 0;
		for(Double component : cont){
			newCont.add(index, component * scalar);
			index++;
		}
		return new Vector(newCont);
	}

	public double magnitude(){
		double sqSum = 0;

		for(Double component : cont){
			sqSum += Math.pow(component, 2);
		}

		return Math.sqrt(sqSum);
	}

	public Vector add(Vector other){
		if(size() != other.size()){
			throw new IllegalArgumentException("Calling vector and argument vector must be of same dimensions.");
		}

		ArrayList<Double> newCont = new ArrayList<>(size());

		for(int index = 0; index < size(); index++){
			newCont.add(index, get(index) + other.get(index));
		}		

		return new Vector(newCont);
	}

	public Vector subtract(Vector other){
		if(size() != other.size()){
			throw new IllegalArgumentException("Calling vector and argument vector must be of same dimensions.");
		}

		ArrayList<Double> newCont = new ArrayList<>(size());

		for(int index = 0; index < size(); index++){
			newCont.add(index, get(index) - other.get(index));
		}		

		return new Vector(newCont);
	}


	public double dotProduct(Vector other){
		if(size() != other.size()){
			throw new IllegalArgumentException("Calling vector and argument vector must be of same dimensions.");
		}

		double sum = 0;
		for(int index = 0; index < other.size(); index++){
			sum += get(index) * other.get(index);
		}

		return sum;
	}

	/*
	u = this 
	v = other
	u x v = this.crossProduct(other);
	*/
	public Vector crossProduct(Vector other){
		if(size() != 3 || other.size() != 3){
			throw new IllegalArgumentException("Cross product is only defined for three dimensional vectors.");
		}
		ArrayList<Double> newCont = new ArrayList<>(3);

		newCont.add(get(1) * other.get(2) - get(2) * other.get(1));
		newCont.add(get(2) * other.get(0) - get(0) * other.get(2));
		newCont.add(get(0) * other.get(1) - get(1) * other.get(0));

		return new Vector(newCont);
	}

	/*
	u = this, v = other
	this.compOnto(other) = comp(v,u)
	*/
	public double compOnto(Vector other){
		return dotProduct(other) / Math.abs(other.magnitude());
	}


	/*
	u = this, v = other
	this.projOnto(other) = proj(v,u)
	*/
	public Vector projOnto(Vector other){
		double factor = dotProduct(other) / Math.pow(other.magnitude(), 2);

		ArrayList<Double> newCont = new ArrayList<>(other.size());

		for(Double d : other.cont){
			newCont.add(d * factor);
		}

		return new Vector(newCont);
	}

	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append('<');
		for(int i = 0; i < cont.size() - 1; i++){
			str.append(cont.get(i));
			str.append(", ");
		}
		str.append(cont.get(cont.size() - 1));
		str.append('>');
		return str.toString();
	}

}