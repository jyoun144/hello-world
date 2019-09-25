package lab04;
import java.io.*;

public class FastaViewer 
{	
	private BufferedReader reader = null;	
	
	public FastaObj getNextSequence() throws IOException
	{		
		FastaObj obj = null;
		boolean hasRetrievedHeader = false;		
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{					
			if(nextLine.contains(">"))
			{
				if(!hasRetrievedHeader)
				{
					obj = new FastaObj(nextLine);
					hasRetrievedHeader = true;
				}
				else
				{					
					reader.reset();
					break;
				}
			}
			else
			{
				if(!nextLine.trim().equals(""))
				{														
					obj.appendSequence(nextLine);
					reader.mark(100);
				}
			}	
		}
		
		return obj;		
	}
	public FastaViewer(String filePath) throws IOException
	{		
		this.reader = new BufferedReader(new FileReader(new File(filePath)));		
	}	
}
