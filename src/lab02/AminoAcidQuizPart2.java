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
/*
 * By passing in two parameters, user can configure two types of quiz modes:  timed or number of questions.
 * User can input 'quit' at any time to terminate the quiz
 * See the following examples to invoke each mode.
 * 'java lab02.AminoAcidQuizPart2 t 30' (user will provide responses over 30 seconds)
 * java lab02.AminoAcidQuizPart2 q 10' (user will answer 10 questions)
 */
	public static void main(String[] args) 
	{	
		Random random = new Random();	
		long startTime = System.currentTimeMillis();
		String [][] aminoAcidSet = getAminoAcids();	
		setTestParameters(args);
		Integer [][] tracker = getAnswerTracker(aminoAcidSet.length);		
		giveTest(aminoAcidSet, random, startTime, tracker);	
		getTestSummaryResults(tracker);
		printFinalReport(aminoAcidSet, tracker);
	}	
	private static void giveTest(String [][] aminoAcidSet, Random random, long startTime, Integer [][] tracker)
	{		
		boolean isTargetTimeElapsed = false;
		boolean hasAskedAllQuestions = false;
		boolean hasAnsweredQuestionInTime = true;
		boolean hasQuit = false;
		int answerBankSize = aminoAcidSet.length;		
		int askedQuestionCount = 0;	
		int score = 0;	
		do {
			float elapsedTimeInSeconds = (System.currentTimeMillis() - startTime)/1000f;
			int index = random.nextInt(answerBankSize);
			String [][] target = new String[][] {{aminoAcidSet[index][0],aminoAcidSet[index][1]}};
			System.out.println("Enter the single letter symbol for " + target[0][1] + ".");
			askedQuestionCount++;
			String input = System.console().readLine().toUpperCase();
			if(input.equals("QUIT"))
			{
				hasQuit = true;	
				System.out.println("User has quit the current session.\n");
			}
			else
			{
				if(isMaxTimeTest)
				{					
					isTargetTimeElapsed = (elapsedTimeInSeconds > maxTestTimeSeconds);
					if(isTargetTimeElapsed)
					{
						hasAnsweredQuestionInTime = false;
						System.out.println("Exceeded test time of " + maxTestTimeSeconds + " seconds.");					
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
						tracker[index][0] += 1;
						score++;
						String message = isMaxTimeTest ?  "seconds= " + elapsedTimeInSeconds + " out of " + maxTestTimeSeconds : 
							"completed " + askedQuestionCount + " out of " + maxTestQuestions + " questions";
						 System.out.println("right.  " + "Score=" + score + " ; " + message + ".\n");
					}
					else
					{
						// Increment incorrect answer count for current amino acid
						tracker[index][1] += 1;
						System.out.println("WRONG.  Correct answer is " + target[0][0] + ".\n");	
					}				
				}
			}
		}while(!hasQuit && ((isMaxTimeTest && !isTargetTimeElapsed) || (isMaxQuestionTest && !hasAskedAllQuestions)));		
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
	private static Integer [][] getAnswerTracker(int numOfRows)
	{				
			Integer[][] answerTracker = new Integer[numOfRows][2];
			for(int i=0; i<numOfRows;i++)
			{
				answerTracker[i][0] = 0;
				answerTracker[i][1] = 0;				
			}
			return answerTracker;				
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
	private static void getTestSummaryResults(Integer [][] answerTracker)
	{
		int correctAnswers = 0;
		int incorrectAnswers = 0;		
		for(int i=0; i<answerTracker.length; i++)
		{
			correctAnswers += answerTracker[i][0];
			incorrectAnswers += answerTracker[i][1];			
		}	
		System.out.println("Correct Answers:  " + correctAnswers + "\n" +
		                   "Incorrect Answers:  " + incorrectAnswers + "\n");
	}
	private static void printFinalReport(String[][] aminoAcidSet, Integer[][] answerTracker)
	{	
		int correctCount = 0;
		int incorrectCount = 0;
	  System.out.printf( "%-15s %10s %10s %n", "Amino Acid", "# correct", "# wrong");
	  for(int i=0; i<aminoAcidSet.length;i++)
	  {
		  if(answerTracker[i][0]  > 0 || answerTracker[i][1] > 0)
		  {
			  correctCount += answerTracker[i][0];
			  incorrectCount += answerTracker[i][1];
			  System.out.printf( "%-15s %10s %10s %n", aminoAcidSet[i][1], answerTracker[i][0], answerTracker[i][1]);
		  }		
	  }
	  System.out.println("------------------------------");
	  System.out.printf( "%-15s %10s %10s %n", "Total", correctCount, incorrectCount);
	}
}
