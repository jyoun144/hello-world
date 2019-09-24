package lab04;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class FastaViewer 
{
	private StringBuilder sequence;
	private String header = null;	
	BufferedReader reader = null;
	
	public List<FastaViewer> readFastaFile(String filePath) throws IOException
	{
		List<FastaViewer> list = new ArrayList<FastaViewer>();		
		Integer seqCount = 0;				
		FastaViewer obj = null;		
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{	
			if(nextLine.contains(">"))
			{				
				obj = new FastaViewer(nextLine);				
				list.add(obj);				
				seqCount++;				
			}
			else
			{
				if(!nextLine.trim().equals(""))
				{														
					obj.appendSequence(nextLine);
				}
			}		
		}
		reader.close();	
		return list;
	}	
	public FastaViewer getNextSequence() throws IOException
	{
		FastaViewer sequence = null;
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{
			FastaViewer obj = null;
			if(nextLine.contains(">"))
			{				
				obj = new FastaViewer(nextLine);				
							
							
			}
			else
			{
				if(!nextLine.trim().equals(""))
				{														
					obj.appendSequence(nextLine);
				}
			}		
		}
		
		return sequence;
		
	}
	public FastaViewer(String filePath) throws IOException
	{
		// this.header = header;
		this.sequence = new StringBuilder();
		this.reader = new BufferedReader(new FileReader(new File(filePath)));		
	}
	public String getHeader()
	{
		String result = "";					
		Pattern p = Pattern.compile(">(\\s*\\w*)*");		
		Matcher matcher = p.matcher(this.header);		
		while(matcher.find())
		{
			int start =  matcher.start();			
			int end = matcher.end();			
			result = this.header.substring(start + 1, end).trim();
		}	
			
	return result;	
	}
	public String getSequence()
	{	
		return this.sequence.toString().replaceAll("\\s*", "");		
	}
	
	public void appendSequence(String sequence)
	{
		if(sequence != null && !sequence.trim().equals(""))
		{
			this.sequence.append(sequence);			
		}
	}	
}
