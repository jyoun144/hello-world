package finalReview;

public class LazyInitRace
{
	private ExpensiveObject instance = null;
	
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
				if(this.instance == null)
				{				
					this.instance = new ExpensiveObject();					
				}				
			}
			return this.instance;			
		}		
	}
		
	
	
	
}
