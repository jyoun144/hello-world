package lab04;
import java.util.Arrays;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FastaSequence implements iFastaSequence
{
	private StringBuilder sequence;
	private int aCount = 0;
	private int gCount = 0;
	private int tCount = 0;
	private int cCount = 0;
	
	public FastaSequence()
	{
		this.sequence = new StringBuilder();		
	}
	public String getHeader()
	{
		String result = "";					
		Pattern p = Pattern.compile(">(\\s*\\w*)*\\n");		
		Matcher matcher = p.matcher(this.sequence);		
		while(matcher.find())
		{
			int start =  matcher.start();			
			int end = matcher.end();			
			result = this.sequence.substring(start + 1, end).trim();
		}	
			
	return result;	
	}
	public String getSequence()
	{	
		return this.sequence.toString();		
	}
	public float getGCRatio()
	{		
		return (float)(gCount + cCount)/(float)(aCount + gCount + cCount + tCount);
	}
	public void appendSequence(String sequence)
	{
		if(sequence != null && !sequence.trim().equals(""))
		{
			this.sequence.append(sequence);
			this.setCounts(sequence);
		}
	}
	public static void writeUniquewriteUnique writeUnique (File inFile, File outFile)
			throws Exception
			{
		
			}
	private void setCounts(String input)
	{
		if(input != null)
		{
			char[] cArray = input.toUpperCase().toCharArray();
			Arrays.sort(cArray);
			String str = new String(cArray);
			this.aCount += str.contains("A") ? ((str.lastIndexOf('A') - str.indexOf('A')) + 1) : 0;
			this.gCount += str.contains("G") ? (str.lastIndexOf('G') - str.indexOf('G')) + 1 : 0;
			this.tCount += str.contains("T") ? (str.lastIndexOf('T') - str.indexOf('T')) + 1 : 0;
			this.cCount += str.contains("C") ? (str.lastIndexOf('C') - str.indexOf('C')) + 1 : 0;
		}
	}	
}
