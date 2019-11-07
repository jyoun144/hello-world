package lab07;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;



public class PrimeUtil extends SwingWorker<Long, Long> 
{
	private static final long LONG_NUM_2 = 2;
	private static final long LONG_NUM_0 = 0;
	private final long maxPrimeNumber;
	private final HomeSwingFrame frame;	
	private final AtomicLong totalPrimeNumCount = new AtomicLong(0);
	private long startTime;
	
	@Override
    protected Long doInBackground() {
		  
		startTime = System.currentTimeMillis();
		
		for(Long i=2L; i <= 50; i++)
		{
			publish(i);
			
			
			
		}
		
			
		
		return 1L;	
	}	
	@Override
    protected void done() {
		float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
		frame.appendMessage(Float.toString(elapsedTime));
		
		
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
