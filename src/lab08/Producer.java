package lab08;

import java.lang.StringBuilder;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.BlockingQueue;
import javax.swing.SwingUtilities;

public class Producer extends Thread
{	
	private static final String MSG_PREFIX_CANCELLED = "************PROCESSING CANCELLED************\n";
	private static final String MSG_PREFIX_PROCESSING = "************CALCULATIONS IN PROGRESS************\n";
	private static final String MSG_PREFIX_COMPLETED = "************PROCESSING COMPLETED************\n";
	private static final String MSG_PREFIX_INITIAL = "*******PERFORMING INITIAL CALCULATIONS*******\n";	
	private final long maxPrimeNumber;	
	private final MultiThreadSwingFrame frame;	
	private final AtomicLong numOfPrimesFound;
	private final AtomicLong numOfSearchedNumbers;
	private final BlockingQueue<PrimeCounter> queue;
	private final long numInterval;
	private final int numOfConsumerThreads;
	
	public Producer(MultiThreadSwingFrame frame, long maxPrimeNumber, BlockingQueue<PrimeCounter> queue, AtomicLong numOfPrimesFound, AtomicLong numOfSearchedNumbers, long numInterval, int numOfConsumerThreads)
	{		
		this.frame = frame;
		this.maxPrimeNumber = maxPrimeNumber;		
		this.queue = queue;
		this.numOfPrimesFound = numOfPrimesFound;
		this.numOfSearchedNumbers = numOfSearchedNumbers;
		this.numInterval = numInterval;
		this.numOfConsumerThreads = numOfConsumerThreads;
	}	
	
	@Override
	 public void run(){	
		long startTime = System.currentTimeMillis();
		ProcessState processState = ProcessState.INPROGRESS;
	    	 try
	    	 {     				
	    		 this.setOutputMessage(MSG_PREFIX_INITIAL);
    			// set output for every two seconds
    			long currentTime = (System.currentTimeMillis() - startTime)/2000L;
    			long lastTime = currentTime; 
    			 long endNumber ;    		
    			this.setOutputMessage(MSG_PREFIX_INITIAL);	    		
    			long i = 2L;
    			while(this.numOfSearchedNumbers.get() <  (this.maxPrimeNumber -1))    			
	    	     {    			
	    			 if(this.isInterrupted())
	    			 {
	    				 processState = ProcessState.CANCELLED;   				
	    				 System.out.println("Producer thread softly interrupted");
	    				 break;
	    			 }	 
	    			
	    		  if(i <=  this.maxPrimeNumber)
	    		  {	    			
	    			 endNumber = (i + this.numInterval);
	    			 endNumber = endNumber <= this.maxPrimeNumber ? endNumber : this.maxPrimeNumber;    				 
	    			 this.queue.put(new PrimeCounter(i, endNumber));
	    			 i = endNumber + 1L;   			
	    		  }	    			 
	    			 if(lastTime != currentTime)	    			
	    			 {
	    				 lastTime = currentTime;	    				 
	    				 this.setOutputMessage(this.getUpdateMessage(startTime, this.numOfSearchedNumbers.get(), this.maxPrimeNumber, this.numOfPrimesFound.get(), processState));
	    			 }	    			 
	    			// set output for every two seconds
	    			 currentTime = (System.currentTimeMillis() - startTime)/2000L;		    			
	    		
	    	     } // OUTER WHILE
    			this.toggleRunButtons(true);	    
    			this.putMultiplePoisonItems(this.numOfConsumerThreads);    			
	    		processState = processState.equals(ProcessState.INPROGRESS) ? ProcessState.COMPLETED : processState;
	    		this.setOutputMessage(this.getUpdateMessage(startTime, this.numOfSearchedNumbers.get(), this.maxPrimeNumber, this.numOfPrimesFound.get(), processState));		
	    	 }	 
	    	 catch(InterruptedException ie)
	    	 {
	    		 try {
	    			 System.out.println(this.getName() + " is within InterruptedException block");
	    			 	this.putMultiplePoisonItems(this.numOfConsumerThreads); 			 	
	    		 	 }
	    		 catch(Exception e)
	    		 {
	    			 System.out.println("An exception occurred while attempting to place poison item(s) in queue to ");
	    			 e.printStackTrace();	    			 
	    		 }
	    		 this.setOutputMessage(this.getUpdateMessage(startTime, this.numOfSearchedNumbers.get(), this.maxPrimeNumber, this.numOfPrimesFound.get(), ProcessState.CANCELLED));		    		
	    		 this.toggleRunButtons(true);
	    		 
	    	 }
	    	 catch(Exception ex)
	    	 {
	    		 try {
	    			 	System.out.println(this.getName() + " is within 'General' Exception block");
	    			 	this.putMultiplePoisonItems(this.numOfConsumerThreads);	    			 	
	    		 	 }
	    		 catch(Exception e)
	    		 {
	    			 System.out.println("An exception occurred while attempting to place poison item(s) in queue to ");
	    			 e.printStackTrace();	    			 
	    		 }
	    		 this.setOutputMessage("An exception occurred within the worker thread: " + ex.getMessage());    		
	    		 this.toggleRunButtons(true);
	    	 }     
	   }	
	private void setOutputMessage(String text)
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	frame.setMessage(text + "\n");   
            }
        });
	}
	private void toggleRunButtons(boolean isReadyState)
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	frame.toggleRunButtons(isReadyState);                         
            }
        });
	}	
	private String getUpdateMessage(long startTime, long currentPrimeSearchCount, long maxPrime, long count, ProcessState processState)
	{
		StringBuilder sb = new StringBuilder();
		currentPrimeSearchCount++;
		if(processState.equals(ProcessState.CANCELLED))
		{
			sb.append(MSG_PREFIX_CANCELLED);
		}
		else if(processState.equals(ProcessState.INPROGRESS))
		{
			sb.append(MSG_PREFIX_PROCESSING);
		}
		else
		{
			sb.append(MSG_PREFIX_COMPLETED);			
		}
		sb.append("Prime Number Count [2, ").
		   append("(").
		   append(currentPrimeSearchCount).
		   append("/").
		   append(maxPrime).
		   append(")").
		   append("] ---> ").
		   append(count).
		   append("\n").
		   append("Processing Time (seconds) ---> ").
		   append( String.format("%.2f", (System.currentTimeMillis() - startTime)/1000F));	
		return sb.toString();
	}	
	private void putMultiplePoisonItems(int n) throws InterruptedException
	{
		for(int i = 0; i < n; i++)
		{
			this.queue.put(new PrimeCounter(Constants.POISON_LONG, Constants.POISON_LONG));		
			System.out.println("Put poison item in queue");
		}		
	}
	
	private enum ProcessState {INPROGRESS, CANCELLED, COMPLETED}
}
