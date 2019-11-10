package lab07;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class SwingWorker extends Thread
{
	//private final JTextArea txtArea;
	private final long LONG_NUM_2 = 2;
	private final long LONG_NUM_0 = 0;
	private final long maxPrimeNumber;	
	private final HomeSwingFrame frame;
	
	public SwingWorker(HomeSwingFrame frame, long maxPrimeNumber)
	{		
		this.frame = frame;
		this.maxPrimeNumber = maxPrimeNumber;		
	}
	@Override
	 public void run(){
		long count = 0;
		long startTime = System.currentTimeMillis();
		long currentTime = (System.currentTimeMillis() - startTime)/1000L;	
		long lastTime = currentTime;		
	    	 try
	    	 {	    			 
	    		 for(long i = 2; i <= this.maxPrimeNumber; i++)
	    	     {
	    			 if(this.isInterrupted())
	    			 {
	    				 break;
	    			 }
	    			 
	    			 if(lastTime != currentTime)	    			
	    			 {
	    				 lastTime = currentTime;
	    				 this.setOutputMessage("Intermediate Count: " + Long.toString(count));
	    				 
	    			 }
	    			 if(this.isNumberPrime(i))
	    			 {			    	 
			    		 count++;
	    			 }	
	    			 currentTime = (System.currentTimeMillis() - startTime)/1000L;
	    	     }
	    		 this.setOutputMessage("Prime Number Count [2, "+  this.maxPrimeNumber + "]: " + Long.toString(count) + "\n" + "Processing Time (seconds): " + Float.toString((System.currentTimeMillis() - startTime)/1000F));
	    		 this.toggleRunButtons(true);
	    		 this.setMaxNumberIsEnabled(true);
	    		 
	    		 
	    	 }
	    	 catch(Exception ex)
	    	 {
	    		 this.setOutputMessage("ERROR*****************");	
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
            	frame.clearMaxNumTxt();
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
	private void setMaxNumberIsEnabled(boolean isEnabled)
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	frame.setMaxNumberEnabled(isEnabled);  
            	frame.setFocusToInputNum();   
            }
        });
	}	
}
