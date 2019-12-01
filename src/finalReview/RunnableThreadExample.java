package finalReview;

public class RunnableThreadExample implements Runnable
{
private final int index;
	
	public RunnableThreadExample(int index)
	{
		this.index = index;		
	}
	@Override  
	public void run()
	{
		try 
		{
			System.out.println("Implemented Runnable interface_" + this.index);
			Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
		   ex.printStackTrace();
		}		
	}
}
