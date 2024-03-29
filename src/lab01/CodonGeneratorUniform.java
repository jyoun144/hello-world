package lab01;
import java.util.Random;

public class CodonGeneratorUniform {		

	public static void main(String[] args) 
	{		
		float aFreq = 0.25f;
		float gFreq = 0.25f;
		float tFreq = 0.25f;
		float cFreq = 0.25f;		
		int inputSequenceCount = 1000;
		String targetCodon = "AAA";		
		int totalSequenceCount = 0;	
	    int targetCodonCount = 0;
	    Random random = new Random();
	    
		for(int i=0; i<inputSequenceCount; i++)
		{
	     String currentCodon = getCodon(random, aFreq, gFreq, tFreq, cFreq);
	     System.out.println(currentCodon);
	     if(currentCodon.equals(targetCodon))
	     {
	    	 targetCodonCount = targetCodonCount + 1;	    	    	 
	     }		
		 totalSequenceCount = totalSequenceCount + 1;	
		}
		printFinalReport(totalSequenceCount, targetCodonCount, targetCodon);	    
	}	
	private static String getCodon(Random random, float aFreq, float gFreq, float tFreq, float cFreq)
	{
		String codon = "";
		for(int i = 0; i < 3; i++)
		{
			codon += getNucleotide(random.nextFloat(), aFreq, gFreq, tFreq, cFreq);
		}
		return codon;
	}	
	private static String getNucleotide(float input, float aFreq, float gFreq, float tFreq, float cFreq)
	{
		String nucleotide = null;
		if( input > 0 && input <= aFreq)
		{
			nucleotide = "A";
		}
		else if( input > aFreq && input <= (aFreq + gFreq))
		{
			nucleotide = "G";
		}
		else if( input > (aFreq + gFreq) && input <= (aFreq + gFreq + tFreq))
		{
			nucleotide = "T";
		}
		else if( input > (aFreq + gFreq + tFreq) && input <= (aFreq + gFreq + tFreq + cFreq))
		{
			nucleotide = "C";
		}
		else
		{
			nucleotide = "X";
		}		
		
		return nucleotide;
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
}
