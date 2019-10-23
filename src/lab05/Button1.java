package lab05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Button1 extends JFrame
{	
	private static final long serialVersionUID = -2082381602987081784L;	
	private static final String newLine = "\n";
	private JComboBox<String> cb = new JComboBox<>();	
	private String[] lst = new String[] {"Punch:Jab", "Punch:Cross", "Punch:Hook", "Punch:Upper Cut", "Punch:Overhand",
											"Kick:Round", "Kick:Front", "Kick:Side", "Kick:Reverse", "Kick:Switch"};
	private JButton	
	b1 = new JButton("Add Strike"),
	b2 = new JButton("Add Separator");

	private JTextArea txt = new JTextArea(20,40);
	
	public Button1()
	{		
		for(String item : lst)	
		{
			cb.addItem(item);
		}		
		b1.addActionListener((ae) ->
		{
			txt.append(cb.getSelectedItem().toString() + newLine);			
		});
		
		b2.addActionListener((ae) ->
		{
			txt.append("***********************" + newLine);			
		});
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(txt), BorderLayout.CENTER);
		this.add(this.getBottomPanel(), BorderLayout.SOUTH);				
		this.setSize(400,300);
		this.setJMenuBar(FileMenuFactory.getBasicMenuBar(this));		
	}
	private JPanel getBottomPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		panel.add(b1);
		panel.add(cb);
		panel.add(b2);
		return panel;
	}
}
