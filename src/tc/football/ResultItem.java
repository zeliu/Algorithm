package tc.football;

import java.util.LinkedList;
import java.util.List;


public abstract class ResultItem {
	protected List<Integer> result;
	public ResultItem() {
		result = new LinkedList<Integer>();
	}
	
	public abstract void addItem(Integer value);
	
	public abstract Object clone();
	
	public List<Integer> getResult() {
		return result;
	}
	
	public String toString() {
		return result.toString();
	}
}
