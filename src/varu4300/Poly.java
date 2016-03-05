package varu4300;

import java.util.StringTokenizer;


public class Poly {
	
	private int deg = 0; // for the degree
	private Rational [] coeffs; // for the array of coefficients
	
	/**
	 * constructor Poly defines a poly object given a coeff array
	 * 
	 * @param degree - coeff array
	 * 
	 */
	
	public Poly(int degree)
	{
		this.deg = degree;
		this.coeffs = new Rational[degree + 1];
		for (int i = 0; i <= this.deg; i++) {
		    this.coeffs[i] = new Rational(0, 1);
		}
	}
	
	public Poly(Rational[] a)
	{
		this.deg = a.length - 1;
		this.coeffs = a;
	}
	
	/**
	 * constructor Poly defines a poly object given a polynomial string
	 * 
	 * @param str
	 * 
	 */
	public Poly(String str)
	{
			String var = InputView.getVar();
			String delimiters = var + "+-*()/";
			Stack<Poly> operandStack = new Stack<Poly>();
			Stack<String> operatorStack = new Stack<String>();
			StringTokenizer input = new StringTokenizer(str, delimiters, true);
			String token = null;
			
		
			if (str.startsWith("-")) {
			    // Token must look like a negative number.
			    try {
				// Read the first operand as a negative number.
				token = input.nextToken() + input.nextToken();
				Poly p = new Poly(0);
				int coefficient = Integer.parseInt(token);
				p.setCoefficient(0, new Rational(coefficient,1));
				operandStack.push(p);
			    } catch (NumberFormatException e) {
			    	NumericalView.setError("Token is not a valid operator, variable, or operand.");
			    	InputView.setEr(true);
			    }
			}

			// Process the rest of the tokens.
			while (input.hasMoreTokens()) {
			    token = input.nextToken();

			    if (token.equals(var)) {
				// Create a new polynomial with a single x term.
				Poly p = new Poly(1);
				p.setCoefficient(1, new Rational(1,1));
				operandStack.push(p);
			    } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("(") || token.equals(")") || token.equals("/")) {
				// token is an operator of some kind.

				if (token.equals("(")) {
				    // Push the operator onto the operator stack.
				    operatorStack.push(token);
				} else if (token.equals(")")) {

				    try {
					// Process all operators up to the next "(".
					while (!operatorStack.peek().toString().equals("(")) {
					    // Calculate answer and push back onto operand
					    // stack.
					    operate(operandStack, operatorStack);
					}
					// Discard the opening "(".
					operatorStack.pop();
				    } catch (final Exception e) {
				    	NumericalView.setError("No Left parenthesis");
				    	InputView.setEr(true);
				    }
				} else {
				    // Process all other operators.
				   

				    while (!operatorStack.isEmpty() && heirarchy(token) < heirarchy(operatorStack.peek())) {
					operate(operandStack, operatorStack);
				    }
				    // pushing new operator on the stack
				    operatorStack.push(token);
				}
			    } else {
				// Must be an operand. If not, there is an error.
				try {
				    final int coefficient = Integer.parseInt(token);
				    final Poly p = new Poly(0);
				    p.setCoefficient(0, new Rational(coefficient,1));
				    operandStack.push(p);
				} catch (NumberFormatException e) {
					NumericalView.setError("Token is not a valid operator, variable, or operand.");
			    	InputView.setEr(true);
				}
			    }
			}

