package lab04;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FastaObj
{
	private StringBuilder sequence;
	private String header = null;	
	
	public FastaObj(String header)
	{
		this.header = header;
		this.sequence = new StringBuilder();		
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
