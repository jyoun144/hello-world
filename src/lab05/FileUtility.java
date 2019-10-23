package lab05;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileUtility
{
	public static void saveToFile(JFrame frame, JTextArea txtArea)
	{
		JFileChooser jfc = new JFileChooser();
		if((jfc.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) || (jfc.getSelectedFile() == null))
		{
			return;
		}		
		File chosenFile = jfc.getSelectedFile();		
	
	if(jfc.getSelectedFile().exists())
	{
		String message = "File " + jfc.getSelectedFile().getName() + " exists.  Overwrite?";
		if(JOptionPane.showConfirmDialog(frame, message) != JOptionPane.YES_OPTION)
		{
			return;			
		}
	}
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(chosenFile));
			writer.write(txtArea.getText());
			writer.flush();
			writer.close();			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame,  ex.getMessage(), "Could not write file", JOptionPane.ERROR_MESSAGE);			
		}	
	}	
	public static void loadFromFile(JFrame frame, JTextArea txtArea)
	{
		JFileChooser jfc = new JFileChooser();
		if(jfc.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION ||
				jfc.getSelectedFile() == null)
		{
			return;			
		}
		File chosenFile = jfc.getSelectedFile();
		try 
		{
			txtArea.setText("");
			BufferedReader reader = new BufferedReader(new FileReader(chosenFile));
			for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
			{	
				txtArea.append(nextLine + "\n");				
			}
			reader.close();				
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, ex.getMessage(), "Could not read file", JOptionPane.ERROR_MESSAGE);			
		}		
	}
}