			while (!operatorStack.isEmpty()) {
			    try {
				// Process the data left on the stacks.
				 operate(operandStack, operatorStack);
			    } catch (final Exception e) {
					NumericalView.setError("Missing operand.");
			    	InputView.setEr(true);
			    }
			}
			// Copy the final polynomial on the stack into this polynomial's data.
			final Poly p = operandStack.pop();
			// Operand stack should be empty at this point.
			if (!operandStack.isEmpty()) {
				NumericalView.setError("Missing operator for operand.");
		    	InputView.setEr(true);
			}
			this.copy(p);
		    }
	
	   public void setCoefficient(int degree, Rational coeff) {
			this.coeffs[degree] = coeff;
		    }
	    public void copy(Poly that) {
	    	this.deg = that.deg;
	    	this.coeffs = new Rational[this.deg + 1];

	    	for (int i = 0; i <= this.deg; i++) {
	    	    this.coeffs[i] = that.coeffs[i];
	    	}
	        }
	
	/**
	 * 
	 * @param o - string operator to assign value to
	 * @return value - assigned value corresponding to string operator
	 */
	private static int heirarchy(String o)
	{
		int value = 0;
		if (o.equals("+") || o.equals("-"))
		{
			value = 1;
		}
		else if (o.equals("*") || o.equals("/") || o.equals("%"))
		{
			value = 2;
		}
		
		return value;
	}
	
	/**
	 * operate helper method calls corresponding poly methods to do operations
	 * 
	 * @param s1 - stack
	 * @param s2 - stack
	 * @throws OperatorException
	 */
	
	private static void operate(Stack<Poly> s1, Stack<String> s2)
	{
		
		String operator = s2.pop();
		Poly a = s1.pop();
		Poly b = s1.pop();
		if (operator.equals("+"))
		{
			s1.push(b.add(a));
		}
		else if (operator.equals("-"))
		{
			s1.push(b.sub(a));
		}
		else if (operator.equals("*"))
		{
			s1.push(b.mul(a));
		}
		else if (operator.equals("/"))
		{
			Rational divisor = a.coeffs[0];
			if (a.deg == 0 && !a.coeffs[0].equals(new Rational(0,1)) )
			{
			Poly result = new Poly(b.deg);
		
			for(int i = 0; i <= b.deg; i ++)
			{
				result.coeffs[i] = b.coeffs[i].div(divisor);
			}
			s1.push(result);
			}
			else
			{
				InputView.setEr(true);
				NumericalView.setError("Error: Division by 0");
			}
		}
		else if (operator.equals("(")) {
			NumericalView.setError("no close bracket");
			InputView.setEr(true);
		}
		else
		{
			NumericalView.setError("cannot perform");
			InputView.setEr(true);
		}
	}
	
	/**
	 * toString method to output string form of poly object
	 */
	 public String toString() {
		String var = InputView.getVar();
	String result = "";

	// Add all the int strings to the final result.
	for (int exponent = this.deg; exponent >= 0; exponent--) {
	    String str = "";
	    Rational coefficient = this.coeffs[exponent];

	    if (!coefficient.equals(new Rational(0, 1))) {

		if (coefficient.greater(new Rational(1, 1)) && exponent > 1) {
		    // Don't add a coefficient of 1 if there is an exponent > 1.
		    str = " + " + coefficient + var + "^" + exponent;
		}
		// Add a positive or negative sign as appropriate.
		if (coefficient.less(new Rational(0,1))) {
		    str = " - ";
		} else {
		    str = " + ";
		}
		Rational c = coefficient.absolute();

		if (!c.equals(new Rational(1,1)) || c.equals(new Rational(1,1)) && exponent == 0) {
		    // Add a lone coefficient if necessary.
		    str += c;
		}
		// Add the exponent, if necessary.
		if (exponent > 1) {
		    str += var + "^" + exponent;
		} else if (exponent == 1) {
		    str += var;
		}
	    }
	    result += str;
	}

	if (result.startsWith(" + ")) {
	    // Remove any plus sign prefix.
	    result = result.substring(3);
	}
	// Remove leading and trailing whitespace.
	result = result.trim();

	if (result.equals("")) {
	    // Must be a zero polynomial.
	    result = "0";
	}
	return result;
    }
	
	/**
	 * equals method defines equivelancy between poly objects
	 * 
	 * @param other - poly object to compare this to
	 * @return result - boolean true or false whether objects are identical
	 */
	
	   public boolean equals(Poly other) {
			if (this.deg != other.deg) {
			    return false;
			}
			for (int i = this.deg; i >= 0; i--) {
			    if (!this.coeffs[i].equals(other.coeffs[i])) {
				return false;
			    }
			}
			return true;
		    }
	
	/**
	 * add method adds other polynomial and this
	 * 
	 * @param other - poly object to add
	 * @return result - sum of polynomials
	 */
	
    public Poly add(Poly other) {
	int i;
	int d = Math.max(this.deg, other.deg);
	int md = Math.min(this.deg, other.deg);
	Poly res = new Poly(d);

	for (i = 0; i <= md; i++) {
	    res.coeffs[i] = this.coeffs[i].add(other.coeffs[i]);
	}
	if (this.deg > other.deg) {
	    for (i = md + 1; i <= d; i++) {
		res.coeffs[i] = this.coeffs[i];
	    }
	} else {
	    for (i = md + 1; i <= d; i++) {
		res.coeffs[i] = other.coeffs[i];
	    }
	}
	i = d;

	while (i > 0 && res.coeffs[i].equals(new Rational(0,1))) {
	    i--;
	}
	res.deg = i;

	return res;
    }
	/**
	 * sub method subtracts other polynomial and this
	 * 
	 * @param other - poly object to subtract
	 * @return result - difference of polynomials
	 */
	
    public Poly sub(Poly other) {
	int i;
	int d = Math.max(this.deg, other.deg);
	int md = Math.min(this.deg, other.deg);
	Poly res = new Poly(d);
	for (i = 0; i <= md; i++) {
	    res.coeffs[i] = this.coeffs[i].sub(other.coeffs[i]);
	}
	if (this.deg > other.deg) {
	    for (i = md + 1; i <= d; i++) {
		res.coeffs[i] = this.coeffs[i];
	    }
	} else {
	    for (i = md + 1; i <= d; i++) {
		res.coeffs[i] = new Rational(0,1).sub(other.coeffs[i]);
	    }
	}
	i = d;
	while (i > 0 && res.coeffs[i].equals(new Rational(0,1))) {
	    i--;
	}
	res.deg = i;

	return res;
    }
	/**
	 * mul method multiplies other polynomial and this
	 * 
	 * @param other - poly object to multiply
	 * @return result - product of polynomials
	 */
	
    public Poly mul(Poly other) {
		int i, j;
		int d = this.deg + other.deg;
		Poly res = new Poly(d);
		for (i = 0; i <= this.deg; i++)
		{
		    for (j = 0; j <= other.deg; j++) {
			res.coeffs[i + j] = res.coeffs[i + j].add(this.coeffs[i].mul(other.coeffs[j]));
		    }
	}
	res.deg = d;
	return res;
    }
	
	/**
	 * diff method differentiates a polynomial
	 * 
	 * @return result - differentiated poly object
	 */
	
    public Poly diff() {
    	int i;
    	Poly res;
    	if (this.deg >= 1) {
    	    int d = this.deg - 1;
    	    res = new Poly(d);
    	    for (i = 0; i <= d; i++) {
    		res.coeffs[i] = this.coeffs[i + 1].mul(new Rational(i + 1,
    			1));
    	    }
    	    res.deg = d;
    	} else {
    	    res = new Poly(0);
    	    res.coeffs[0] = new Rational(0,1);
    	}

    	return res;

        }
	
	/**
	 * evalAt method evaluates a poly object at p
	 * 
	 * @param other
	 * @return v
	 */
	
    public double evalAt(double other)
    {
		double v = 0;
		int i;
	
		for (i = this.deg; i >= 0; i--) {
		    v = (v*other) + this.coeffs[i].convertToDecimal();
		}
	
		return v;
	    
	}
	/*
	 * iRoots method prints all rational roots to this
	 * 
	 */
	
	/*public void iRoots()
	{
		if (this.deg == 0)
		{
		    System.out.println("No roots");
		}
		else if (this.coeffs.length == 0)
		{
		    System.out.println("Infinitely many roots");
		}
		else 
		{
		    // Find the first non-zero coefficient
		    int i = 0;
		    while (i < this.deg && this.coeffs[i].equals(new Rational(0,1)))
		    {
		    	i++;
		    }
		    //poly is just a constant
		    if (i == this.coeffs.length-1)
			{
			    System.out.println("No roots");
			}
		    else
			{
			    Rational c = this.coeffs[this.coeffs.length - 1];
			    Rational d = this.coeffs[i];
			    
			    Rational upperValue = c.abs();
			    //Rational upperValue2 = d.abs();
			    Rational lowerValue = c.abs().mul(new Rational(-1,1));
			    Rational lowerValue2 = d.abs().mul(new Rational(-1,1));
			    
			    // Check for roots in the range and print any found.
			    Rational counter = lowerValue;
			    
			    Rational counter2 = lowerValue2;
			    while (counter.lesseq(upperValue))
			    {
			    	counter2 = lowerValue2;
			    	while (counter2.lesseq(new Rational(0,1)))
			    	{
						// Evaluate the polynomial for 0 and for values that evenly
						// divide the coefficient
						if (counter.equals(new Rational(0,1)) || c.mod(counter))
						{
							if (!counter2.equals(new Rational(0,1)) && d.mod(counter2))
							{
							    if ((this.evalAt((counter).div(counter2))).equals(new Rational(0,1)))
							    {
							    	System.out.println((counter.div(counter2)).toString() + " is a root.");
							    }
							}
						}
						
				    	counter2 = counter2.add(new Rational(1,1));
			    	}
			    	counter = counter.add(new Rational(1,1));
			    }
			}
		}
	}*/
}