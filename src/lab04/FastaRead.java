package lab04;
import java.util.List;
import java.io.File;

public class FastaRead
{
	public static void main(String[] args) throws Exception
	{	
		String shortSequenceFilePath = ".\\src\\lab04\\FastaInput.txt";		
		String resultShortSequenceFilePath = ".\\src\\lab04\\UniqueSequenceCountResult.txt";	
		
		List<FastaSequence> fastaList = 
				FastaSequence.readFastaFile(shortSequenceFilePath);
				for( FastaSequence fs : fastaList)
				{
					System.out.println(fs.getHeader());
					System.out.println(fs.getSequence().trim());
					System.out.println(fs.getGCRatio());
					System.out.println();
				}
				
FastaSequence.writeUniquewriteUnique(new File(shortSequenceFilePath), new File(resultShortSequenceFilePath));
	}	
}
