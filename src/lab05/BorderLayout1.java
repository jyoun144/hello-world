package lab05;
import javax.swing.*;
import java.awt.*;

public class BorderLayout1 extends JFrame
{	
	private static final long serialVersionUID = -3737577161564344776L;

	public BorderLayout1()
	{
		this.add(BorderLayout.NORTH, new JButton("North"));
		this.add(BorderLayout.SOUTH, new JButton("South"));
		this.add(BorderLayout.EAST, new JButton("East"));
		this.add(BorderLayout.WEST, new JButton("West"));
		this.add(BorderLayout.CENTER, new JButton("Center"));		
	}
	

}
