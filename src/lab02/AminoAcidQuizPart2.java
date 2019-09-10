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
		giveTest(maxTestTimeSeconds, aminoAcidSet, random, startTime);	
		getTestSummaryResults(aminoAcidSet);
	}	
	private static void giveTest(float totalTestTime, String [][] aminoAcidSet, Random random, long startTime)
	{		
		boolean isTargetTimeElapsed = false;
		boolean hasAskedAllQuestions = false;
		boolean hasAnsweredQuestionInTime = true;
		boolean hasQuit = false;
		int answerBankSize = aminoAcidSet.length;		
		int askedQuestionCount = 0;		
		do {
			int index = random.nextInt(answerBankSize);
			String [][] target = new String[][] {{aminoAcidSet[index][0],aminoAcidSet[index][1]}};
			System.out.println("Enter the single letter symbol for " + target[0][1] + ".");
			askedQuestionCount++;
			String input = System.console().readLine().toUpperCase();
			if(input.equals("QUIT"))
			{
				hasQuit = true;	
				System.out.println("User has quit the current session");
			}
			else
			{
				if(isMaxTimeTest)
				{					
					isTargetTimeElapsed = ((System.currentTimeMillis() - startTime)/1000f > maxTestTimeSeconds);
					if(isTargetTimeElapsed)
					{
						hasAnsweredQuestionInTime = false;
						System.out.println("Exceeded test time of " + totalTestTime + " seconds.");					
					}				
				}
				else
				{
					hasAskedAllQuestions = !(askedQuestionCount < maxTestQuestions);				
				}
				if(hasAnsweredQuestionInTime)
				{
					if(target[0][0].equals(input))
					{
						// Increment correct answer count for current amino acid
						aminoAcidSet[index][2] = Integer.toString(Integer.parseInt(aminoAcidSet[index][2]) + 1);
						 System.out.println("right.");
					}
					else
					{
						// Increment incorrect answer count for current amino acid
						aminoAcidSet[index][3] = Integer.toString(Integer.parseInt(aminoAcidSet[index][3]) + 1);
						System.out.println("WRONG.  Correct answer is " + target[0][0] + ".");	
					}				
				}
			}
		}while(!hasQuit && ((isMaxTimeTest && !isTargetTimeElapsed) || (isMaxQuestionTest && !hasAskedAllQuestions)));		
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
	private static void getTestSummaryResults(String [][] aminoAcidSet)
	{
		int correctAnswers = 0;
		int incorrectAnswers = 0;		
		for(int i=0; i<aminoAcidSet.length; i++)
		{
			correctAnswers += Integer.parseInt(aminoAcidSet[i][2]);
			incorrectAnswers += Integer.parseInt(aminoAcidSet[i][3]);			
		}	
		System.out.println("Correct Answers:  " + correctAnswers + "\n" +
		                   "Incorrect Answers:  " + incorrectAnswers + "\n");
	}	
}
