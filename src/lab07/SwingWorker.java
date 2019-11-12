package lab07;
import javax.swing.SwingUtilities;
import java.lang.StringBuilder;

public class SwingWorker extends Thread
{
	private final long LONG_NUM_2 = 2;
	private final long LONG_NUM_0 = 0;
	private final long maxPrimeNumber;	
	private final HomeSwingFrame frame;
	private static final String MSG_PREFIX_CANCELLED = "************PROCESSING CANCELLED************\n";
	private static final String MSG_PREFIX_PROCESSING = "************CALCULATIONS IN PROGRESS************\n";
	private static final String MSG_PREFIX_COMPLETED = "************PROCESSING COMPLETED************\n";
	private static final String MSG_PREFIX_INITIAL = "*******PERFORMING INITIAL CALCULATIONS*******\n";
	
	
	public SwingWorker(HomeSwingFrame frame, long maxPrimeNumber)
	{		
		this.frame = frame;
		this.maxPrimeNumber = maxPrimeNumber;		
	}
	@Override
	 public void run(){	
	    	 try
	    	 {
	    		 ProcessState processState = ProcessState.INPROGRESS;
    			long count = 0;
    			long startTime = System.currentTimeMillis();
    			long currentTime = (System.currentTimeMillis() - startTime)/2000L;
    			long lastTime = currentTime;    			
    			long i;
    			this.setOutputMessage(MSG_PREFIX_INITIAL);
    			
	    		 for(i = 2; i <= this.maxPrimeNumber; i++)
	    	     {
	    			 if(this.isInterrupted())
	    			 {
	    				 processState = ProcessState.CANCELLED;
	    				 break;
	    			 }	    			 
	    			 if(lastTime != currentTime)	    			
	    			 {
	    				 lastTime = currentTime;	    				 
	    				 this.setOutputMessage(this.getUpdateMessage(startTime, i, this.maxPrimeNumber, count, processState));
	    			 }
	    			 if(this.isNumberPrime(i))
	    			 {			    	 
			    		 count++;
	    			 }	
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
	private boolean isNumberPrime(long n)
	{
		boolean result = true;
		long floor = n/LONG_NUM_2;		
		for(long i = 2; i <= floor; i++)
		{
			if ((i != n) && (n % i) == LONG_NUM_0)
			{
				result = false;
				break;
			}
		}
		return result;
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
		   append("]: ").
		   append(count).
		   append("\n").
		   append("Processing Time (seconds): ").
		   append(Float.toString((System.currentTimeMillis() - startTime)/1000F));	
		return sb.toString();
	}
	private enum ProcessState {INPROGRESS, CANCELLED, COMPLETED}
}
