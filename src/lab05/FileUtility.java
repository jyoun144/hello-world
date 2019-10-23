package lab05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.*;

public class FileUtility
{
	public static void saveToFile(JFrame frame)
	{
		JFileChooser jfc = new JFileChooser();
		if((jfc.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) || (jfc.getSelectedFile() == null))
		{
			return;
		}
		
		File chosenFile = jfc.getSelectedFile();		
	}

}
