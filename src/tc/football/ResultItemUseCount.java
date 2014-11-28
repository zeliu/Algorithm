package tc.football;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ResultItemUseCount extends ResultItem {
	private int[] args;
	private Map<Integer, Integer> counterMap;
	
	public ResultItemUseCount(int[] args) {
		super();
		this.args = args;
		counterMap = new HashMap<Integer, Integer>();
		for(int value : args) {
			counterMap.put(value, 0);
		}
	}
	
	public ResultItemUseCount(ResultItemUseCount item) {
		result = new LinkedList<Integer>(item.result);
		this.args = item.args;
		this.counterMap = new HashMap<Integer, Integer>(item.counterMap);		
	}
	
	@Override
	public void addItem(Integer value) {
		if(value == null)
			return ;
		Integer counter = counterMap.get(value);
		if(counter == null) {
			System.err.println("never happen : not exist this counter");
			return ;
		}
		result.add(value);
		counterMap.put(value, counter + 1);
	}
	
	public int hashCode() {
		int result = 17;
		for(int value : counterMap.values()) {
			result = 37 * result + value;
			result |= (1 << (value % 16));
		}	
		return result;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof ResultItemUseCount)) {
			return false;
		}
		
		ResultItemUseCount item = (ResultItemUseCount)obj;
		if(item.result.size() != this.result.size())
			return false;
		
		for(int key : this.counterMap.keySet()) {
			Integer value = item.counterMap.get(key);
			if(value == null && value != this.counterMap.get(key))
				return false;			
		}
		
		return true;
	}
}
