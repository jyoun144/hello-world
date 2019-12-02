package finalReview;

public class MainFinal
{
	private static class MyWorker implements Runnable
	{
		public void run()
		{
			try 
			{
				System.out.println("I am a worker");
				Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}
			// do something slow
		}
	}

	public static void main(String[] args)
	{
		//question_1_implementing_runnable_interface_and_extending_thread_class();
		// question_2_run_multiple_threads_in_parallel();
		question_6_lazyInitRace();
		

	}
	private static void question_1_implementing_runnable_interface_and_extending_thread_class()
	{
		for(int i=0; i<10; i++)
		{
			Thread runnable = new Thread(new RunnableThreadExample(i));
			runnable.start();		
			ExtendedThread extended = new ExtendedThread(i);
			extended.start();
		}
	}
	
	private static void question_2_run_multiple_threads_in_parallel()
	{
		for(int x=0; x<100; x++)
		{
			(new Thread(new MyWorker())).start();			
		}		
	}
	private static void question_6_lazyInitRace()
	{
		LazyInitRace obj = new LazyInitRace();
		for(int i=0; i< 20; i++)
		{
			new Thread(new InstanceTest(obj)).start();
		}
	   
	}
	
	

}
