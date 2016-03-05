package varu4300;

import javax.swing.JFrame;
/**
 * @author Minesh varu
 * @version 02/12/2013
 */
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class Main extends JPanel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame panel = new JFrame("Minesh's Plotter");
		NumericalView view = new NumericalView();
		panel.setContentPane(view);
		panel.setSize(430,100);
		panel.setLocation(0,0);
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setVisible(true);

	}

}
