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
	
	
	public SwingWorker(HomeSwingFrame frame, long maxPrimeNumber)
	{		
		this.frame = frame;
		this.maxPrimeNumber = maxPrimeNumber;		
	}
	@Override
	 public void run(){
		long count = 0;
		long startTime = System.currentTimeMillis();
		long currentTime = (System.currentTimeMillis() - startTime)/2000L;	
		long lastTime = currentTime;
		boolean isCancelled = false;
		long i;
	    	 try
	    	 {	    			 
	    		 for(i = 2; i <= this.maxPrimeNumber; i++)
	    	     {
	    			 if(this.isInterrupted())
	    			 {
	    				 isCancelled = true;
	    				 break;
	    			 }	    			 
	    			 if(lastTime != currentTime)	    			
	    			 {
	    				 lastTime = currentTime;	    				 
	    				 this.setOutputMessage(this.getUpdateMessage(startTime, i, this.maxPrimeNumber, count, isCancelled));
	    			 }
	    			 if(this.isNumberPrime(i))
	    			 {			    	 
			    		 count++;
	    			 }	
	    			 currentTime = (System.currentTimeMillis() - startTime)/2000L;		    		
	    	     }
	    		 this.setOutputMessage(this.getUpdateMessage(startTime, !isCancelled ? i-1 : i, this.maxPrimeNumber, count, isCancelled));
	    		 this.toggleRunButtons(true);
	    		 this.setMaxNumberIsEnabled(true);	    		
	    	 }
	    	 catch(Exception ex)
	    	 {
	    		 this.setOutputMessage("An exception occurred within the worker thread: " + ex.getMessage());	    		
	    		 this.setMaxNumberIsEnabled(true);
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
            	frame.clearMaxNumTxt();            }
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
	private void setMaxNumberIsEnabled(boolean isEnabled)
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	frame.setMaxNumberEnabled(isEnabled);  
            	frame.setFocusToInputNum();   
            }
        });
	}	
	private String getUpdateMessage(long startTime, long currentPrime, long maxPrime, long count, boolean isCancelled)
	{
		StringBuilder sb = new StringBuilder();
		if(isCancelled)
		{
			sb.append(MSG_PREFIX_CANCELLED);
		}
		sb.append("Prime Number Count [2, ").
		   append(currentPrime).
		   append("/").
		   append(maxPrime).
		   append("]: ").
		   append(count).
		   append("\n").
		   append("Processing Time (seconds): ").
		   append(Float.toString((System.currentTimeMillis() - startTime)/1000F));
		
		
		return sb.toString();
	}
}
