package lab03;
import java.util.regex.*;
public class FastaRead
{
	public static void main(String[] args) 
	{	
		Pattern p = Pattern.compile("seq\\w*<");
		String s = "dfgdhdhseq22<";		
		Matcher matcher = p.matcher(s);
		while(matcher.find())
		{
			System.out.println("Start index: " + matcher.start());
			System.out.println("End index: " + matcher.end());
		}		
	}
}
