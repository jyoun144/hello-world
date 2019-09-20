package lab04;
import java.util.Arrays;
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
		String header = null;
		return header;
	}
	public String getSequence()
	{		
		return this.sequence.toString();
	}
	public float getGCRatio()
	{		
		return (float)(gCount + cCount)/(float)(aCount + gCount + cCount + tCount);
	}
	public void appendSequence(String input)
	{
		if(input != null)
		{
			sequence.append(input);
		
			this.setCounts(input);
		}
	}
	private void setCounts(String nextLine)
	{
		char[] cArray = nextLine.toUpperCase().toCharArray();
		Arrays.sort(cArray);
		String str = new String(cArray);
		this.aCount += str.contains("A") ? ((str.lastIndexOf('A') - str.indexOf('A')) + 1) : 0;
		this.gCount += str.contains("G") ? (str.lastIndexOf('G') - str.indexOf('G')) + 1 : 0;
		this.tCount += str.contains("T") ? (str.lastIndexOf('T') - str.indexOf('T')) + 1 : 0;
		this.cCount = str.contains("C") ? (str.lastIndexOf('C') - str.indexOf('C')) + 1 : 0;		
	}
	private String getHeader(String singleFastaLine)
	{
		String result = "";
		if(singleFastaLine != null)
		{
			Pattern p = Pattern.compile(">\\s*\\w+");
			Matcher matcher = p.matcher(singleFastaLine);
			while(matcher.find())
			{
				int start =  matcher.start();			
				int end = matcher.end();			
				result = singleFastaLine.substring(start + 1, end).trim();
			}	
		}
		return result.replace(">", "");		
	}	
}
