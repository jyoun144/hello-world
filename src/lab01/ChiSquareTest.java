package lab01;
import java.util.Random;

public class ChiSquareTest {		

	public static void main(String[] args) 
	{	
		float aFreq = 0.12f;
		float cFreq = 0.38f;
		float gFreq = 0.39f;
		float tFreq = 0.11f;		
		double significanceValue = 0.05;
		int numOfCategories = 2;		
		int inputSequenceCount = 10000;
		int inputTrialRuns = 10000;
		String targetCodon = "AAA";		 
		int totalSequenceCount = 0;	
	    int targetCodonCount = 0;
	    
	    for(int h=0; h<inputTrialRuns; h++)
	    {
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
	    }		
	    printChiSquareTestResults(significanceValue, targetCodon, aFreq, gFreq, tFreq, cFreq,  targetCodonCount, totalSequenceCount, numOfCategories - 1);
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
	private static double getExpectedCodonFrequency(String codon, float aFreq, float gFreq, float tFreq, float cFreq )
	{
		int codonLength = codon.length();
		double result = 1;
		
		for(int i=0; i< codonLength; i++)
		{
			float freq = 0f;
			char currentBase = codon.charAt(i);
			if(currentBase == 'A')
			{
				freq = aFreq;
			}
			else if(currentBase == 'G')
			{
				freq = gFreq;
			}
			else if(currentBase == 'T')
			{
				freq = tFreq;				
			}
			else if(currentBase == 'C')
			{
				freq = cFreq;
			}
			result *= freq;			
		}
		
		return result;
	}	
	private static void printChiSquareTestResults(double significanceValue, String codon, float aFreq, float gFreq, float tFreq, float cFreq, int actualTargetCodonCount, int totalSimulationCount, int degreesOfFreedom)
	{
		double codonFreq = getExpectedCodonFrequency(codon, aFreq, gFreq, tFreq, cFreq);
		double expectedCodonCount = codonFreq * totalSimulationCount;
		double expectedNoncondonCount = totalSimulationCount - expectedCodonCount;
		int actualNoncondonCount = totalSimulationCount - actualTargetCodonCount;
		double chiSquareValue = (Math.pow(actualTargetCodonCount - expectedCodonCount, 2)/expectedCodonCount) + 
				                (Math.pow(actualNoncondonCount - expectedNoncondonCount, 2)/expectedNoncondonCount);
		
		double pValue = ChiSquareUtils.pochisq(chiSquareValue, degreesOfFreedom);	
		 System.out.println("\nExpected count for the target codon (" + codon + ") is " + expectedCodonCount + 
				             "\nThe actual target codon count is " + actualTargetCodonCount +
				            "\nThe expected non-target codon count is " + expectedNoncondonCount +
				            "\nThe actual non-target codon count is " + actualNoncondonCount + 
				            "\nChi squared value is " + chiSquareValue +
				            "\nThe calculated p-Value is " + pValue);
		 if(pValue >= significanceValue )
		 {
			 System.out.println("Since p-value of " + pValue + " is greater than the significance value of " + significanceValue + " the null hypothesis cannot be rejected.");
		 }
		 else
		 {
			 System.out.println("Since p-value of " + pValue + " is less than the significance value of " + significanceValue + " the null hypothesis is rejected.");
		 }		
	}	
}
