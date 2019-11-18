package lab08;

import java.util.concurrent.atomic.AtomicLong;

public class ConsumerService extends Thread
{	
	private final AtomicLong numOfPrimesFound;
	private final AtomicLong numOfSearchedNumbers;
	private final PrimeCounter counter;
	
	public ConsumerService(AtomicLong numOfPrimesFound, AtomicLong numOfSearchedNumbers, PrimeCounter counter)
	{		
		this.numOfPrimesFound = numOfPrimesFound;
		this.numOfSearchedNumbers = numOfSearchedNumbers;	
		this.counter = counter;
	}
	@Override
	 public void run()
	{	
		 try
		 {
			 long startNum = counter.getStartNum();
			 long endNum = counter.getEndNum();   			
			 long primeNumbersFound = 0;
			 for(long i = startNum; i <= endNum; i++)
			 {
				 if(this.isInterrupted())
				 {
					 //System.out.println(this.getName() + ":  " + "Exiting...soft interuption");
					 System.out.println("Working thread Exiting...soft interuption");
					 break;
				 }	    					
				 if(this.isNumberPrime(i))
				 {	    						
					 primeNumbersFound++;	    						
				 }	    					
			 }
					this.updateCounts(primeNumbersFound, ((endNum - startNum) + 1));	 
		 }	    	 
		 catch(Exception ex)
		 {
			 // System.out.println(this.getName() + ":  " + "Exiting...General Exception");
			 System.out.println("Working thread within general exception block");
			 ex.printStackTrace();
		 }
	}
	private boolean isNumberPrime(long n)
	{
		boolean result = true;
		long floor = n/Constants.LONG_2;		
		for(long i = 2; i <= floor; i++)
		{
			if ((i != n) && (n % i) == Constants.LONG_0)
			{
				result = false;
				break;
			}
		}
		return result;
	}
	private void updateCounts(long primeFoundCnt, long searchNumCnt)
	{
		this.numOfPrimesFound.addAndGet(primeFoundCnt);
		this.numOfSearchedNumbers.addAndGet(searchNumCnt);
	}
}
