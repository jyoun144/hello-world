package lab03;
import java.util.regex.*;
import java.io.*;
public class FastaRead
{
	public static void main(String[] args) throws Exception
	{	
		readFile();
	}
	private static void regExTester() 
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
	private static void readFile() throws IOException
	{
		String targetFile = "C:\\Users\\young\\Documents\\UNCC_Fall_2019\\Advance_Programming\\Labs\\Lab03\\FastaInput.txt";
		Integer seqCount = 0;
		BufferedReader reader = new BufferedReader(new FileReader(new File(targetFile)));
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{
			if(nextLine.contains(">"))
			{
				System.out.println(nextLine);
				seqCount++;
			}
			// System.out.println(nextLine);
			
		}
		reader.close();
		System.out.println("Seq line count is:  " + seqCount);
	}
}
