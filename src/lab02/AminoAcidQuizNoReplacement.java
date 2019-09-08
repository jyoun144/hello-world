package lab02;
import java.util.Random;

public class AminoAcidQuizNoReplacement {	
	private final static String[] SHORT_NAMES = 
		{ "A","R", "N", "D", "C", "Q", "E", 
		"G",  "H", "I", "L", "K", "M", "F", 
		"P", "S", "T", "W", "Y", "V" 
		};

		private static final String[] FULL_NAMES = 
		{
		"alanine","arginine", "asparagine", 
		"aspartic acid", "cysteine",
		"glutamine",  "glutamic acid",
		"glycine" ,"histidine","isoleucine",
		"leucine",  "lysine", "methionine", 
		"phenylalanine", "proline", 
		"serine","threonine","tryptophan", 
		"tyrosine", "valine"
		};	

	public static void main(String[] args) 
	{		
		Random random = new Random();
		int totalTestTimeInput = 30;
		long startTime = System.currentTimeMillis();
		String [][] aminoAcidSet = getAminoAcids();	
		int testScore = giveTest(totalTestTimeInput, aminoAcidSet, random, startTime);	
		System.out.println("\nTest ends with score of " + testScore);
	}	
	private static int giveTest(int totalTestTime, String [][] aminoAcidSet, Random random, long startTime)
	{
		boolean isCorrectAnswer = true;
		boolean isTargetTimeElapsed = false;
		int score = 0;
		do {
			int availableAnswerCount = getAvailableAnswerCount(aminoAcidSet);
			String [][] target = getTargetAminoAcid(random, aminoAcidSet, availableAnswerCount);
			System.out.println("Enter the single letter symbol for " + target[0][1] + ".");
			String input = System.console().readLine().toUpperCase();
			float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;	
			if(elapsedTime <= 30f)
			{
				if(target[0][0].equals(input))
				{
					score++;					
					 System.out.println("right.  Score=" + score + " ; " + "seconds= " + elapsedTime + " out of " + totalTestTime);
				}
				else
				{
					isCorrectAnswer = false;
					System.out.println("WRONG.  Correct answer is " + target[0][0] + ".");				
				}
			}
			else
			{
				isTargetTimeElapsed = true;
				System.out.println("Exceeded test time of " + totalTestTime + " seconds.");
			}
			
			}while(isCorrectAnswer == true && isTargetTimeElapsed == false);	
		return score;
	}	
	private static String [][] getAminoAcids()
	{			
			int length = SHORT_NAMES.length;			
			String[][] aminoAcids = new String[length][3];
			for(int i=0; i<length;i++)
			{
				aminoAcids[i][0] = SHORT_NAMES[i];
				aminoAcids[i][1] = FULL_NAMES[i];
				aminoAcids[i][2] = "A";
			}
			return aminoAcids;				
	}
	private static int getAvailableAnswerCount(String [][] answers)
	{
		int count = 0;
		for(int i=0; i < answers.length; i++)
		{
			if(answers[i][2].equals("A"))
			{
				count++;
			}
		}
		return count;
	}	
	private static String [][] getTargetAminoAcid(Random rand, String [][] aminoAcids, int availableAnswerCount)
	{
		int cnt = 0;
		String [][] target = null;
		int index = rand.nextInt(availableAnswerCount);
		for(int i =0; i<aminoAcids.length; i++)
		{
			if(aminoAcids[i][2].equals("A"))
			{
				if(cnt == index)
				{
					aminoAcids[i][2] = "U";
					target = new String[][] {{aminoAcids[i][0],aminoAcids[i][1]}};					
					break;				
				}
				else
				{
					cnt++;
				}				
			}
		}		
		return target;		
	}	
}
