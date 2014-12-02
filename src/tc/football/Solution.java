package tc.football;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	protected int[] minValues;
	protected Set<ResultItem> results = null;
	protected int sumValue;
	
	public Solution(int[] args, int sum) {
		this.minValues = args;
		this.sumValue = sum;
		this.results = new HashSet<>();
	}
	
	public int fetchCombinations() {
		return 0;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer("minvalues : [");
		for(int value : minValues) {
			buf.append(value + ", ");
		}
		buf.setCharAt(buf.length() - 2, ']');
		buf.append(", ").append("sum value = " + sumValue + "\n");
		buf.append("output : size = " + results.size() + "\n");

		for(ResultItem result : results) {
			buf.append(result.toString()).append("\n");
		}
		return buf.toString();
	}
}
