package varu4300;
	
import java.util.Scanner;


public class Rational {
	/**
	 * @author: Minesh Varu 
	 * @version: 27/09/2013
	 */
	
	private double numerator  = 0;   //numerator
	private double denominator = 1;//denominator
	
	/**
	 * Rational Constructor takes integers "a" (numerator) and "b" (denominator) as arguments
	 * and updates the numerator and denominator and normalizes them
	 * 
	 * @param a
	 * @param b
	 */
	public Rational(double a, double b)
	{
		
		this.numerator = a ;
		this.denominator = b ;
		this.normalize();
	}
	
    public Rational(double n) 
    {
    	this.numerator = n;
    }
	/**
	 * Rational Constructor takes a String "str" as argument and splits them up
	 * where the delimiter is and stores them in variable numerator and denominator
	 * 
	 * @param str
	 * 
	 */
	public Rational(String str) 
	{
		Scanner split = new Scanner(str);
		try{
			split.useDelimiter("/");
			this.numerator = split.nextInt();
			if (split.hasNextInt())
			{
				this.denominator = split.nextInt();
				this.normalize();
			}
		}
		catch (Exception e)
		{
			NumericalView.setError("Invalid Format");
			InputView.setEr(true);
		}
		finally
		{
		split.close();
		}
	}
	/**
	 * GCD method - Greatest Common Divisor finds the greatest common divisor
	 * 
	 * @param a
	 * @param b
	 * @return a
	 */
	private static double gcd(double a, double b)
	{
		double t = 0;

		if (a < b) {
		    t = a;
		    a = b;
		    b = t;
		}
		while (b != 0) {
		    t = a % b;
		    a = b;
		    b = t;
		}
		return a;
	}
	/**
	 * To String methods prints numerator and denominator into form "a/b"
	 * 
	 * @return str
	 */
	public String toString()
	{
		String str = "";
		if (this.denominator != 1)
		{
			str =  this.numerator + "/" + this.denominator;
		}else
		{
		str = this.numerator + "";
		}
		return str;
		
	}
	/**
	 * Equals Method determines if both numerators and denominators are equal
	 * 
	 * @param other
	 * @return True/False
	 */
	public Boolean equals(Rational other)
	{
		return (this.numerator * other.denominator - this.denominator * other.numerator == 0);
	}
	/**
	 * Add Method adds Rational numbers
	 * 
	 * @param other
	 * @return result
	 */
	public Rational add(Rational other)
	{
		double num = this.numerator * other.denominator + other.numerator * this.denominator;
		double den = this.denominator * other.denominator;
		return new Rational(num,den);
	}
	/**
	 * Subtraction Method substracts Rational numbers
	 * 
	 * @param other
	 * @return subtracted results
	 */
	public Rational sub(Rational other)
	{
		double num = this.numerator * other.denominator - other.numerator * this.denominator;
		double den = this.denominator * other.denominator;
		return new Rational(num,den);

	}
	/**
	 * Multiply Method multiplies Rational numbers
	 * 
	 * @param other
	 * @return multiplied result
	 */
	public Rational mul(Rational other)
	{
		double num = this.numerator *  other.numerator ;
		double den = this.denominator * other.denominator;
		return new Rational(num,den);
	}
	/**
	 * Divide Method divides Rational numbers
	 * 
	 * @param other
	 * @return divided result
	 */
	public Rational div(Rational other)
	{
		double num = this.numerator * other.denominator ;
		double den = other.numerator * this.denominator;
		return new Rational(num,den);
	}
	/**
	 * Absolute Method returns the absolute value of the numerator
	 * 
	 * @return r
	 */
	public Rational absolute()
	{
		Rational r = new Rational(Math.abs(this.numerator), Math.abs(this.denominator));
		return r;
	}
	/**
	 * Less method checks to see if the this.numerator is less
	 * then the other numerator
	 * 
	 * @param other
	 * @return this.numerator is less then the other numerator
	 */
	public Boolean lessEqualTo(Rational other)
	{
		return (this.numerator <= other.numerator);
	}
	/**
	 * Less Than method checks to see if the this.numerator is less
	 * then the other numerator
	 * 
	 * @param other
	 * @return if this.numerator is less then the other numerator
	 */
	public Boolean less(Rational other)
	{
		return (this.numerator < other.numerator);
	}
	/**
	 * Greater Equal To method checks to see if the this.numerator is greater then or equal to
	 * then the other numerator
	 * 
	 * @param other
	 * @return if this.numerator >= other.numerator
	 */
	public Boolean greaterEqualTo(Rational other)
	{
		return (this.numerator >= other.numerator);
	}
	/**
	 * Greater method checks to see if the this.numerator is greater
	 * then the other numerator
	 * 
	 * @param other
	 * @return if this.numerator > other.numerator
	 */
	public Boolean greater(Rational other)
	{
		return (this.numerator > other.numerator);
	}
	/**
	 * Mod method checks to see if the modulus of two numerators is 0
	 * 
	 * @param other
	 * @return if this.numerator % other.numerator == 0
	 */
	public Boolean mod(Rational other)
	{
		return (this.numerator % other.numerator == 0);
	}

	/**
	 * Normalize method reduces the numerator and denominator by its GCD
	 */

	public void normalize()
	{
		double t = gcd(this.numerator, this.denominator);
		this.numerator = this.numerator / t;
		this.denominator = this.denominator / t;

		if (this.denominator < 0) 
		{
		    this.denominator = -this.denominator;
		    this.numerator = -this.numerator;
		}
				
	}
	public double convertToDecimal()
	{
		
		return this.numerator/this.denominator;
		
		
	}
	
	 
	
}
