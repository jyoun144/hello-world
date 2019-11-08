package lab07;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;



public class PrimeUtil extends SwingWorker<Float, Long> 
{
	private static final long LONG_NUM_2 = 2;
	private static final long LONG_NUM_0 = 0;
	private final long maxPrimeNumber;
	private final HomeSwingFrame frame;	
	private final AtomicLong totalPrimeNumCount = new AtomicLong(0);
	
	
	@Override
    protected Float doInBackground() {
		  
		long startTime = System.currentTimeMillis();
		List<Long> list = new ArrayList<Long>();
		
		for(Long i=2L; i <= 100000; i++)
		{
			try
			{
		  this.wait(1000);
			}
			catch(Exception ex)
			{
				
			}
		}		
		
		float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
		
		return elapsedTime;	
	}	
	@Override
    protected void done() {
		
		//f;
		try
		{
			Float result = this.get();
			
				frame.appendMessage(Float.toString(result) + "\n");
				
			
			
		}
		catch(Exception ex)
		{
			
		}
		
		
	}	
	@Override
    protected void process(List<Long> list) {
		
		for(Long item : list)
		{
		frame.appendMessage(item.toString() + "\n");		
		}
	}	
	public PrimeUtil(long maxPrimeNumber, HomeSwingFrame frame)
	{
		this.maxPrimeNumber = maxPrimeNumber;
		this.frame = frame;		
	}
	private boolean isNumberPrime(long n)
	{
		boolean result = true;
		long floor = n/LONG_NUM_2;
		for(long i = 1; i <= floor; i++)
		{
			if ((n % i) == LONG_NUM_0)
			{
				result = false;
				break;
			}
		}
		return result;
	}
}
