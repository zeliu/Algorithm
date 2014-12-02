package tc.football;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ResultItemUseCount extends ResultItem {
	private int[] args;
	private Map<Integer, Integer> counterMap;
	
	private ResultItemUseCount() {
		super();
	}
	
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
	
	public Object clone() {
		ResultItemUseCount item = new ResultItemUseCount();
		item.args = this.args;
		item.result.addAll(this.result);
		item.counterMap = new HashMap<Integer, Integer>(this.counterMap);
		
		return item;
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
			if(value == null || value != this.counterMap.get(key))
				return false;			
		}
		
		/*
		 * check result is right !
		Integer[] curList = this.result.toArray(new Integer[0]);
		Arrays.sort(curList);
		Integer[] itemList = item.result.toArray(new Integer[0]);
		Arrays.sort(itemList);
		if(curList.length != curList.length) {
			System.err.println(item + " equals " + this);
			return false;
		}
		for(int i = 0 ; i < curList.length ; ++ i) {
			if(curList[i] != itemList[i])
			{
				System.err.println(item + " equals " + this);
				return false;
			}
		}
		*/
		
		return true;
	}
}
