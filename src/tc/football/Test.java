package tc.football;

import tc.common.InputUtils;

public class Test {
	public static void main(String[] args) {
		test();
		for(int i = 0 ; i < 1 ; ++ i) {
			testFromTo(new int[]{2, 3, 7}, 10, 500, 7);
			System.out.println("\n\n");
		}
	}
	
	private static void testWithInput(int[] args) {
		Integer value = InputUtils.getNextInt();
		int[] mins = args;
		while(value != null) {
			long start = System.currentTimeMillis();
			Solution solution = new SolutionFromBottomToUp(mins, value);
			solution.fetchCombinations();
			long cost = System.currentTimeMillis() - start;
			System.out.println(solution);
			System.err.println("cost " + cost + " millseconds\n");
			
			value = InputUtils.getNextInt();
		}
	}
	
	private static void testFromTo(int[] args, int from, int to, int gap) {
		if(from > to || from <= 0 || to <= 0 || args == null || args.length < 1)
			return ;
		
		int[] mins = args;
		for(int i = from; i <= to ; i += gap) {
			long start = System.currentTimeMillis();
			Solution solution = new SolutionWithCacheUseThread(mins, i);
			int result = solution.fetchCombinations();
			long cost = System.currentTimeMillis() - start;
			System.out.println("value = " + i + ", result = " + result + ", cost = " + cost + " ms");
		}
	}
	
	private static void test() {
		long sum = 2;
		long prevSum = 1;
		int index = 1;
		
		for(int i = 3 ; i < 100 ; ++ i) {
			int j = 2;
			for( ; j <= (i >> 1) && (i %j != 0) ; ++ j);
			if(j > (i >> 1)) {
				prevSum = sum;
				sum *= i;
				index ++;
				System.out.println("index = " + index + ", value = " + i);
			}
			if(prevSum > sum) {
				System.out.println("sum = " + sum  + " , prevSum = " 
					+ prevSum + ", current prime = " + i + ", current index = " + index);
				
			}
		}
	}
}
