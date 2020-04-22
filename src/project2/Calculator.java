package project2;
import java.util.Scanner;

/*
 * 
 * Worked with James Rhinehart on this project.
 * Workload split evenely, both doing about 50% of the project. Collaborated on most of it via Discord.
 * 
 */

public class Calculator {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Scanner polyInput = new Scanner(System.in);
		Scanner rationalOrReal = new Scanner(System.in);
		
		int selection = 0; //set initial menu selection to 0
        boolean menuOpen = true;
        String rationalReal;

        while(menuOpen) {
        	System.out.println("Please select an operation:");
            System.out.println("1. Addition");
            System.out.println("2. Multiplication");
            System.out.println("3. Evaluation");
            System.out.println("4. Derivative");
            System.out.println("5. Exit");
            System.out.println(); //spacing
            selection = input.nextInt();
            
            System.out.println(); //spacing

            switch(selection) {
                case 1:
                	System.out.println("Please select the scalar field Rational (Q) or Real (R)");
                	rationalReal = rationalOrReal.nextLine();
                	System.out.println(); //spacing
                	System.out.println("Please enter the first polynomial");
                	String polynomialInput = polyInput.nextLine();
                	Polynomial firstPoly = new Polynomial(polynomialInput, rationalReal);
                	System.out.println(); //spacing
                	System.out.println("Please enter the second polynomial");
                	polynomialInput = polyInput.nextLine();
                	Polynomial secondPoly = new Polynomial(polynomialInput, rationalReal);
                	System.out.println(); //spacing
                	Polynomial sumPoly = new Polynomial();
                	sumPoly = firstPoly.add(secondPoly);
                	System.out.println("The Solution is:");
                	System.out.println(sumPoly.toString());
            		System.out.println(); //spacing
                    break;
                    
                case 2:
                   	System.out.println("Please select the scalar field Rational (Q) or Real (R)");
                	rationalReal = rationalOrReal.nextLine();
                	System.out.println(); //spacing
                	System.out.println("Please enter the first polynomial");
                	String poly1 = polyInput.nextLine();
                	Polynomial polyOnePolynomial = new Polynomial(poly1, rationalReal);
                	System.out.println(); //spacing
                	System.out.println("Please enter the second polynomial");
                	String poly2 = polyInput.nextLine();
                	Polynomial polyTwoPolynomial = new Polynomial(poly2, rationalReal);
                	Polynomial productPoly = new Polynomial();
                	productPoly = polyOnePolynomial.mult(polyTwoPolynomial);
                	System.out.println(); //spacing
                	System.out.println("The Solution is: ");
                	System.out.println(productPoly.toString());
                	System.out.println(); //spacing
                    break;
                    
                case 3:
                   	System.out.println("Please select the scalar field Rational (Q) or Real (R)");
                	rationalReal = rationalOrReal.nextLine();
                	System.out.println(); //spacing
                	System.out.println("Please enter a polynomial: ");
                	String poly = polyInput.nextLine();
                	Polynomial polynomial = new Polynomial(poly, rationalReal);
                	System.out.println(); //spacing
                	System.out.println("Enter a value for x: ");
                	int evalInput = polyInput.nextInt();
                	if(rationalReal.compareTo("R") == 0) {
                		Scalar x = new RealScalar();
                		x.setVal(evalInput);
                		Scalar evalScalar = polynomial.evaluate(x);
                		System.out.println(); //spacing
                    	System.out.println("The evaluation is:");
                    	System.out.println(evalScalar.getVal());
                    	System.out.println(); //spacing
                	}
                	else {
                		Scalar x = new RationalScalar(evalInput, 1);
                		Scalar evalScalar = polynomial.evaluate(x);
                		System.out.println(); //spacing
                    	System.out.println("The evaluation is:");
                    	System.out.println(evalScalar.giveUsString());
                    	System.out.println(); //spacing
                	}
                    break;
                    
                case 4:
                   	System.out.println("Please select the scalar field Rational (Q) or Real (R)");
                	rationalReal = rationalOrReal.nextLine();
                	System.out.println(); //spacing
                	Scanner derivInput = new Scanner(System.in);
                	System.out.println("Please enter a polynomial: ");
                	String derivPoly = derivInput.nextLine();
                	Polynomial derivPolynomial = new Polynomial(derivPoly, rationalReal);
                	Polynomial derivResult = new Polynomial();
                	derivResult = derivPolynomial.derivative();
                	System.out.println(); //spacing
                	System.out.println("The derivative of the polynomial is: ");
                	System.out.println(derivResult.toString());
                	System.out.println(); //spacing
                    break;
                    
                case 5:
                	System.out.println("Exiting program..");
                    menuOpen = false;
                    break;
                
                default:
                	System.out.println("Invalid input. Try again.");
                	System.out.println(); //spacing
             } //end switch
        } //end while
        
        input.close();
        polyInput.close();
        rationalOrReal.close();
        
	} //end main
} //end calculator class
