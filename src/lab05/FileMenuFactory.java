package lab05;
import javax.swing.*;

public class FileMenuFactory
{
	public static JMenuBar getBasicMenuBar(JFrame frame, JTextArea txtArea)
	{
		JMenuBar menuBar = new JMenuBar();		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		menuBar.add(fileMenu);		
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener((ae) ->
		{
			FileUtility.saveToFile(frame, txtArea);		
		});
		saveItem.setMnemonic('S');
		JMenuItem openMenuItem = new JMenuItem("Open");	
		openMenuItem.setMnemonic('O');
		openMenuItem.addActionListener((ae) ->
		{
			int dialogResult = showConfirmDialog(frame, txtArea);	
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				FileUtility.loadFromFile(frame, txtArea);
			}
		});
		JMenuItem newMenuItem = new JMenuItem("New");	
		newMenuItem.setMnemonic('N');
		newMenuItem.addActionListener((ae) ->
		{
			showConfirmDialog(frame, txtArea);						
		});
		fileMenu.add(saveItem);
		fileMenu.add(openMenuItem);	
		fileMenu.add(newMenuItem);	
		return menuBar;
	}
	
	private static int showConfirmDialog(JFrame frame, JTextArea txtArea)
	{
		 int dialogResult = JOptionPane.showConfirmDialog(frame,"Are you sure?  Existing text will be permanently deleted.");
		 if(dialogResult == JOptionPane.YES_OPTION)
			{
				txtArea.setText("");
			}
		 return dialogResult;
	}
}