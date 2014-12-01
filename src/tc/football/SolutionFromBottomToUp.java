package tc.football;

import java.util.HashSet;
import java.util.Set;

import sun.awt.SunHints.Value;

public class SolutionFromBottomToUp extends RecursionSolutionWithCache {
	private int maxValue = 0;
	public SolutionFromBottomToUp(int[] args, int sum) {
		super(args, sum);
	}

	public int fetchCombinations() {
		initCache();
		//得到最大值，用于释放不必要的内存
		maxValue = minValues[0];
		for(int value : minValues) {
			if(value > maxValue)
				maxValue = value;
		}
		fetchCombinationsInside();
		if(results == null)
			return -1;
		return results.size();
	}
	
	private void fetchCombinationsInside() {
		int index = 0;
		for( ; index < cache.size() && cache.get(index) != null ; ++ index);
		//对每一个进行计算
		for(int i = index ; i < cache.size() ; ++ i) {
			//如果已经计算过了，则不需要重复计算,防止初始化的结果被覆盖，导致出错
			if(cache.get(i) != null)
				continue;
			Set<ResultItem> newResult = new HashSet<ResultItem>();
			for(int value : minValues) {
				int sub = i - value;
				if(sub < 0)
					continue;
				Set<ResultItem> result = cache.get(sub);
				if(result == null)
					continue;
				for(ResultItem item : result) {
					ResultItem newItem = (ResultItem) item.clone();
					newItem.addItem(value);
					newResult.add(newItem);
				}
			}
			cache.set(i, newResult);
			//清理不需要的内存
			if(i - maxValue >= 0)
				cache.set(i - maxValue, null);
		}
		results = cache.get(sumValue);
	}
}
