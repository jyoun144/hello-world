package lab02;
import java.util.Random;

public class AminoAcidQuizPart2 
{	
	private final static String[] SHORT_NAMES = 
		{ 	"A","R", "N", "D", "C", "Q", "E", 
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
	private static float maxTestTimeSeconds;
	private static int maxTestQuestions;
	private static boolean isMaxQuestionTest;
	private static boolean isMaxTimeTest;

	public static void main(String[] args) 
	{		
		Random random = new Random();	
		long startTime = System.currentTimeMillis();
		String [][] aminoAcidSet = getAminoAcids();	
		setTestParameters(args);
		System.out.println("Quiz settings are the following:\n" +
				"isMaxTimeTest = " + isMaxTimeTest + "\n" +
		"isMaxQuestionTest = "  + isMaxQuestionTest + "\n" +
		"maxTestTimeSeconds = " + maxTestTimeSeconds +"\n" +
				"maxTestQuestions = " + maxTestQuestions );
		// int testScore = giveTest(totalTestTimeInput, aminoAcidSet, random, startTime);	
		// System.out.println("\nTest ends with score of " + testScore + ".*******");
	}	
	private static int giveTest(float totalTestTime, String [][] aminoAcidSet, Random random, long startTime)
	{
		boolean isCorrectAnswer = true;
		boolean isTargetTimeElapsed = false;
		int score = 0;
		int answerBankSize = aminoAcidSet.length;
		do {
			int index = random.nextInt(answerBankSize);
			String [][] target = new String[][] {{aminoAcidSet[index][0],aminoAcidSet[index][1]}};
			System.out.println("Enter the single letter symbol for " + target[0][1] + ".");
			String input = System.console().readLine().toUpperCase();
			float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;	
			if(elapsedTime <= totalTestTime)
			{
				if(target[0][0].equals(input))
				{
					score++;
					aminoAcidSet[index][3] = Integer.toString(Integer.parseInt(aminoAcidSet[index][3]) + 1);
					 System.out.println("right.  Score=" + score + " ; " + "seconds= " + elapsedTime + " out of " + totalTestTime);
				}
				else
				{
					isCorrectAnswer = false;
					aminoAcidSet[index][3] = Integer.toString(Integer.parseInt(aminoAcidSet[index][4]) + 1);
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
			String[][] aminoAcids = new String[length][4];
			for(int i=0; i<length;i++)
			{
				aminoAcids[i][0] = SHORT_NAMES[i];
				aminoAcids[i][1] = FULL_NAMES[i];
				aminoAcids[i][2] = "0";	
				aminoAcids[i][3] = "0";	
			}
			return aminoAcids;				
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
			else
			{
				setDefaultQuizValues();				
			}
		}
		else
		{
			setDefaultQuizValues();
		}
	}
	private static void setDefaultQuizValues()
	{
		isMaxTimeTest = true;
		isMaxQuestionTest = false;
		maxTestTimeSeconds = 30f;
		maxTestQuestions = 0;	
	}
}
