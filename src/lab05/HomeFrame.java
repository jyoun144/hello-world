package lab05;
import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame
{	
	private static final long serialVersionUID = -2082381602987081784L;	
	private static final String newLine = "\n";
	private JComboBox<String> cb = new JComboBox<>();	
	private String[] lst = new String[] {"---Select Strike Type---", "Punch:Jab", "Punch:Cross", "Punch:Hook", "Punch:Upper Cut", "Punch:Overhand",
											"Kick:Round", "Kick:Front", "Kick:Side", "Kick:Reverse", "Kick:Switch"};
	private JButton	
	btnAddStrike = new JButton("Add Strike"),
	btnAddSeparator = new JButton("Add Separator");

	private JTextArea txt = new JTextArea(20,40);
	
	public HomeFrame()
	{	
		this.setButtonsState(false);		
		for(String item : lst)	
		{
			cb.addItem(item);
		}		
		cb.addActionListener((ae) ->
		{
			
		if(cb.getSelectedIndex() == 0)
		{
			this.setButtonsState(false);
		}
		else
		{
			this.setButtonsState(true);					
		}
		});
		btnAddStrike.addActionListener((ae) ->
		{
			txt.append(cb.getSelectedItem().toString() + newLine);
			cb.setSelectedIndex(0);
		});
		
		btnAddSeparator.addActionListener((ae) ->
		{
			txt.append("***********************" + newLine);
			// cb.setSelectedIndex(0);
		});
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(txt), BorderLayout.CENTER);
		this.add(this.getBottomPanel(), BorderLayout.SOUTH);				
		this.setSize(400,300);
		this.setJMenuBar(FileMenuFactory.getBasicMenuBar(this, txt));		
	}
	private JPanel getBottomPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		panel.add(btnAddStrike);
		panel.add(cb);
		panel.add(btnAddSeparator);
		return panel;
	}
	private void setButtonsState(Boolean isEnabled)
	{
		btnAddStrike.setEnabled(isEnabled);					
	}
}
