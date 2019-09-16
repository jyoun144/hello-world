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
	private final String tab = "\t";
	private final String newline = "\n";
	public void saveFastaSummaryReport(String sourceFilePath, String targetFilePath) throws IOException
	{
		List<FastaObj> summaryList = this.getDnaCountsFromFastaFile(sourceFilePath);
		this.printDnaCounts(summaryList);
		this.writeTabDelimitedFile(summaryList, targetFilePath);		
	}	
	private List<FastaObj> getDnaCountsFromFastaFile(String filePath) throws IOException
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
					obj.appendSequence(nextLine.trim());
				}
			}		
		}
		reader.close();	
		return list;
	}	
	private String GetTabDelimitedData(List<FastaObj> list)
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
	private void writeTabDelimitedFile(List<FastaObj> list, String filePath) throws IOException
	{		
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)));
		String fileContents = this.GetTabDelimitedData(list);
		writer.write(fileContents);
		writer.flush();
		writer.close();		
	}
	private void setCounts(String nextLine, FastaObj obj)
	{
		char[] cArray = nextLine.toUpperCase().toCharArray();
		Arrays.sort(cArray);
		String str = new String(cArray);
		int aCount = (str.lastIndexOf('A') - str.indexOf('A')) + 1;
		int gCount = (str.lastIndexOf('G') - str.indexOf('G')) + 1;
		int tCount = (str.lastIndexOf('T') - str.indexOf('T')) + 1;
		int cCount = (str.lastIndexOf('C') - str.indexOf('C')) + 1;	
		obj.incrementCounts(aCount, gCount, tCount, cCount);		
	}
	private String getSequenceId(String singleFastaLine)
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
	private void printDnaCounts(List<FastaObj> list)
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

