package lab08;

import java.lang.StringBuilder;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.BlockingQueue;

import javax.swing.SwingUtilities;

public class Producer extends Thread
{
	private static final long LONG_NUM_2 = 2;
	private static final long LONG_NUM_0 = 0;
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
	
	public Producer(MultiThreadSwingFrame frame, long maxPrimeNumber, BlockingQueue<PrimeCounter> queue, AtomicLong numOfPrimesFound, AtomicLong numOfSearchedNumbers, long numInterval)
	{		
		this.frame = frame;
		this.maxPrimeNumber = maxPrimeNumber;		
		this.queue = queue;
		this.numOfPrimesFound = numOfPrimesFound;
		this.numOfSearchedNumbers = numOfSearchedNumbers;
		this.numInterval = numInterval;
	}
	
	@Override
	 public void run(){	
	    	 try
	    	 {
	    		ProcessState processState = ProcessState.INPROGRESS;
    			long count = 0;
    			long startTime = System.currentTimeMillis();
    			// set output for every two seconds
    			long currentTime = (System.currentTimeMillis() - startTime)/2000L;
    			long lastTime = currentTime;    			
    			long i;
    			this.setOutputMessage(MSG_PREFIX_INITIAL);
    			
	    		 for(i = 2; i <= this.maxPrimeNumber; this.getNextNumber(i, this.numInterval))
	    	     {	    			 
	    			 if(this.isInterrupted())
	    			 {
	    				 processState = ProcessState.CANCELLED;
	    				 break;
	    			 }	 
	    			
	    			 long endNumber = (i + this.numInterval);
	    			 endNumber = endNumber <= this.numInterval ? endNumber : this.maxPrimeNumber;
	    			 this.queue.put(new PrimeCounter(i, endNumber));
	    			 if(lastTime != currentTime)	    			
	    			 {
	    				 lastTime = currentTime;	    				 
	    				 this.setOutputMessage(this.getUpdateMessage(startTime, i, this.maxPrimeNumber, count, processState));
	    			 }	    			 
	    			// set output for every two seconds
	    			 currentTime = (System.currentTimeMillis() - startTime)/2000L;		    		
	    	     }
	    		 processState = processState.equals(ProcessState.INPROGRESS) ? ProcessState.COMPLETED : processState;
	    		 this.setOutputMessage(this.getUpdateMessage(startTime, !processState.equals(ProcessState.CANCELLED) ? i-1 : i, this.maxPrimeNumber, count, processState));
	    		 this.toggleRunButtons(true);	    		  		
	    	 }
	    	 catch(Exception ex)
	    	 {
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
	private String getUpdateMessage(long startTime, long currentPrime, long maxPrime, long count, ProcessState processState)
	{
		StringBuilder sb = new StringBuilder();
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
		   append(currentPrime).
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
	private long getNextNumber(long currentIndex, long numInterval)
	{
		return (currentIndex + 1 + numInterval);
	}
	
	private enum ProcessState {INPROGRESS, CANCELLED, COMPLETED}
}
