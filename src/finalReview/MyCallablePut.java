package finalReview;
import java.util.concurrent.Callable;
import java.util.concurrent.BlockingQueue;

public class MyCallablePut implements Callable<String> {
	private long waitTime;	
	private final BlockingQueue<String> blockingQueue;
	
	public MyCallablePut(int timeInMillis, BlockingQueue<String> blockingQueue){
		this.waitTime=timeInMillis;		
		this.blockingQueue = blockingQueue;		
	}
	@Override
	public String call() throws Exception {
		Thread.sleep(waitTime);        
		this.blockingQueue.put(Thread.currentThread().getName());	
		System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + " put item in Blocking Queue");
        return Thread.currentThread().getName();
	}
}