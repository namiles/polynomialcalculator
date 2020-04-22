package project2;

/*
 * 
 * Worked with James Rhinehart on this project.
 * Workload split evenely, both doing about 50% of the project. Collaborated on most of it via Discord.
 * 
 */

public class RealScalar implements Scalar {
	
	private double value;
	
	public RealScalar() {
		value = 0.0;
	}
	
	public RealScalar(double n) {
		value = n;
	}
    
    public double getVal() {
        return value;
    }
    
    public void setVal(double n) {
    	value = n;
    }
    
    public Scalar add(Scalar s) {
    	double toAdd = s.getVal();
		Scalar temp = new RealScalar();
		//add toAdd to the value of the current scalar
		toAdd = toAdd+value;
		//set value of temp scalar to the sum of toAdd and value of current scalar
    	temp.setVal(toAdd);
    	return temp;
    }
    
    public Scalar mult(Scalar s) {
    	double toMult = s.getVal();
		Scalar temp = new RealScalar();
		//add toMult to the value of the current scalar
		toMult = toMult*value;
		//set value of temp scalar to the product of toMult and value of current scalar
    	temp.setVal(toMult);
    	return temp;
    }
    
    public Scalar mult(int s) {
    	double toMult = s;
		Scalar temp = new RealScalar();
		//add toMult to the value of the current scalar
		toMult = toMult*value;
		//set value of temp scalar to the product of toMult and value of current scalar
    	temp.setVal(toMult);
    	return temp;
    }
    
    public Scalar pow(int exp) {
    	Scalar temp = new RealScalar();
    	double val = value;
    	val = Math.pow(val, exp);
    	temp.setVal(val);
		return temp;
    }
    
    public Scalar neg() {
    	Scalar temp = new RealScalar();
    	double val = value*-1;
    	temp.setVal(val);
    	return temp;
    }
    
    public boolean equals(Scalar s) {
    	boolean isTrue = false;
    	if(value == s.getVal()) {
    		isTrue = true;
    	}
    	else {
    		isTrue = false;
    	}
    	return isTrue;
    }
    
	public boolean equals(int s) {
    	boolean isTrue = false;
    	if(value == s) {
    		isTrue = true;
    	}
    	else {
    		isTrue = false;
    	}
    	return isTrue;
    }
	
	public String giveUsString() {
		String temp = "";
		temp += value;
		return temp;
	}
	
	public int getNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDenom() {
		// TODO Auto-generated method stub
		return 0;
	}
}
