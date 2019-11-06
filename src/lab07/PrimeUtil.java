package lab07;
import javax.swing.SwingWorker;



public class PrimeUtil extends SwingWorker<Long, Long> 
{
	private static final long LONG_NUM_2 = 2;
	private static final long LONG_NUM_0 = 0;
	private final long maxPrimeNumber;
	private final HomeSwingFrame frame;
	
	@Override
    protected Long doInBackground() {
		Long i = 1L;
		return i;
	
	}	
	public PrimeUtil(long maxPrimeNumber, HomeSwingFrame frame)
	{
		this.maxPrimeNumber = maxPrimeNumber;
		this.frame = frame;
	}
	private boolean isNumberPrimer(long n)
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
