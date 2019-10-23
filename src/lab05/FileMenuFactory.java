package lab05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

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
		fileMenu.add(saveItem);
		fileMenu.add(openMenuItem);		
		return menuBar;
	}

}
