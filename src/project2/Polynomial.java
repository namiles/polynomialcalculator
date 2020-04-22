package project2;
import java.util.ArrayList;
import java.util.Collections;

/*
 * 
 * Worked with James Rhinehart on this project.
 * Workload split evenely, both doing about 50% of the project. Collaborated on most of it via Discord.
 * 
 */

public class Polynomial {
	private String polyNum;
	private ArrayList<PolyTerm> terms;
	private String qR;
	
	//default constructor
	public Polynomial() {
		polyNum = null;
		terms = new ArrayList<PolyTerm>();
	}
	
	//construct a polynomial with a given string, and create an array of polyterms with it
	public Polynomial(String s, String r) {
		qR = r;
		terms = new ArrayList<PolyTerm>();
		s = s.replaceAll("-", "+!");
		polyNum = s.replaceAll("\\s", "");
		String[] parse = polyNum.split("[\\+]");
		for(int i = 0; i<parse.length;i++) {
			parse[i] = parse[i].replaceAll("!", "-");
			
			PolyTerm temp = new PolyTerm(parse[i], r);
			terms.add(temp);
		}
	}
	
	private void absorb(PolyTerm pt) {
		PolyTerm ptToAdd = new PolyTerm();
		boolean add = false;
		for(int i=0; i<this.terms.size(); i++) {
			if(this.terms.get(i).canAdd(pt)) {
				ptToAdd = this.terms.get(i).add(pt);
				this.terms.set(i, ptToAdd);
				add = true;
			}
		}
		if(add == false) {
			this.terms.add(pt);
		}
	}
	
	public Polynomial add(Polynomial poly) {
		Polynomial temp = new Polynomial();
		for(int i=0; i<poly.terms.size(); i++) {
			this.absorb(poly.terms.get(i));
		}
		//add all the terms in this to the temp polyterm to return
		temp.terms.addAll(terms);
		return temp;
	}
	
	public Polynomial mult(Polynomial poly) {
		Polynomial temp = new Polynomial();
		PolyTerm ptMult = new PolyTerm();
		int polySize = poly.terms.size();
		int thisSize = this.terms.size();
		
		for(int i=0; i<thisSize; i++) {
			for(int j=0; j<polySize; j++){
				ptMult = this.terms.get(i).mult(poly.terms.get(j));
				temp.terms.add(ptMult);
			}
		}
		
		//sort the list before combining polyterms with same exponents
		Collections.sort(temp.terms);
		
		//compare polyterms next to each other to see if they can add, add them if canAdd == true
		for(int i=0; i<temp.terms.size()-1; i++) {
			if(temp.terms.get(i).canAdd(temp.terms.get(i+1))) {
				temp.terms.set(i, temp.terms.get(i).add(temp.terms.get(i+1)));
				temp.terms.remove(i+1);
				//Check again to in case there are more than two polyerms with the same exponent
				if(temp.terms.get(i).canAdd(temp.terms.get(i+1))) {
					temp.terms.set(i, temp.terms.get(i).add(temp.terms.get(i+1)));
					temp.terms.remove(i+1);
				}
			}
		}
		
		return temp;
	}
	
	public Scalar evaluate(Scalar scalar) {
		if(qR.compareTo("R") == 0) {
			Scalar total = new RealScalar();
			for(int i = 0; i<terms.size(); i++) {
				total = total.add(terms.get(i).evaluate(scalar));
			}
			return total;
		}
		else {
			Scalar total = new RationalScalar();
			for(int i = 0; i<terms.size(); i++) {
				total = total.add(terms.get(i).evaluate(scalar));
			}
			return total;
		}
	}
	
	public Polynomial derivative() {
		Polynomial temp = new Polynomial();
		for(int i = 0; i<terms.size(); i++) {
			temp.terms.add(terms.get(i).derviative());
		}
		return temp;
	}
	
	public String toString() {
		//Remove coefficients of zero, added so that 0s in results of derviatives do not display
		//We remove them at the beginning so that they are not sorted if they are going to be removed anyway
		for(int h = 0; h<this.terms.size(); h++) {
			if(this.terms.get(h).coefficient.equals(0)) {
				this.terms.remove(h);
			}
		}
		
		//sort in order of increasing exponent
		Collections.sort(this.terms);
		
		int sizeOfList = this.terms.size();
		String polynomial = "";
		for(int i = 0; i<sizeOfList-1; i++ ) {
			PolyTerm temp = this.terms.get(i);
			if(temp.getExponent()==0) {
				polynomial += temp.coefficient.giveUsString() + "+";
			} 
			else if (temp.getExponent()==1) {
				polynomial += temp.coefficient.giveUsString() + "x"+"+";
			}
			else {
				polynomial += temp.coefficient.giveUsString() + "x" + "^" + temp.getExponent()+ "+";
			}
		}
		
		//Append the last item to the polynomial - We do this to avoid adding a + or ^ to the end of the polynomial
		PolyTerm temp=this.terms.get(sizeOfList-1);
		if(temp.getExponent()==0) {
			polynomial += temp.coefficient.giveUsString();
		} 
		else {
			polynomial += temp.coefficient.giveUsString()+"x"+"^"+temp.getExponent();
		}
		return polynomial;
	}
	
	//Compares the polyterms arrays of two polynomials to see if they are equal. Only true if arrays have the same polyterms at the same index
	public boolean equals(Polynomial poly) {
		boolean isEqual = false;
		for(int i = 0; i<terms.size(); i++) {
			for(int f = 0; f<poly.terms.size(); f++) {
				if(terms.get(i).equals(poly.terms.get(f))) {
					isEqual = true;
				}
				else {
					isEqual = false;
				}
			}
		}
		return isEqual;
	}
}
