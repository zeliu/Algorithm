package tc.football;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class SolutionWithCacheUseThread extends RecursionSolutionWithCache {
	private int minValue;
	private Object[] locks;
	
	public SolutionWithCacheUseThread(int[] args, int sum) {
		super(args, sum);
		int size = cache.size();
		locks = new Object[size];
		for(int i = 0 ; i < size ; ++ i)
			locks[i] = new Object();
		
		minValue = 1;
	}
	
	public int fetchCombinations() {
		initCache();
		//得到最大值，用于释放不必要的内存
		minValue = minValues[0];
		for(int value : minValues) {
			if(value < minValue)
				minValue = value;
		}
		if(minValue < 4)
			minValue = 4;
		fetchCombinationsInside();
		if(results == null)
			return -1;
		return results.size();
	}
	
	private void fetchCombinationsInside() {
		int index = 0;
		int size = cache.size();
		for( ; index < size && cache.get(index) != null ; ++ index);
		
		TaskThread[] threads = new TaskThread[minValue];
		for(int i = 0 ; i < minValue ; ++ i) {
			threads[i] = new TaskThread(i);
			threads[i].start();
		}
		
		for(int i = index ; i < size ; ++ i) {
			threads[i % minValue].put(i);
		}
		for(TaskThread thread : threads) {
			thread.put(Integer.valueOf(-1));
		}
		
		try {
			for(TaskThread thread : threads) {
				thread.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		results = cache.get(sumValue);
	}
	
	class TaskThread extends Thread {
		private LinkedBlockingQueue<Integer> queue;
		public TaskThread(int index) {
			super("thread-" + index);
			queue = new LinkedBlockingQueue<Integer>();
		}
		
		public void put(Integer value) {
			try {
//				System.err.println(getName() + " put " + value);
				queue.put(value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			Integer current = null;
			try {
				current = queue.take();				
				while(current > 0) {
					Set<ResultItem> newResult = new HashSet<ResultItem>();
					for(int value : minValues) {
						int sub = current - value;
						if(sub < 0)
							continue;
						Set<ResultItem> result = null;
						while((result = cache.get(sub)) == null){
							Object lock = locks[sub];
							synchronized (lock) {
								lock.wait();
							}
						}
							
						for(ResultItem item : result) {
							ResultItem newItem = (ResultItem) item.clone();
							newItem.addItem(value);
							newResult.add(newItem);
						}
					}			
					cache.set(current, newResult);
//					System.err.println(getName() + " calculate " + current);
					Object lock = locks[current];
					synchronized (lock) {
						lock.notifyAll();
					}
					current = queue.take();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.err.println(getName() + " finish !");
		}
	}
}
