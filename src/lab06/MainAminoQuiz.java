package lab06;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MainAminoQuiz
{	
	public static void main(String[] args)
	{		
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {	            	
	                createAndShowGUI(new HomeTestFrame(), 600, 600, "Amino Acid Quiz");
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
		((HomeTestFrame)f).initializeFrame();
		displayInitialMessage(f);
	}
	private static void displayInitialMessage(final JFrame f)
	{
		JOptionPane.showMessageDialog(f,
			    "1.  Click 'Start Quiz' button to begin the 30-second amino acid quiz.\n" +
		         "2.  Click 'Cancel' button to terminate the quiz.\n" +
			     "3.  Input single letter symbol into the 'Symbol' text field for the respective amino acid name.\n" +
		         "4.  Press 'Enter' keyboard button to submit your symbol answer for each amino acid name.");		
	}

}
