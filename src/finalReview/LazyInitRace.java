package finalReview;

public class LazyInitRace
{
	private ExpensiveObject instance = null;	
	public synchronized ExpensiveObject getInstance()
	{
		if(instance == null)
		{
			instance = new ExpensiveObject();
		}
		return instance;
	}	
}
