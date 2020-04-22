package project2;

/*
 * 
 * Worked with James Rhinehart on this project.
 * Workload split evenely, both doing about 50% of the project. Collaborated on most of it via Discord.
 * 
 */

public class RationalScalar implements Scalar {

	private int numerator;
	private int denominator;
	
	public RationalScalar() {
		numerator = 0;
		denominator = 1;
	}
	
	public RationalScalar(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public int getNum() {
		return numerator;
	}
	
	public void setNum(int numerator) {
		this.numerator = numerator;
	}
	
	public int getDenom() {
		return denominator;
	}
	
	public void setDenom(int denominator) {
		this.denominator = denominator;
	}

	public Scalar add(Scalar s) {
		RationalScalar temp = new RationalScalar();
		//  x/y + a/b = (xb+ya)/yb
		int num = ((this.getNum() * s.getDenom()) + (this.getDenom() * s.getNum()));
		int denom = (this.getDenom() * s.getDenom());
		temp.setNum(num);
		temp.setDenom(denom);		
		return temp;
	}

	public Scalar mult(Scalar s) {
		RationalScalar temp = new RationalScalar();
		int num = (this.getNum() * s.getNum());
		int denom = (this.getDenom() * s.getDenom());
		temp.setNum(num);
		temp.setDenom(denom);	
		return temp;
	}
	
	public Scalar mult(int s) {
		RationalScalar temp = new RationalScalar();
		int num = (this.getNum() * s);
		int denom = this.getDenom();
		temp.setNum(num);
		temp.setDenom(denom);
		return temp;
	}

	public Scalar pow(int exp) {
    	RationalScalar temp = new RationalScalar();
    	int numVal = this.getNum();
    	int denomVal = this.getDenom();
    	numVal = (int) Math.pow(numVal, exp);
    	denomVal = (int) Math.pow(denomVal, exp);
    	temp.setNum(numVal);
    	temp.setDenom(denomVal);
    	return temp;
	}

	public Scalar neg() {
		RationalScalar temp = new RationalScalar();
		int numVal = this.getNum()*-1;
		temp.setNum(numVal);
		temp.setDenom(this.denominator);
		return temp;
	}

	public boolean equals(Scalar s) {
		boolean isTrue = false;
		if((this.getNum() == s.getNum()) && (this.getDenom() == s.getDenom())) {
			isTrue = true;
		}
		return isTrue;
	}

	public boolean equals(int s) {
    	boolean isTrue = false;
    	if(numerator == s) {
    		isTrue = true;
    	}
    	else {
    		isTrue = false;
    	}
    	return isTrue;
    }
	
	public void reduce(int num, int denom) {
		int n;
		n = findGCD(num,denom);
		numerator = num/n;
		denominator = denom/n;
	}
	
	public int findGCD(int x, int y) {
		if(y == 0) {
			return x;
		}
		return findGCD(y, x%y);
	}

	public String giveUsString() {
		String temp = "";
		//Reduce fraction first
		reduce(numerator, denominator);
		if(denominator == 1) {
			temp += numerator;
		}
		else {
			if(numerator == denominator) {
				temp += 1;
			}
			else {
				temp = numerator + "/" + denominator; 
			}
		}
		return temp;
	}
	
	public double getVal() {
		return 0;
	}

	public void setVal(double n) {
		
	}
}
