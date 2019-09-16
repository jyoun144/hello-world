package lab03;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FastaFileUtil
{
	protected final String tab = "\t";
	protected final String newline = "\n";
	public void saveFastaSummaryReport(String sourceFilePath, String targetFilePath) throws IOException
	{
		List<FastaObj> summaryList = this.getDnaCountsFromFastaFile(sourceFilePath);
		this.printDnaCounts(summaryList);
		this.writeTabDelimitedFile(summaryList, targetFilePath);		
	}	
	protected List<FastaObj> getDnaCountsFromFastaFile(String filePath) throws IOException
	{
		List<FastaObj> list = new ArrayList<FastaObj>();		
		String targetFile = filePath;
		Integer seqCount = 0;
		BufferedReader reader = new BufferedReader(new FileReader(new File(targetFile)));		
		FastaObj obj = null;
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{	
			if(nextLine.contains(">"))
			{
				String sequenceId = this.getSequenceId(nextLine);		
				obj = new FastaObj(sequenceId);
				list.add(obj);				
				seqCount++;				
			}
			else
			{
				if(!nextLine.trim().equals(""))
				{					
					this.setCounts(nextLine, obj);
					// this.setCounts2(nextLine, obj);					
					obj.appendSequence(nextLine.trim());
				}
			}		
		}
		reader.close();	
		return list;
	}	
	protected String GetTabDelimitedData(List<FastaObj> list)
	{
		StringBuilder str = new StringBuilder();
		str.append("sequenceID" + tab + "numA" + tab + "numC" + tab + "numG" + tab + "numT" + tab + "sequence" + newline);
		
		for(int i=0;i<list.size();i++)
		{	
			var obj = list.get(i);
			str.append(obj.getSequenceId() + tab + obj.getACount() + tab + obj.getCCount() + 
			           tab + obj.getGCount() + tab + obj.getTCount() + tab + obj.getSequence() + newline);			
		}
		return str.toString();
	}
	protected void writeTabDelimitedFile(List<FastaObj> list, String filePath) throws IOException
	{		
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)));
		String fileContents = this.GetTabDelimitedData(list);
		writer.write(fileContents);
		writer.flush();
		writer.close();		
	}
	protected void setCounts(String nextLine, FastaObj obj)
	{
		for(int i=0; i<nextLine.length(); i++)
		{
			int aCount = 0;
			int gCount = 0;
			int tCount = 0;
			int cCount = 0;
			String upperCaseStr = nextLine.toUpperCase();
			if(upperCaseStr.charAt(i) == 'A')
			{
				aCount++;
			}
			else if(upperCaseStr.charAt(i) == 'G')
			{
				gCount++;
			}
			else if(upperCaseStr.charAt(i) == 'T')
			{
				tCount++;
			}
			else if(upperCaseStr.charAt(i) == 'C')
			{
				cCount++;
			}
			obj.incrementCounts(aCount, gCount, tCount, cCount);
		}		
	}	
	protected String getSequenceId(String singleFastaLine)
	{
		String result = "";
		Pattern p = Pattern.compile(">\\s*\\w+");
		Matcher matcher = p.matcher(singleFastaLine);
		while(matcher.find())
		{
			int start =  matcher.start();
			int end = matcher.end();			
			result = singleFastaLine.substring(start + 1, end).trim();
		}		
		return result;		
	}
	// This method is just used for testing purposes
	protected void printDnaCounts(List<FastaObj> list)
	{
		System.out.println("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence");
		for(int i=0;i<list.size();i++)
		{	
			var obj = list.get(i);
			System.out.println(obj.getSequenceId() + tab + obj.getACount() + tab + obj.getCCount() + 
					           tab + obj.getGCount() + tab + obj.getTCount() + tab + obj.getSequence());			
		}
	}
}

