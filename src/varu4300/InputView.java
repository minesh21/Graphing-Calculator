package varu4300;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
@SuppressWarnings("serial")
public class InputView extends JPanel {
	//declaring local instance variables and objects
	private JButton plot = new JButton("Plot");
	private static JTextField expression = new JTextField(7);
	private static JTextField var = new JTextField(7);
	private static JTextField start = new JTextField(7);
	private static JTextField end = new JTextField(7);
	private static boolean er = false;
	private class ButtonListener extends JPanel implements ActionListener
	{
		/**
		 * actionPerformed Method creates new JPanel 
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			try {
				//Creating variables that get the start and end points
				double a = InputView.getStart();
				double b = InputView.getEnd();
			
				
				if (a < b && InputView.var.getText().length()== 1 && !InputView.isNumeric(InputView.var.getText()) )
				{
					//Creating variables that get the expression and variable to evaluate at
					String exp = InputView.getExpression();
					String var = InputView.getVar();
					//setting the variable er to false
					InputView.setEr(false);
					//creating a new PlotPoints object
					PlotPoints p = new PlotPoints(exp,var,a,b);
					if (!InputView.getEr(InputView.er))
					{
						//Creating new JPanel to draw grpah and plot points
						JFrame panel = new JFrame("Minesh's Plotter");
						panel.setContentPane(p);
						panel.setSize(600,600);
						panel.setLocation(0, 100);
						panel.setBackground(Color.WHITE);
						panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						panel.setVisible(true);
					}
				}
				else{
					NumericalView.setError("Invalid Input, try again");
				}
			}
			catch (Exception err)
			{
				NumericalView.setError("Invalid Input, try again");
			}
		
		}

		
	}
	/**
	 * Constructor intializes the button and the layout view for main
	 */
	public InputView()
	{
		this.layoutView();
		this.registerButtons();
	}
	/**
	 * layoutView method adds the buttons and labels to the input JPanel
	 */
	
	public void layoutView()
	{
		
		this.add(expression);
		this.add(var);
		this.add(start);
		this.add(end);
		this.add(this.plot);
	}
	/**
	 * Register button method tells the button to create a 
	 * new Graph each time clicked
	 */
	public void registerButtons()
	{
		plot.addActionListener(new ButtonListener());
	}
	/**
	 * getExpresssion returns the expression inputed
	 * @return str
	 */
	public static String getExpression()
	{
		String str = "";
		str = expression.getText();

		return str;
				
	}
	/**
	 * getVar() method returns the variable to evaluate
	 * the expression at
	 * 
	 * @return str
	 */
	public static String getVar()
	{
		String str = "";
		str = var.getText();
		return str;
				
	}
	/**
	 * getStart() returns a converted string into 
	 * a double (the point where the graph begins at)
	 * 
	 * @return str
	 */
	public static double getStart()
	{
		double str= 0;
		str = Double.parseDouble(start.getText());
		return str;
				
	}
	/**
	 * getEnd() returns a converted string into 
	 * a double (the point where the graph ends at)
	 * 
	 * @return finish
	 */
	public static double getEnd()
	{
		double finish=0;
	
		finish = Double.parseDouble(end.getText());
		
		return finish;
				
	}
	/**
	 * setEr() Method sets the variable (er) to true or false
	 * 
	 * @param b
	 */
	public static void setEr(boolean b)
	{
		InputView.er = b;
	}
	/**
	 * getEr() Method determines the variable er is 
	 * true or false
	 * 
	 * @param b
	 * @return b
	 */
	public static boolean getEr(boolean b)
	{
		return b;
	}
	/**
	 * isNumeric() Method determines whether or not a string
	 * is a number
	 * 
	 * @param str
	 * @return isNum
	 */
	public static boolean isNumeric (String str)
	{
		boolean isNum = true;
		double d = 0;
		try{
		d = Double.parseDouble(str);
		}
		catch(NumberFormatException e)
		{
			isNum = false;
		}
		return isNum;
	}

}
