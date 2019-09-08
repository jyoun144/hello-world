package lab02;
import java.util.Random;

public class AminoAcidQuiz2V2 {	
	private static float maxTestTimeSeconds = 30f;
	private static int maxTestQuestions;
	private static boolean isMaxQuestionTest = false;
	private static boolean isMaxTimeTest = true;
	
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
		setTestParameters(args);
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
		boolean hasQuit = false;
		int score = 0;
		int attemptedQuestionCount = 0;
		do {			
			String [][] target = getTargetAminoAcid(random, aminoAcidSet);
			System.out.println("Enter the single letter symbol for " + target[0][1] + ".");
			String input = System.console().readLine().toUpperCase();
			float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;	
			if(input.equals("QUIT"))
			{
				hasQuit = true;	
				System.out.println("User has quit the current session");
			}
			else
			{
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
			}		
			
			}while(isCorrectAnswer == true && isTargetTimeElapsed == false  && hasQuit == false);	
		return score;
	}	
	private static String[][] getAminoAcids()
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
		String [][] target = new String[][] {{aminoAcids[index][0],aminoAcids[index][1]}};	;
		return target;		
	}	
	private static void setTestParameters(String[] args)
	{
		if(args.length == 2)
		{
			if(args[0].toUpperCase().equals("T"))
			{
				isMaxTimeTest = true;
				maxTestTimeSeconds = Float.parseFloat(args[1]);
			}
			else if(args[0].toUpperCase().equals("Q"))
			{
				isMaxQuestionTest = true;	
				maxTestQuestions = Integer.parseInt(args[1]);
			}
		}
	}
	
}
