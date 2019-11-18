package lab08;

import java.lang.StringBuilder;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.BlockingQueue;
import javax.swing.SwingUtilities;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

public class SwingUpdater extends Thread
{	
	private static final String MSG_PREFIX_CANCELLED = "************PROCESSING CANCELLED************\n";
	private static final String MSG_PREFIX_PROCESSING = "************CALCULATIONS IN PROGRESS************\n";
	private static final String MSG_PREFIX_COMPLETED = "************PROCESSING COMPLETED************\n";
	private static final String MSG_PREFIX_INITIAL = "*******PERFORMING INITIAL CALCULATIONS*******\n";	
	private final long maxPrimeNumber;	
	private final MultiThreadSwingFrame frame;	
	private final AtomicLong numOfPrimesFound;
	private final AtomicLong numOfSearchedNumbers;	
	private final long numInterval;	
	private final ExecutorService workerPool;
	
	public SwingUpdater(MultiThreadSwingFrame frame, long maxPrimeNumber, AtomicLong numOfPrimesFound, AtomicLong numOfSearchedNumbers, long numInterval, ExecutorService workerPool)
	{		
		this.frame = frame;
		this.maxPrimeNumber = maxPrimeNumber;		
		this.numOfPrimesFound = numOfPrimesFound;
		this.numOfSearchedNumbers = numOfSearchedNumbers;
		this.numInterval = numInterval;		
		this.workerPool = workerPool;
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
    			List<Future<?>> list = new ArrayList<>();
    			while(this.numOfSearchedNumbers.get() <  (this.maxPrimeNumber -1))    			
	    	     {    			
	    			 if(this.isInterrupted())
	    			 {
	    				 processState = ProcessState.CANCELLED;   				
	    				 System.out.println("Producer thread softly interrupted");	 
	    				 for(var item : list)
	    				 {
	    					 item.cancel(true);
	    				 }
	    				 break;
	    			 }	 
	    			
	    		  if(i <=  this.maxPrimeNumber)
	    		  {	    			
	    			 endNumber = (i + this.numInterval);
	    			 endNumber = endNumber <= this.maxPrimeNumber ? endNumber : this.maxPrimeNumber;  
	    			 list.add(workerPool.submit(new ConsumerService(this.numOfPrimesFound, this.numOfSearchedNumbers, new PrimeCounter(i, endNumber))));
	    			 //workerPool.execute(new ConsumerService(this.numOfPrimesFound, this.numOfSearchedNumbers, new PrimeCounter(i, endNumber)));
	    			 
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
	    		processState = processState.equals(ProcessState.INPROGRESS) ? ProcessState.COMPLETED : processState;
	    		this.setOutputMessage(this.getUpdateMessage(startTime, this.numOfSearchedNumbers.get(), this.maxPrimeNumber, this.numOfPrimesFound.get(), processState));		
	    	 }	    	 
	    	 catch(Exception ex)
	    	 {
	    		 try {
	    			    this.workerPool.shutdown();
	    			 	System.out.println(this.getName() + " is within 'General' Exception block");
	    			 	 ex.printStackTrace();
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
	
	private enum ProcessState {INPROGRESS, CANCELLED, COMPLETED}
}
