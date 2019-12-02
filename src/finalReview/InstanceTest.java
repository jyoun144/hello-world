package finalReview;

public class InstanceTest implements Runnable
{
	private final LazyInitRace obj;
	public InstanceTest(LazyInitRace obj)
	{
		this.obj = obj;
	}
	@Override
	public void run()
	{
		var result = this.obj.getInstance();	
	}
}
