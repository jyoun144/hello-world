package lab04;
import java.util.List;
import java.io.File;

public class MainFastaRead
{
	public static void main(String[] args) throws Exception
	{	
		String targetFilePath = "C:\\Users\\young\\git\\hello-world\\src\\lab04\\FastaInput.txt";			
		String sequenceSummaryFilePath = "C:\\Users\\young\\git\\hello-world\\src\\lab04\\UniqueSequenceCountResult.txt";		
		List<FastaSequence> fastaList = 
				FastaSequence.readFastaFile(targetFilePath);
				for( FastaSequence fs : fastaList)
				{
					System.out.println(fs.getHeader());
					System.out.println(fs.getSequence().trim());
					System.out.println(fs.getGCRatio());
					System.out.println();
				}
				
		FastaSequence.writeUnique(new File(targetFilePath), new File(sequenceSummaryFilePath));
	}	
}
