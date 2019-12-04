package finalReview;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.BlockingQueue;

public class MyCallableTake implements Callable<String> {	
	private final Semaphore semaphore;
	private final BlockingQueue<String> blockingQueue;
	
	public MyCallableTake(Semaphore semaphore, BlockingQueue<String> blockingQueue)
	{		
		this.semaphore = semaphore;
		this.blockingQueue = blockingQueue;
	}	
	@Override
	public String call() throws Exception {		
        //return the thread name executing this callable task
		String queueItem = "";
		try
		{
			this.semaphore.acquire();
			queueItem = this.blockingQueue.take();	
			System.out.println(Thread.currentThread().getName() + " took item from queue");
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();			
		}
		finally {
		this.semaphore.release();
		}
        return ("String Name: " + Thread.currentThread().getName() + "/" + "Queue Item: " + queueItem);
	}

}