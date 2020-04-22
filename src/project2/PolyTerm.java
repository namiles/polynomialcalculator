package project2;

/*
 * 
 * Worked with James Rhinehart on this project.
 * Workload split evenely, both doing about 50% of the project. Collaborated on most of it via Discord.
 * 
 */

public class PolyTerm implements Comparable<PolyTerm> {
	protected Scalar coefficient;
	private int exponent;
	//String used to keep track of rational or real
	private String qR;
	
	@Override
	public int compareTo(PolyTerm pt) {
		if(exponent==pt.exponent) {
			return 0;
		} 
		else if (exponent>pt.exponent) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	PolyTerm() {
		exponent = 0;
		if(this.qR == "R") {
			coefficient = new RealScalar(0.0);
		}
		else {
			coefficient = new RationalScalar(0,1);
		}
	}
	
	/*PolyTerm(double num, int exp) {
		exponent = exp;
		coefficient = new Scalar(num);
	}*/
	
	public PolyTerm(String input, String r) {
		//if it's real
		if(r.compareTo("R") == 0) {
	        if (input.contains("^")) {
	            int f = input.indexOf('x');
	            if(f == 0) {
	            	double tempD = 1;
		            String sub = input.substring(0, (f));
		            sub = sub.trim();
		            coefficient = new RealScalar(tempD);
		            f = input.indexOf('^');
		            sub = input.substring((f+1), input.length());
		            sub = sub.trim();
		            int tempInt = Integer.parseInt(sub);
		            exponent = tempInt;
	            }
	            else {
		            String sub = input.substring(0, (f));
		            sub = sub.trim();
		            double tempD = Double.parseDouble(sub);
		            coefficient = new RealScalar(tempD);
		            f = input.indexOf('^');
		            sub = input.substring((f+1), input.length());
		            sub = sub.trim();
		            int tempInt = Integer.parseInt(sub);
		            exponent = tempInt;
	            }
	        }
	        else { //If there is no given exponent but still has variable x the exponent is 1
	            if (input.contains("x")) {
	                int f = input.indexOf('x');
	                if(f == 0) {
		                double tempDouble = 1;
		                coefficient = new RealScalar(tempDouble);
		                exponent = 1;
	                }
	                else {
		                String sub = input.substring(0, f);
		                sub = sub.trim();
		                double tempDouble = Double.parseDouble(sub);
		                coefficient = new RealScalar(tempDouble);
		                exponent = 1;
	                }
	            }
	            else { //If there is no variable x then the exponent is 0
	                double tempDouble = Double.parseDouble(input);
	                coefficient = new RealScalar(tempDouble);
	                exponent = 0;
	            }
	        }  
		} 
		else {
			int num;
			int denom;
	        if (input.contains("^")) {
	            int f = input.indexOf('x');
	            //if f = 0, then no coefficient was entered, set it to 1 because no coeff is the same as coeff = 1.
	            if(f == 0) {
	            	coefficient = new RationalScalar(1, 1);
	            	String sub = input.substring(0, (f));
		            sub = sub.trim();
		            f = input.indexOf('^');
		            sub = input.substring((f+1), input.length());
		            sub = sub.trim();
		            int tempInt = Integer.parseInt(sub);
		            exponent = tempInt;
	            }
	            else {
		            String sub = input.substring(0, (f));
		            sub = sub.trim();
		            if(sub.contains("/")) {
			            //split sub at /
		            	String[] parseFraction = sub.split("/");
			            num = Integer.parseInt(parseFraction[0]);
			            denom = Integer.parseInt(parseFraction[1]);
			            
		            }
		            else {
		            	num = Integer.parseInt(sub);
		            	denom = 1;
		            }
		            coefficient = new RationalScalar(num, denom);
		            f = input.indexOf('^');
		            sub = input.substring((f+1), input.length());
		            sub = sub.trim();
		            int tempInt = Integer.parseInt(sub);
		            exponent = tempInt;
	            }
	        }
	        else { //If there is no given exponent but still has variable x the exponent is 1
	            if (input.contains("x")) {
	                int f = input.indexOf('x');
	                if(f == 0) {
		            	coefficient = new RationalScalar(1, 1);
			            exponent = 1;
	                }
	                else {
		                String sub = input.substring(0, f);
		                sub = sub.trim();
		                if(sub.contains("/")) {
				            //split sub at /
			            	String[] parseFraction = sub.split("/");
				            num = Integer.parseInt(parseFraction[0]);
				            denom = Integer.parseInt(parseFraction[1]);
				            
			            }
			            else {
			            	num = Integer.parseInt(sub);
			            	denom = 1;
			            }
			            coefficient = new RationalScalar(num, denom);
		                exponent = 1;
	                }
	            }
	            else { //If there is no variable x then the exponent is 0
	            	if(input.contains("/")) {
			            //split sub at /
		            	String[] parseFraction = input.split("/");
			            num = Integer.parseInt(parseFraction[0]);
			            denom = Integer.parseInt(parseFraction[1]);
			            
		            }
		            else {
		            	num = Integer.parseInt(input);
		            	denom = 1;
		            }
	                coefficient = new RationalScalar(num, denom);
	                exponent = 0;
	            }
	        } 
		}
		qR = r;
    }
	
	
	
	/* Helper Methods */
	
	
	//Returns coefficient as a double type
	public double getCoefficient() {
		double result;
		result = coefficient.getVal();
		return result;
	}
	
	//Set the coefficient using a double value
	public void setCoefficient(double s) {
		coefficient.setVal(s);
	}
	
	public void setCoefficientScalar(Scalar s) {
		coefficient = s;
	}
	
	public int getExponent() {
		return exponent;
	}
	
	public void setExponent(int input) {
		exponent = input;
	}
	
	
	
	/* Required Functions for Assignment */
	
	public boolean canAdd(PolyTerm pt) {
		boolean areExponentsEqual = false;
		if(exponent == pt.getExponent()) {
			areExponentsEqual = true;
		}
		else {
			areExponentsEqual = false;
		}
		return areExponentsEqual;
	}
	
	public PolyTerm add(PolyTerm pt) {
		if(this.canAdd(pt)) {
			PolyTerm temp = new PolyTerm();
			temp.setCoefficientScalar(this.coefficient.add(pt.coefficient));
			//If they can add, they will have the same exponent so you can set the new polyterm exponent to either this or pt.
			temp.setExponent(this.getExponent());
			return temp;
		}
		else {
			return null;
		}
	}
	
	public PolyTerm mult(PolyTerm pt) {
		PolyTerm temp = new PolyTerm();
		temp.setCoefficientScalar(this.coefficient.mult(pt.coefficient));
		int exponentSum = this.exponent + pt.exponent;
		temp.setExponent(exponentSum);
		return temp;
	}
	
	public Scalar evaluate(Scalar scalar) {
		Scalar result = scalar.pow(exponent);
		result = result.mult(coefficient);
		return result;
	}
	
	//Takes the derivative of the polyterm. Multiplies exponent by coefficient and subtracts exponent by 1
	public PolyTerm derviative() {
		PolyTerm temp = new PolyTerm();
		Scalar newCoef = coefficient.mult(exponent);
		temp.setCoefficientScalar(newCoef);
		if(exponent == 0) {
			temp.setExponent(0); //set exponent to 0 if it is already zero so that it is not subtracted to -1
		}
		else
		{
			int newExp = exponent - 1;
			temp.setExponent(newExp);
		}
		return temp;
	}
	
	//Tests if two polyterms are equal (coefficients are equal and exponents are equal) Returns true if equal, false if not.
	boolean equals(PolyTerm pt) {
		boolean isEqual = false;
		if((this.coefficient.equals(pt.coefficient)) && (this.exponent == pt.exponent)) {
			isEqual = true;
		}
		return isEqual;
	}
	
} //End polyterm class

