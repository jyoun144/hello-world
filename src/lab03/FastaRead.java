package lab03;

public class FastaRead
{
	public static void main(String[] args) throws Exception
	{	
		String sourceFilePath1 = "C:\\Users\\young\\Documents\\UNCC_Fall_2019\\Advance_Programming\\Labs\\Lab03\\FastaInput.txt";
		String sourceFilePath2 = "C:\\Users\\young\\Documents\\UNCC_Fall_2019\\Advance_Programming\\Labs\\Lab03\\CytBDNA.txt";
		String targetFilePath = "C:\\Users\\young\\Documents\\Lab3\\result.txt";
		var obj = new FastaFileUtil();
		obj.saveFastaSummaryReport(sourceFilePath2, targetFilePath);		
	}	
}
