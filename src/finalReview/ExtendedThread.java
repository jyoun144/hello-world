package finalReview;

public class ExtendedThread extends Thread
{ 
	private final int index;
	
	public ExtendedThread(int index)
	{
		this.index = index;		
	}
	
	@Override
	public void run()
	{
		try 
		{
			System.out.println("Extended Thread class_" + this.index);	
			Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
		   ex.printStackTrace();
		}
	}
}
