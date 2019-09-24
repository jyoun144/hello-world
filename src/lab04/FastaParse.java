package lab04;
import java.util.List;
import java.io.File;

public class FastaParse
{
	public static void main(String[] args) throws Exception
	{	
		
		runLab4Part1();
	}	
	private static void runLab4Part1() throws Exception
	{
		// String targetFilePath = ".\\src\\lab04\\FastaInput.txt";	
				String targetFilePath = ".\\src\\lab04\\CytBDNA.txt";		
				String sequenceSummaryFilePath = ".\\src\\lab04\\UniqueSequenceCountResult.txt";	
				
				List<FastaSequence> fastaList = 
						FastaSequence.readFastaFile(targetFilePath);
						for( FastaSequence fs : fastaList)
						{
							System.out.println(fs.getHeader());
							System.out.println(fs.getSequence().trim());
							System.out.println(fs.getGCRatio());
							System.out.println();
						}
						
		FastaSequence.writeUniquewriteUnique(new File(targetFilePath), new File(sequenceSummaryFilePath));
	}
}
