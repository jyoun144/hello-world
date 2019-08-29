package test;
import java.util.Random;

public class HelloWorld {
	private static Random random = new Random();
	private static float aFreq = 0.25f;
	private static float gFreq = 0.25f;
	private static float tFreq = 0.25f;
	private static float cFreq = 0.25f;	
	private static int totalSequenceCount = 1000;
	private static final String targetCodon = "AAA";	

	public static void main(String[] args) 
	{
		int sequenceCount = 0;	
	    int targetCodonCount = 0;
		for(int i=0; i<totalSequenceCount; i++)
		{
	     String currentCodon = GetCodon();
	     System.out.println(currentCodon);
	     if(currentCodon.equals(targetCodon))
	     {
	    	 targetCodonCount = targetCodonCount + 1;	    	    	 
	     }		
		 sequenceCount = sequenceCount + 1;	
		}
		PrintFinalReport(sequenceCount, targetCodonCount, targetCodon);	    
	}	
	private static String GetCodon()
	{
		String codon = "";
		for(int i = 0; i < 3; i++)
		{
			codon += GetNucleoTide(random.nextFloat());
		}
		return codon;
	}	
	private static String GetNucleoTide(float input)
	{
		String nucleotide = null;
		if( input >= 0 && input <= aFreq)
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
	private static void PrintFinalReport(int totalSequenceCount, int targetCodonCount, String targetCodon)
	{
		// Expected target codon count for 1000 trials is 15.625
		// System.out.println("**************************************************");
		 System.out.println("\n The target sequence " + "(" + targetCodon + ")" + 
				 			" was generated " + targetCodonCount + " out of " + 
				 			totalSequenceCount + " times.");
	}
	
}
