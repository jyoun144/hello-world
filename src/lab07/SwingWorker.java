package lab07;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class SwingWorker extends Thread
{
	private final JTextArea txtArea;
	private final long LONG_NUM_2 = 2;
	private final long LONG_NUM_0 = 0;
	private final long maxPrimeNumber;	
	
	public SwingWorker(JTextArea txtArea, long maxPrimeNumber)
	{		
		this.txtArea = txtArea;
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
	    				 this.appendText("Intermediate Count: " + Long.toString(count));
	    				 
	    			 }
	    			 if(this.isNumberPrime(i))
	    			 {			    	 
			    		 count++;
	    			 }	
	    			 currentTime = (System.currentTimeMillis() - startTime)/1000L;
	    	     }
	    		 this.appendText("Final count is: " + Long.toString(count) + "\n" + "Total run time (seconds): " + Float.toString((System.currentTimeMillis() - startTime)/1000F));
	    	 }
	    	 catch(Exception ex)
	    	 {
	    		 this.appendText("ERROR*****************");	    		 
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
	private void appendText(String text)
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	txtArea.setText(text + "\n");                         
            }
        });
	}
}
