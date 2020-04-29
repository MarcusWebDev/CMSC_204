package application;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * A queue that holds the directions for a vehicle to move. 0 is up, 1 is down, 2 is right, and 3 is left
 * @author Marcus Brooks
 *
 */
public class CarQueue<T> {
	
	private LinkedList<Integer> queue;
	private Lock lock;
	private Condition sufficientItemsCondition;
	private Condition belowPreferredQueueSizeCondition;
	private final int PREFERRED_QUEUE_SIZE = 50;
	
	public CarQueue() {
		
		lock = new ReentrantLock();
		sufficientItemsCondition = lock.newCondition();
		belowPreferredQueueSizeCondition = lock.newCondition();
		queue = new LinkedList<Integer>();
		
		int valueToAdd = (int)(Math.random() * 4);
		for (int i = 0; i < 6 && !Thread.interrupted(); i++) {
			queue.add(valueToAdd);
			valueToAdd = (int)(Math.random() * 4);
			
		}
	}
	
	public void addToQueue() {
		class MyRunnable implements Runnable {
			public void run() {
				int valueToAdd = (int)(Math.random() * 4);
				int counter = 0;
				while (true) {
					lock.lock();
					while (queue.size() >= PREFERRED_QUEUE_SIZE) {
						try {
							belowPreferredQueueSizeCondition.await();
						} catch (InterruptedException e) {}
					}
					queue.add(valueToAdd);
					System.out.println("Queue size: " + queue.size());
					sufficientItemsCondition.signalAll();
					valueToAdd = (int)(Math.random() * 4);
					counter++;
					lock.unlock();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
				}
			}
		}
		
		Runnable r = new MyRunnable();
		Thread t = new Thread(r);
		t.start();
	}
	public int deleteQueue() {
		lock.lock();
		while (queue.isEmpty()) {
			try {
				sufficientItemsCondition.await();
			} catch (InterruptedException e) {}
		}
		
		int removedValue = queue.remove();
		belowPreferredQueueSizeCondition.signalAll();
		lock.unlock();
		
		return removedValue;
	}
}