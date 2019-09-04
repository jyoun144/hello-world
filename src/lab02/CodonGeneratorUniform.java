package lab02;
import java.util.Random;

public class CodonGeneratorUniform {	
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
		String [][] aa = getAminoAcids();
		int b = 1;
	      
		
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
}
