package lab04;
import java.io.*;

public class FastaViewer2 
{	
	private BufferedReader reader = null;
	private String nextSequenceHeader = null;
	private boolean hasRetrievedHeader = false;
	
	public FastaObj getNextSequence() throws IOException
	{		
		FastaObj obj = null;
				
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
					this.nextSequenceHeader = nextLine;					
					break;
				}
			}			
			else
			{
				if(this.nextSequenceHeader != null)
				{
					obj = new FastaObj(this.nextSequenceHeader);
					this.nextSequenceHeader = null;					
				}
				if(!nextLine.trim().equals(""))
				{														
					obj.appendSequence(nextLine);					
				}
			}	
		}
		
		return obj;		
	}
	public FastaViewer2(String filePath) throws IOException
	{		
		this.reader = new BufferedReader(new FileReader(new File(filePath)));		
	}	
}
