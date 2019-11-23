package lab08;

import java.lang.StringBuilder;
import java.util.concurrent.atomic.AtomicLong;
import javax.swing.SwingUtilities;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

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
	private final int numOfWorkerThreads;
	private enum ProcessState {INPROGRESS, CANCELLED, COMPLETED}	
	
	public SwingUpdater(MultiThreadSwingFrame frame, long maxPrimeNumber, AtomicLong numOfPrimesFound, AtomicLong numOfSearchedNumbers, long numInterval, ExecutorService workerPool, int numOfWorkerThreads)
	{		
		this.frame = frame;
		this.maxPrimeNumber = maxPrimeNumber;		
		this.numOfPrimesFound = numOfPrimesFound;
		this.numOfSearchedNumbers = numOfSearchedNumbers;
		this.numInterval = numInterval;		
		this.workerPool = workerPool;
		this.numOfWorkerThreads = numOfWorkerThreads;
	}	
	
	@Override
	 public void run(){	
		long startTime = System.currentTimeMillis();
		ProcessState processState = ProcessState.INPROGRESS;
		List<Future<?>> list = new ArrayList<>();		
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
	    				 this.cancelThreads(list);
	    				 break;
	    			 }	 
	    			
	    		  if(i <=  this.maxPrimeNumber)
	    		  {	
	    			  // Limits the max number of tasks that can reside within the queue in order to prevent memory overflow
	    			  if(((ThreadPoolExecutor)workerPool).getQueue().size() < Constants.MAX_TASK_QUEUE_SIZE)
	    			  {	    			  
		    			 endNumber = (i + this.numInterval);
		    			 endNumber = endNumber <= this.maxPrimeNumber ? endNumber : this.maxPrimeNumber;  
		    			 list.add(workerPool.submit(new ConsumerService(this.numOfPrimesFound, this.numOfSearchedNumbers, new PrimeCounter(i, endNumber))));		    			
		    			 i = endNumber + 1L;  
	    			  }
	    			  else
	    			  {
	    				  // Removes completed tasks from list
	    				  // Ensures list of Future<Consumer> references does not grow too large
	    				  this.removeCompletedTasks(list);	   
	    				 Thread.sleep(100);
	    			  }	    			
	    		  }	    			 
	    			 if(lastTime != currentTime)	    			
	    			 {
	    				 lastTime = currentTime;	    				 
	    				 this.setOutputMessage(this.getUpdateMessage(startTime, this.numOfSearchedNumbers.get(), this.maxPrimeNumber, this.numOfPrimesFound.get(), processState));
	    			 }	    			 
	    			// set output for every two seconds
	    			 currentTime = (System.currentTimeMillis() - startTime)/2000L;		    			
	    		
	    	     }    			
    			this.toggleRunButtons(true);    				
	    		processState = processState.equals(ProcessState.INPROGRESS) ? ProcessState.COMPLETED : processState;
	    		this.setOutputMessage(this.getUpdateMessage(startTime, this.numOfSearchedNumbers.get(), this.maxPrimeNumber, this.numOfPrimesFound.get(), processState));		
	    	 }	
	    	
	    	 catch(InterruptedException ie)
	    	 {	    		
				 this.cancelThreads(list);
				 this.toggleRunButtons(true);
				 System.out.println(this.getName() + ":  interrupted by user");
	    	 }	
	    	    	 
	    	 catch(Exception ex)
	    	 {	    		 
	    		 this.cancelThreads(list);
	    		 System.out.println(this.getName() + ":");
	    		 ex.printStackTrace();
	    		 this.setOutputMessage("A serious system error occurred.  Please, try resubmitting input.");    		
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
		   append( String.format("%.2f", (System.currentTimeMillis() - startTime)/1000F)).
		   append("\n").
		   append("Number of worker threads ---> " + this.numOfWorkerThreads);		
		return sb.toString();
	}	
	private void cancelThreads(List<Future<?>> list)
	{		
		if(list != null)
		{
			for(Future<?> item : list)
			{				
				if(item != null && !item.isCancelled() && !item.isDone())
				{
					item.cancel(true);
				}
			}
		}
	}	
	private void removeCompletedTasks(List<Future<?>> list)
	{			
		List<Future<?>> completedTasks = new ArrayList<Future<?>>();		
		for(Future<?> item : list)
		{				
			if(item.isDone())
			{
				completedTasks.add(item);
			}
		}
		if(completedTasks.size() > 0)
		{
			list.removeAll(completedTasks);
		}		
	}
}
