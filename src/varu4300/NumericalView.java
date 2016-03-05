package varu4300;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class NumericalView extends JPanel

{
	//creating LabelView and InputView objects and a error label
	private LabelView lv = null;
	private InputView iv = null;
	private static JLabel error = new JLabel();
	/**
	 * Constructor initializes new LabelView() and InputView() objects
	 * and the layoutView() method
	 */
	public NumericalView()
	{
		lv = new LabelView();
		iv = new InputView();
		this.layoutView();
	}
	/**
	 * layoutView() method adds the LabelView() frame as well as
	 * the InputView() Frame to one frame and adds the label for 
	 * error at the bottom
	 */
	public void layoutView()
	{
		this.setLayout(new BorderLayout());
		this.add(lv, BorderLayout.NORTH);
		this.add(iv, BorderLayout.WEST);
		this.add(error, BorderLayout.SOUTH);
	}
	/**
	 * Sets the texts of the error label to something new 
	 * 
	 * @param err
	 */
	public static void setError(String err)
	{
		error.setText(err);
	}
}
