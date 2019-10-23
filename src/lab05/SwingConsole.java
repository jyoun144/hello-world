package lab05;
import javax.swing.*;

public class SwingConsole
{
	public static void run(final JFrame f, final int width, final int height)
	{
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run()
			{
				f.setTitle("Martial Arts Training Combinations");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setVisible(true);	
				f.setLocationRelativeTo(null);			
			}
		});
	}

}
