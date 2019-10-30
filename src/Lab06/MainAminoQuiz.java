package Lab06;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import  javax.swing.Timer;
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
		
}

}
