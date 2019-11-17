package lab08;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Consumer extends Thread
{	
	private final BlockingQueue<PrimeCounter> queue;
	private final AtomicLong numOfPrimesFound;
	private final AtomicLong numOfSearchedNumbers;	
	
	public Consumer(BlockingQueue<PrimeCounter> queue, AtomicLong numOfPrimesFound, AtomicLong numOfSearchedNumbers)
	{
		this.queue = queue;
		this.numOfPrimesFound = numOfPrimesFound;
		this.numOfSearchedNumbers = numOfSearchedNumbers;		
	}
	@Override
	 public void run(){	
	    	 try
	    	 {	    		 
	    		 while(true)
	    		 {
	    			PrimeCounter counter = this.queue.take();
	    			long startNum = counter.getStartNum();
	    			long endNum = counter.getEndNum();
	    			
	    			if(!this.hasToPerformBreak(startNum))
	    			{
	    				long primeNumbersFound = 0;
	    				for(long i = startNum; i <= endNum; i++)
	    				{
	    					if(this.isNumberPrime(i))
	    					{	    						
	    						primeNumbersFound++;	    						
	    					}	    					
	    				}
	    				this.updateCounts(primeNumbersFound, ((endNum - startNum) + 1));	    				
	    			}
	    			else
	    			{
	    				break;
	    			}		    			
	    		 }
	    	 }
	    	 catch(InterruptedException ex)
	    	 {
	    		 System.out.println(this.getName() + ":  " + "Exiting...Interrupted Exception");
	    		 ex.printStackTrace();
	    	 }
	    	 catch(Exception ex)
	    	 {
	    		 System.out.println(this.getName() + ":  " + "Exiting...General Exception");
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
	private boolean hasToPerformBreak(long startNumValue)
	{
		boolean performBreak = false;
		if(startNumValue == Constants.POISON_LONG)
		{
			System.out.println(this.getName() + ":  " + "Exiting...received poison");
			performBreak = true;
		}
		if(this.isInterrupted())
		{
			System.out.println(this.getName() + ":  " + "Exiting...soft interuption");
			performBreak = true;
			
		}
		return performBreak;
	}
}
