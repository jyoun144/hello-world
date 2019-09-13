package lab03;
import java.util.regex.*;
import java.io.*;
import java.util.*;
public class FastaRead
{
	public static void main(String[] args) throws Exception
	{	
		// readFile();
		String fastaFilePath = "C:\\Users\\young\\Documents\\UNCC_Fall_2019\\Advance_Programming\\Labs\\Lab03\\FastaInput.txt";
		var fastaUtil = new FastaFileUtil();
		var nucleotideCounts = fastaUtil.getDnaCountsFromFastaFile(fastaFilePath);
		fastaUtil.printDnaCounts(nucleotideCounts);
		var printDate = fastaUtil.GetTabDelimitedData(nucleotideCounts);
		int j = 1;
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
		List<FastaObj> list = new ArrayList<FastaObj>();
		FastaObj obj = null;
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{	
			if(nextLine.contains(">"))
			{
				var num = nextLine.indexOf(' ');				
				obj = new FastaObj(nextLine.substring(1, num));
				list.add(obj);				
				System.out.println(nextLine.substring(1, num));
				seqCount++;
			}
			else
			{				
				for(int i=0; i<nextLine.length(); i++)
				{
					int aCount = 0;
					int gCount = 0;
					int tCount = 0;
					int cCount = 0;
					if(Character.toUpperCase(nextLine.charAt(i)) == 'A')
					{
						aCount++;
					}
					else if(Character.toUpperCase(nextLine.charAt(i)) == 'G')
					{
						gCount++;
					}
					else if(Character.toUpperCase(nextLine.charAt(i)) == 'T')
					{
						tCount++;
					}
					else if(Character.toUpperCase(nextLine.charAt(i)) == 'C')
					{
						cCount++;
					}
					obj.incrementCounts(aCount, gCount, tCount, cCount);
				}				
			}
			
		}
		reader.close();			
	}
}
