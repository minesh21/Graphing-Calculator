package varu4300;


/*
 * Change one unit into pixels sx = width/(b-a)
 * X = (x-a)*sx
 * sy = height/(ymax - ymin)
 * Y1 = height - (y-ymin)*sy
 * Y2 = (ymax-y)*sy
 * Y1=Y2(same expression)
 */
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LabelView extends JPanel{
	//creating private instance objects
	private JLabel exp = new JLabel("Function:");
	private JLabel v = new JLabel("Var:");
	private JLabel s = new JLabel("Start:");
	private JLabel e = new JLabel("End:");
	/**
	 * Constructor initilializes the layoutView() method
	 */
	public LabelView()
	{
		
		this.layoutView();
	}
	/**
	 * layoutView() method adds the objects to the Frame
	 */
	public void layoutView()
	{
		this.setLayout(new GridLayout(1,4));
		this.add(exp);
		this.add(v);
		this.add(s);
		this.add(e);
	}
}
