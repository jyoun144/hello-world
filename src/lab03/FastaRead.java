package lab03;
import java.util.regex.*;
import java.io.*;
import java.util.*;

public class FastaRead
{
	public static void main(String[] args) throws Exception
	{	
		// readFile();
		/* String fastaFilePath = "C:\\Users\\young\\Documents\\UNCC_Fall_2019\\Advance_Programming\\Labs\\Lab03\\FastaInput.txt";
		var fastaUtil = new FastaFileUtil();
		var nucleotideCounts = fastaUtil.getDnaCountsFromFastaFile(fastaFilePath);
		fastaUtil.printDnaCounts(nucleotideCounts);
		var printDate = fastaUtil.GetTabDelimitedData(nucleotideCounts);
		int j = 1;
		*/
		// regExTester();
		 getCounts();
	}
	private static void getCounts()
	{
		// 4 A's; 3 G's; 3 C's; 2 T's
		String sss = "CATGAGCTAGAC";	      
		var ca = sss.toCharArray();
		Arrays.sort(ca);
		String str = new String(ca);
		System.out.println("First index of A: " + str.indexOf('A') + " Last index of A: " + str.lastIndexOf('A'));
		System.out.println("First index of G: " + str.indexOf('G') + " Last index of G: " + str.lastIndexOf('G'));
		System.out.println("First index of C: " + str.indexOf('C') + " Last index of C: " + str.lastIndexOf('C'));
		System.out.println("First index of T: " + str.indexOf('T') + " Last index of T: " + str.lastIndexOf('T'));
		/* Output
		 *  First index of A: 0 Last index of A: 3
			First index of G: 7 Last index of A: 9
			First index of C: 4 Last index of A: 6
			First index of T: 10 Last index of A: 11		
		 */
	}
	private static void regExTester() 
	{
		//Pattern p = Pattern.compile("seq\\w*<");
		//String s = "dfgdhdhseq22<";
		Pattern p = Pattern.compile(">\\s*\\w+");
		String s = "    >            P01013          GENE X PROTEIN (OVALBUMIN-RELATED)";
		Matcher matcher = p.matcher(s);
		while(matcher.find())
		{
			int start =  matcher.start();
			int end = matcher.end();
			System.out.println("Start index: " + start);
			System.out.println("End index: " + end);
			System.out.println(s.substring(start + 1, end).trim());
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
