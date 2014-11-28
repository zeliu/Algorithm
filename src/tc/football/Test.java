package tc.football;

import java.util.List;

import sun.tools.jar.resources.jar;
import tc.common.InputUtils;

public class Test {
	public static void main(String[] args) {
		testFromTo(new int[]{2, 3, 7}, 1, 100);
	}
	
	private static void testWithInput(int[] args) {
		Integer value = InputUtils.getNextInt();
		int[] mins = args;
		while(value != null) {
			long start = System.currentTimeMillis();
			Solution solution = new RecursionSolutionWithCache(mins, value);
			solution.fetchCombinations();
			long cost = System.currentTimeMillis() - start;
			System.out.println(solution);
			System.err.println("cost " + cost + " millseconds\n");
			
			value = InputUtils.getNextInt();
		}
	}
	
	private static void testFromTo(int[] args, int from, int to) {
		if(from > to || from <= 0 || to <= 0 || args == null || args.length < 1)
			return ;
		
		int[] mins = args;
		for(int i = from; i <= to ; ++ i) {
			long start = System.currentTimeMillis();
			Solution solution = new RecursionSolution(mins, i);
			int result = solution.fetchCombinations();
			long cost = System.currentTimeMillis() - start;
			System.out.println("value = " + i + ", result = " + result + ", cost = " + cost + " ms");
		}
	}
	
	private static void test() {
		long sum = 2;
		long prevSum = 1;
		int index = 1;
		
		for(int i = 3 ; true ; ++ i) {
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
