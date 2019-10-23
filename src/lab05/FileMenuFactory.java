package lab05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class FileMenuFactory
{
	public static JMenuBar getBasicMenuBar()
	{
		JMenuBar jb = new JMenuBar();		
		JMenu fileMenu = new JMenu("File");
		jb.add(fileMenu);
		fileMenu.setMnemonic('F');
		JMenuItem newMenuItem = new JMenuItem("Save");
		newMenuItem.setMnemonic('N');
		JMenuItem openMenuItem = new JMenuItem("Open");	
		openMenuItem.setMnemonic('O');
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);		
		return jb;
	}

}
