package lab05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Button1 extends JFrame
{	
	private static final long serialVersionUID = -2082381602987081784L;	
	private JComboBox<String> cb = new JComboBox<>();
	private static String STRIKE_TITLE = "Add Strike";
	private static String SEPARATOR_TITLE = "Add Separator";
	private static String SEPARATOR = "***********************";
	private String[] lst = new String[] {"Punch:Jab", "Punch:Cross", "Punch:Hook", "Punch:Upper Cut", "Punch:Overhand",
											"Kick:Round", "Kick:Front", "Kick:Side", "Kick:Reverse", "Kick:Switch"};
	private JButton	
	b1 = new JButton(STRIKE_TITLE),
	b2 = new JButton(SEPARATOR_TITLE);

	private JTextArea txt = new JTextArea(20,40);
	private ActionListener al = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String currentText;
					if(((JButton)e.getSource()).getText().equals(STRIKE_TITLE))
					{
						currentText = cb.getSelectedItem().toString();
					}
					else
					{
						currentText = SEPARATOR;
					}
					txt.append(currentText + "\n");					
				}
			};  
	public Button1()
	{		
		for(String item : lst)	
		{
			cb.addItem(item);
		}		
		b1.addActionListener(al);
		b2.addActionListener(al);
		this.setLayout(new FlowLayout());
		this.add(b1);
		this.add(b2);
		this.add(cb);		
		this.add(new JScrollPane(txt));
		this.setSize(400,300);
	}
}
