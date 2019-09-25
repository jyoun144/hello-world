package lab04;

public class MainFastaParse
{
	private static final String defaultFilePath  = "C:\\Users\\young\\git\\hello-world\\src\\lab04\\FastaInput.txt";	
	
	/*
	 * Invoke this program via command line using the following syntax
	 * java lab04.MainFastaParse absolute_FASTA_file_path
	 * example:  "java lab04.MainFastaParse C:\Users\young\git\hello-world\src\lab04\FastaInput.txt"
	 */
	public static void main(String[] args) throws Exception
	{
		String targetFilePath = getTargetFilePath(args);
		navigateSequences(targetFilePath);			
	}	
	private static void navigateSequences(String targetFilePath) throws Exception
	{
		 FastaViewer view = new FastaViewer(targetFilePath);
		 String input;
		 FastaObj sequence = null;
		 do {
		 System.out.println("Enter 'N' to view next FASTA sequence, or enter 'Q' to terminate program.\n");
		 input = System.console().readLine().toUpperCase();			
		 if(input.equals("N"))
			{
				sequence = view.getNextSequence();
				if(sequence != null)
				{
					System.out.println(sequence.getHeader());
					System.out.println(sequence.getSequence());
					System.out.println();
				}
				else
				{
					System.out.println("Reached end of file.");				
				}
			}
			if(input.equals("Q"))
			{
				System.out.println("User has ended session.");
			}
		
		 }while(!input.equals("Q") && sequence != null);	
	}
	private static String getTargetFilePath(String[] args)
	{
		String targetFilePath = null;
		if(args.length == 1 && args[0].trim() != "")
		 {
			 targetFilePath = args[0];			 
		 }
		 else
		 {
			 targetFilePath = defaultFilePath;			 
		 }
		return targetFilePath;
	}
}
