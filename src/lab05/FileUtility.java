package lab05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class FileUtility
{
	public static void saveToFile()
	{
		JFileChooser jfc = new JFileChooser();
		of(jfc.showSaveDialog(parent))
	}

}
