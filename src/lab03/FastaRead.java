package lab03;

public class FastaRead
{
	public static void main(String[] args) throws Exception
	{	
		String shortSequenceFilePath = ".\\src\\lab03\\FastaInput.txt";		
		String resultShortSequenceFilePath = ".\\src\\lab03\\ShortSequenceResult.txt";			
		var obj = new FastaFileUtil();
		/* The following method reads data from an input text file containing multiple 
		 * FASTA nucleotide sequences, and then it writes a tab-delimited file that 
		 * summarizes nucleotide counts for each nucleotide sequence.
		 */
		obj.saveFastaSummaryReport(shortSequenceFilePath, resultShortSequenceFilePath);		
	}	
}
