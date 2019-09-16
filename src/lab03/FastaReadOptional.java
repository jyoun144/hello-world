package lab03;

public class FastaReadOptional
{
	public static void main(String[] args) throws Exception
	{	
		String shortSequenceFile = ".\\src\\lab03\\FastaInput.txt";			
		String resultShortSequenceFile = ".\\src\\lab03\\ShortSequenceResult.txt";		
		var obj = new FastaFileUtilOptional();
		obj.saveFastaSummaryReportOptional(shortSequenceFile, resultShortSequenceFile);	
	}	
}
