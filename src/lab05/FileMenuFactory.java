package lab05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class FileMenuFactory
{
	public JMenuBar getBasicMenuBar()
	{
		JMenuBar jb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem openMenuItem = new JMenuItem("Open");		
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);		
		return jb;
	}

}
