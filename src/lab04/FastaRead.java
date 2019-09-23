package lab04;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FastaRead
{
	public static void main(String[] args) throws Exception
	{	
		String shortSequenceFilePath = ".\\src\\lab04\\FastaInput.txt";		
		String resultShortSequenceFilePath = ".\\src\\lab04\\ShortSequenceResult.txt";			
		
		List<FastaSequence> fastaList = 
				FastaSequence.readFastaFile(shortSequenceFilePath);

				for( FastaSequence fs : fastaList)
				{
				System.out.println(fs.getHeader());
				System.out.println(fs.getSequence().trim());
				System.out.println(fs.getGCRatio());
				System.out.println();
				}
				
	//	test1();

	}	
	private static void test1()
	{
		String input = ">seq1 This is the description of my first sequence.\n" +
		 "GGTACGTAGTAGCTGCTGCTACGTGCGCTAGCTAGTACGTCA\n" + 
		 "CGACGTAGATGCTAGCTGACTCGATGCAAAAA";
		System.out.println(getHeader(input));
	}
	
	public static String getHeader(String sequence)
	{
		String result = "";					
		// Pattern p = Pattern.compile(">(\\s*\\w*)*\\n");	
		Pattern p = Pattern.compile(">(\\s*\\w*)*\\n");	
		Matcher matcher = p.matcher(sequence);		
		while(matcher.find())
		{
			int start =  matcher.start();			
			int end = matcher.end();			
			result = sequence.substring(start + 1, end).trim();
		}	
			
	return result;	
}
}
