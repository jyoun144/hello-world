package lab02;
import java.util.Random;

public class AminoAcidQuiz {	
	private final static String[] SHORT_NAMES = 
		{ "A","R", "N", "D", "C", "Q", "E", 
		"G",  "H", "I", "L", "K", "M", "F", 
		"P", "S", "T", "W", "Y", "V" };

		private static final String[] FULL_NAMES = 
		{
		"alanine","arginine", "asparagine", 
		"aspartic acid", "cysteine",
		"glutamine",  "glutamic acid",
		"glycine" ,"histidine","isoleucine",
		"leucine",  "lysine", "methionine", 
		"phenylalanine", "proline", 
		"serine","threonine","tryptophan", 
		"tyrosine", "valine"};
		

	public static void main(String[] args) 
	{
		Random random = new Random();
		int totalTestTimeInput = 30;
		long startTime = System.currentTimeMillis();
		String [][] aminoAcidSet = getAminoAcids();
		giveTest(totalTestTimeInput, aminoAcidSet, random);
		
		int r = 1;		
		
	}
	private static void giveTest(int totalTestTime, String [][] aminoAcidSet, Random random)
	{
		boolean isCorrectAnswer = false;
		boolean isTargetTimeElapsed = false;
		do {
			String [][] target = getTargetAminoAcid(random, aminoAcidSet);
			 System.out.println("Enter single letter system for " + target[0][1]);
			String input = System.console().readLine().toUpperCase();
			   // Statements
			}while(isCorrectAnswer == true && isTargetTimeElapsed == true);	
	}
	
	
	private static void printFinalReport(int totalSequenceCount, int targetCodonCount, String targetCodon)
	{
		/* 1) How often would you expect to see this 3mer by chance?
		 * The expected 3mer count (expected_count) for 1000 trials is 15.625:  expected_count = (1/4)^3 * 1000	
		 * 
		 * 2) Is Java's number close to the number you would expect?  
		 * Yes, the 3mer count for 'AAA' was within the neighborhood of 15.625 after executing the program several times.
		 */
		 System.out.println("\n The target sequence " + "(" + targetCodon + ")" + 
				 			" was generated " + targetCodonCount + " out of " + 
				 			totalSequenceCount + " times.");
	}
	private static String [][] getAminoAcids()
	{			
			int length = SHORT_NAMES.length;			
			String[][] aminoAcids = new String[length][2];
			for(int i=0; i<length;i++)
			{
				aminoAcids[i][0] = SHORT_NAMES[i];
				aminoAcids[i][1] = FULL_NAMES[i];
			}
			return aminoAcids;				
	}
	private static String [][] getTargetAminoAcid(Random rand, String [][] aminoAcids)
	{
		int index = rand.nextInt(aminoAcids.length);		
		String [][] target = new String[][] {{aminoAcids[index][0],aminoAcids[index][1]}};
		return target;		
		
	}	
}
