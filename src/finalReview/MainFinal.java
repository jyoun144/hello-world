package finalReview;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


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
		// question_6_lazyInitRace();
		// question_18_synchronized_list_putIfAbsent();
		 question_34_semaphore_futureTask_blockingQueue();
	
		

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
		for(int i=0; i< 40; i++)
		{
			new Thread(new InstanceTest(obj)).start();			
		}
	   
	}
	
	private static void question_18_synchronized_list_putIfAbsent()
	{
		ListHelper<String> list = new ListHelper<>();
		list.putIfAbsent("one");
		System.out.println("Added 'one'");
		list.putIfAbsent("one");
		System.out.println("Added 'one'");
		list.putIfAbsent("two");
		System.out.println("Added 'two'");
		System.out.println("Expected list size: 2" + " / " + "Actual list size: " + list.list.size());
	}
	private static void question_34_semaphore_futureTask_blockingQueue()
	{	
		final Semaphore semaphore = new Semaphore(2);
		final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(2);
		MyCallablePut callableput1 = new MyCallablePut(1000, blockingQueue);
		MyCallablePut callableput2 = new MyCallablePut(2000, blockingQueue);
		MyCallablePut callableput3 = new MyCallablePut(1000, blockingQueue);
		MyCallablePut callableput4 = new MyCallablePut(2000, blockingQueue);
		FutureTask<String> futureTaskput1 = new FutureTask<String>(callableput1);
		FutureTask<String> futureTaskput2 = new FutureTask<String>(callableput2);
		FutureTask<String> futureTaskput3 = new FutureTask<String>(callableput3);
		FutureTask<String> futureTaskput4 = new FutureTask<String>(callableput4);
		new Thread(futureTaskput1).start();
		new Thread(futureTaskput2).start();
		new Thread(futureTaskput3).start();
		new Thread(futureTaskput4).start();
		MyCallableTake callabletake1 = new MyCallableTake(semaphore, blockingQueue);
		MyCallableTake callabletake2 = new MyCallableTake(semaphore, blockingQueue);
		MyCallableTake callabletake3 = new MyCallableTake(semaphore, blockingQueue);
		MyCallableTake callabletake4 = new MyCallableTake(semaphore, blockingQueue);
		FutureTask<String> futureTasktake1 = new FutureTask<String>(callabletake1);
		FutureTask<String> futureTasktake2 = new FutureTask<String>(callabletake2);
		FutureTask<String> futureTasktake3 = new FutureTask<String>(callabletake3);
		FutureTask<String> futureTasktake4 = new FutureTask<String>(callabletake4);
		new Thread(futureTasktake1).start();
		new Thread(futureTasktake2).start();
		new Thread(futureTasktake3).start();
		new Thread(futureTasktake4).start();

		
		
		while (true) {
			try {
				if(futureTaskput1.isDone() && futureTaskput2.isDone() 
						&& futureTasktake1.isDone()
						&& futureTasktake2.isDone()
						&& futureTasktake3.isDone()
						&& futureTasktake4.isDone()){
					System.out.println("Done");					
					return;
				}
				
				if(!futureTaskput1.isDone()){					
				//wait indefinitely for future task to complete
				System.out.println("FutureTask1 output="+futureTaskput1.get());
				}
				
				System.out.println("Waiting for FutureTask2 to complete");
				String s = futureTaskput2.get(200L, TimeUnit.MILLISECONDS);
				if(s !=null){
					System.out.println("FutureTask2 output="+s);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}catch(TimeoutException e){
				//do nothing
			}
		}
		
	}
}
