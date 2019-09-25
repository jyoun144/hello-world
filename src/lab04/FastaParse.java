package lab04;
import java.util.List;
import java.io.File;

public class FastaParse
{
	public static void main(String[] args) throws Exception
	{	
		 String targetFilePath = ".\\src\\lab04\\FastaInput.txt";
		for(int i = 0; i < 9; i++)
		{
			FastaViewer view = new FastaViewer(targetFilePath);
			var sequence1 = view.getNextSequence();
			System.out.println(sequence1.getHeader());
			System.out.println(sequence1.getSequence());			
		}		
	}	
	
}
