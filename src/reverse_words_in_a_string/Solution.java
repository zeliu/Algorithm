package reverse_words_in_a_string;

/*
 * 首先将字符串按照分隔符分割，然后对每一个分割的块进行逆序，然后对每一个逆序之后的块之间添加一个分隔符
 * 最后再将整个合并之后的字符串在进行逆序
 */

public class Solution {
	public String solution(String s) {
		if(s == null || s.isEmpty())
			return null;
		int start = -1;
		final char delimiter = ' ';
		s = s.trim();
		StringBuffer result = new StringBuffer();
		int size = s.length();
		if(size == 0) 
			return result.toString();
				
		for(int i = 0 ; i < size ; ++ i) {
			if(s.charAt(i) == delimiter) {
				if(start < 0)
					continue;
				dealWithSplit(s, start, i - 1, result);
				result.append(delimiter);
				start = -1;
			} else {
				if(start < 0)
					start = i;
			}
		}
		if(start >= 0) {
			dealWithSplit(s, start, size - 1, result);
		}
		reverseString(result);
		return result.toString();
	}
	
	private void dealWithSplit(String s, int start, int end, StringBuffer buf) {
		for(int i = end ; i >= start ; -- i) {
			buf.append(s.charAt(i));
		}
	}
	
	private void reverseString(StringBuffer buf) {
		int end = buf.length() - 1;
		int start = 0;
		while(start < end) {
			char temp = buf.charAt(start);
			buf.setCharAt(start, buf.charAt(end));
			buf.setCharAt(end, temp);
			
			++ start;
			-- end;
		}
	}
	
	public static void main(String[] args) {
		String test = "a";
		Solution s = new Solution();
		System.out.println(s.solution(test));
	}
}
