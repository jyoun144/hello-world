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
		final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);		
		FutureTask<String> futureTaskput1 = getFutureThreadPut(blockingQueue, "futureTaskPut_1", 100);
		FutureTask<String> futureTaskput2 = getFutureThreadPut(blockingQueue, "futureTaskPut_2", 200);
		FutureTask<String> futureTaskput3 = getFutureThreadPut(blockingQueue, "futureTaskPut_3", 300);
		FutureTask<String> futureTaskput4 = getFutureThreadPut(blockingQueue, "futureTaskPut_4", 400);		
		FutureTask<String> futureTasktake1 = getFutureThreadTake(blockingQueue, semaphore, "futureTaskTake_1");
		FutureTask<String> futureTasktake2 = getFutureThreadTake(blockingQueue,  semaphore, "futureTaskTake_2");
		FutureTask<String> futureTasktake3 = getFutureThreadTake(blockingQueue,  semaphore, "futureTaskTake_3");
		FutureTask<String> futureTasktake4 = getFutureThreadTake(blockingQueue,  semaphore, "futureTaskTake_4");		
		while (true) {
			//try {
				if(futureTaskput1.isDone() 
						&& futureTaskput2.isDone() 
						&& futureTaskput3.isDone() 
						&& futureTaskput4.isDone() 
						&& futureTasktake1.isDone()
						&& futureTasktake2.isDone()
						&& futureTasktake3.isDone()
						&& futureTasktake4.isDone()){
					System.out.println("Done");					
					return;
				}				
				
			//} catch (InterruptedException | ExecutionException e) {
				//e.printStackTrace();
			//}catch(TimeoutException e){
				//do nothing
			//}
			//}
		}
	}	
	private static FutureTask<String> getFutureThreadTake(BlockingQueue<String> blockingQueue, Semaphore semaphore, String threadName)
	{
		MyCallableTake callabletake = new MyCallableTake(semaphore, blockingQueue);	
		FutureTask<String> futureTasktake = new FutureTask<String>(callabletake);
		new Thread(futureTasktake, threadName).start();
		return futureTasktake;
	}
	private static FutureTask<String> getFutureThreadPut(BlockingQueue<String> blockingQueue, String threadName, int timeDelay)
	{
		MyCallablePut callable = new MyCallablePut(timeDelay, blockingQueue);	
		FutureTask<String> futureTaskput = new FutureTask<String>(callable);
		 new Thread(futureTaskput, threadName).start();
		 return futureTaskput;
		
	}
}
