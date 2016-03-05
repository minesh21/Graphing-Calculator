package varu4300;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;

import javax.swing.JComponent;
import javax.swing.JLabel;
@SuppressWarnings("serial")
public class PlotPoints extends JComponent{
	//Declaring private instance variables
	private Poly eval;
	@SuppressWarnings("unused")
	private String variable;
	private double a;
	private double b;
	public static JLabel xp = new JLabel("hello");
	public static JLabel yp = new JLabel("jello");
	/**
	 * Constructor which takes in the values of the text fields
	 * and sets it equal to the respective instance variables
	 * @param exp
	 * @param var
	 * @param start
	 * @param end
	 */
	 
	public PlotPoints(String exp,String var, double start, double end) {
		if (exp.startsWith("-"))
		{
			exp = "-1*" + exp.substring(1);
		}
		this.eval = new Poly(exp);
		this.variable = var;
		this.a = start;
		this.b = end;
		this.repaint();
		
		
	}

	/**
	 * Paint Components draws the graph
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		//declaring local variables
		double ymin = 0;
		double ymax = 0;
		Poly p = null;
		double [] xdata = new double[getWidth()];
		double [] ydata = new double[getWidth()];
		double [] fxdata = new double[getWidth()];
		double [] fydata = new double[getWidth()];
		double [] sxdata = new double[getWidth()];
		double [] sydata = new double[getWidth()];
		int [] x1data = new int[getWidth()];
		int [] y1data = new int[getWidth()];
		int [] x2data = new int[getWidth()];
		int [] y2data = new int[getWidth()];
		int [] x3data = new int[getWidth()];
		int [] y3data = new int[getWidth()];
		DecimalFormat df = new DecimalFormat("#.##");
		double sx = getWidth()/(b-a);
		double sy = 0;
		double x = 0;
		double y = 0;
		p = this.eval;
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//Converting one unit into pixel for x
		sx = getWidth()/(this.b-this.a);
		//Creating poly for first derivative
		Poly first = p.diff();
		//Creating second for first derivative
		Poly second = first.diff();
		//Calls the evaluate points method, which evaluates the polynomials at a range of points
		this.evaluatePoints(p, xdata, ydata);
		this.evaluatePoints(first, fxdata, fydata);
		this.evaluatePoints(second, sxdata, sydata);
		
		//If the max point of y equals the min point of y then the max and min will
		//equal the b and a variables that were inputted otherwise whill get the max
		//point from the y values that were evaluated and the min value
		
		if (ymax == ymin)
		{
			ymax = b;
			ymin = a;
		}
		else
		{
			ymax = this.getMax(ydata);
			ymin = this.getMin(ydata);
		}	
		//Converting one unit into pixel for y
		sy = getHeight()/(ymax - ymin);
		//Converting the the x and y points of the data to go with the 
		//coordinates on the JPanel for original poly and first and second
		for (int i = 0; i < ydata.length; i++)
		{
			x = (xdata[i] - a) * sx;
			y = (ymax -ydata[i])*sy;
			x1data[i] = (int) x;
			y1data[i] = (int) y;
		}
		for (int i = 0; i < fydata.length; i++)
		{
			x = (fxdata[i] - a) * sx;
			y = (ymax -fydata[i])*sy;
			x2data[i] = (int) x;
			y2data[i] = (int) y;
		}
		for (int i = 0; i < sydata.length; i++)
		{
			x = (sxdata[i] - a) * sx;
			y = (ymax -sydata[i])*sy;
			x3data[i] = (int) x;
			y3data[i] = (int) y;
		}
		
		//will move the x axis according to the range inputed between a and b
		if (this.a<= 0 && this.b >= 0)
		{
			g2d.setColor(Color.black);
			g2d.draw(new Line2D.Double(-a*sx, 0 ,-a*sx,getHeight()));
			g2d.drawString("0",getWidth()/2,getHeight()-5);
			//Draws string representation for functions
			
		}
		//will move the y-axis according to the between ymax and ymin
		if ((int)ymin <= 0 && (int)ymax >= 0)
		{
			g2d.setColor(Color.black);
			g2d.draw(new Line2D.Double(0,(ymax)*sy-1,getWidth(),(ymax)*sy-1));
			g2d.drawString("0",0,(float)((ymax)*sy-1));
		}
		double k = 0;
		//Creating a range that will display between the ymin and ymax
		for (double i = ymax; i >= ymin; i -= (ymax-ymin)/9)
		{
			g2d.setColor(Color.black);
			g2d.draw(new Line2D.Double(2, k * (getHeight()/9), 10, k*(getHeight()/9)));
			g2d.drawString(df.format(i), 10,(float) ((k*(getHeight()/9))+12));
			k = k + 1;
		}
		double h = 0;
		//Creating a range that will display between the a and b
		for (double i = a; i <= b; i += (b-a)/9)
		{
			g2d.setColor(Color.black);
			g2d.draw(new Line2D.Double(h * (getWidth()/9), getHeight() , h * (getWidth()/9), getHeight()-5));
			g2d.drawString(df.format(i),(float)( h * (getWidth()/9)),  getHeight()-5);
			h = h + 1;
			
		}

		//Plots and draws the curve or line for the oringial, first and second derivatives
		g2d.setColor(Color.red);
		g2d.drawPolyline(x1data,y1data, ydata.length);
		
		g2d.setColor(Color.blue);
		g2d.drawPolyline(x2data,y2data, ydata.length);
		
		g2d.setColor(Color.green);
		g2d.drawPolyline(x3data,y3data, ydata.length);
		
		g2d.setColor(Color.red);
		g2d.drawString("f(x) = " + p.toString(), (getWidth()/2)+5, 20);
		g2d.setColor(Color.blue);
		g2d.drawString("f'(x) = " + first.toString(), (getWidth()/2)+5, 40);
		g2d.setColor(Color.green);
		g2d.drawString("f''(x) = " + second.toString(), (getWidth()/2)+5, 60);
		
	}
	/**
	 * evaluatePoints method evaluates the x and y points of 
	 * the polynomial, takes in a Poly and two array list of
	 * type double
	 * 
	 * @param p
	 * @param x
	 * @param y
	 */
	public void evaluatePoints(Poly p, double[] x, double[] y)
	{

		int j = 0;
		double ypoint = 0;
			try{
			for (double i = a; i < b; i += (b-a)/getWidth())
			{
				ypoint = p.evalAt(i);
				y[j] = ypoint;
				x[j] = i;
				j = j + 1;
				
			}
			}
			catch (Exception e){
				InputView.setEr(true);
			}
	}
	/**
	 * getMax Method gets the max y point of the
	 * y data 
	 * 
	 * @param data
	 * @return ymax
	 */
	public double getMax(double [] data)
	{
		double ymax = data[0];
		
		for (int i =0; i < data.length; i++)
		{
			if (data[i] >ymax)
			{
				ymax = data[i];
			}
		}
		return ymax;
	}
	/**
	 * getMin Method gets the min y point of the
	 * y data 
	 * 
	 * @param data
	 * @return ymin
	 */
	public double getMin(double [] data)
	{
		double ymin = data[0];
		for (int i =0; i < data.length; i++)
		{
			
			if (data[i] < ymin)
			{
				ymin = data[i];
			}
		}
		return ymin;
	
	}

	
}
