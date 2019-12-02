package finalReview;

public class LazyInitRace
{
	private ExpensiveObject instance = null;
	private int counter = 0;	
	
	public ExpensiveObject getInstance()
	{		
		if(this.instance != null)
		{		
			return this.instance;
		}
		else
		{
			synchronized(this)
			{
				System.out.println(++counter);
				
				if(this.instance == null)
				{				
					this.instance = new ExpensiveObject();					
				}
			}
			return this.instance;
		}
		
	}
		
	
	
	
}
