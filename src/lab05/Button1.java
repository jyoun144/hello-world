package lab05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Button1 extends JFrame
{	
	private static final long serialVersionUID = -2082381602987081784L;	
	private JButton
	b1 = new JButton("Button 1"),
	b2 = new JButton("Button 2");
	private JTextArea txt = new JTextArea(20,40);
	private ActionListener al = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String name = ((JButton)e.getSource()).getText();
					txt.append(name + "\n");					
				}
			};
	public Button1()
	{
		b1.addActionListener(al);
		b2.addActionListener(al);
		this.setLayout(new FlowLayout());
		this.add(b1);
		this.add(b2);
		this.add(new JScrollPane(txt));
	}
}
