package lab07;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainThreading
{
	public static void main(String[] args)
	{		
		SwingUtilities.invokeLater(new Runnable() {
	            public void run() {	            	
	                createAndShowGUI(new HomeSwingFrame(), 600, 600, "Prime Number Counter");
	            }
	        });
	}
	private static void createAndShowGUI(final JFrame f, final int width, final int height, String title) 
	{
		f.setTitle(title);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(width, height);
		f.setVisible(true);	
		f.setLocationRelativeTo(null);
		((HomeSwingFrame)f).initializeFrame();		
	}
}
