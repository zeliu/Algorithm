package tc.football;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecursionSolutionWithCache extends Solution {
	private List<Set<ResultItem>> cache = null;
	
	public RecursionSolutionWithCache(int[] args, int sum) {
		super(args, sum);
		int max = args[0];
		for(int value : args){
			if(value > max) {
				max = value;
			}
		}
		max = max > sum ? max : sum;
		cache = new ArrayList<Set<ResultItem>>(max + 1);
		for(int i = 0 ; i < max + 1 ; ++ i)
			cache.add(null);
	}
	
	public int fetchCombinations() {
		int minValue = minValues[0];
		//初始化已有参数和比这些数小的数对应的结果
		for(int value : minValues) {
			ResultItem item = new ResultItemUseCount(minValues);
//			ResultItem item = new ResultItemUsePrime(minValues);
			item.addItem(value);
			Set<ResultItem> values = new HashSet<ResultItem>();
			values.add(item);
			cache.set(value, values);
			if(value < minValue)
				minValue = value;
		}
		
		for(int i = 0 ; i < minValue ; ++ i) {
			cache.set(i, new HashSet<ResultItem>());
		}
		
		fetchCombinationsInside();
		return results.size();
	}
	
	private void fetchCombinationsInside() {
		results = recursionCalculate(sumValue);
	}
		
	
	private Set<ResultItem> recursionCalculate(int param) {
		if((cache.size() < param) || (param < 0))
			return null;		
		if(cache.get(param) != null) {
			return cache.get(param);
		}
		
		Set<ResultItem> thisResult = new HashSet<ResultItem>();
		for(int value : minValues) {
			Set<ResultItem> valueResult = recursionCalculate(param - value);
			if(valueResult == null)
				continue;
			for(ResultItem item : valueResult) {
				if(item == null) {
					System.err.println("never happen!");
					continue;
				}
				ResultItem copyItem = new ResultItemUseCount((ResultItemUseCount)item);
//				ResultItem copyItem = new ResultItemUsePrime((ResultItemUsePrime)item);
				copyItem.addItem(value);
				thisResult.add(copyItem);
			}		
		}
		cache.set(param, thisResult);
		return thisResult;
	}
}
