import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Solution {
	public String solution(String s, int nRows) {
		if(s == null || nRows <= 0)
			return null;
		StringBuffer result = new StringBuffer();
		if(s.isEmpty())
			return result.toString();
		if(nRows == 1)
			return s;
		int length = s.length();
		int maxLengthPerLine = (length / nRows + 1) << 1;
		List<List<Character>> allChars = new ArrayList<List<Character>>(nRows);
		for(int i = 0 ; i < nRows ; ++ i) {
			allChars.add(new ArrayList<Character>(maxLengthPerLine));
		}
		
		int len = (nRows << 1) - 2;
		int lines = length / nRows;
		for(int i = 0 ; i < lines + 1 ; ++ i) {
			for(int j = 0 ; j < nRows ; ++ j) {
				int next = i * len + j;
				if(next < length) {
					allChars.get(j).add(s.charAt(next));
				}
				if(j > 0 && j < nRows - 1) {
					next = (i + 1) * len - j;
					if(next < length)
						allChars.get(j).add(s.charAt(next));
				}
			}
		}
		
		for(List<Character> chars : allChars) {
			for(Character ch : chars)
				result.append(ch);
		}
		
		return result.toString();
	}
	
	public String pureMathSolution(String s , int nRows) {
		if(s == null || nRows <= 0)
			return null;
		StringBuffer result = new StringBuffer();
		if(s.isEmpty())
			return result.toString();
		if(nRows == 1)
			return s;
		int length = s.length();
		
		int len = (nRows << 1) - 2;
		int counter = length / len;
		int last = length % len;
		
		for(int i = 0 ; i < nRows ; ++ i) {
			int count = counter + (last > i ? 1 : 0);
			for(int j = 0 ; j < count ; ++ j) {
				result.append(s.charAt(j * len + i));
				if(i > 0 && i < nRows - 1) {
					int next = (j + 1) * len - i;
					if(next < length)
						result.append(s.charAt(next));
				}
			}
		}
		return result.toString();
	}
	
	public boolean isPalindrome(int x) {
		if(x < 0)
			return false;
		if(x == 0)
			return true;
		
		int length = 1;
		int sum = 1;
		int temp = x;
		while(temp >= 10) {
			temp /= 10;
			sum *= 10;
			length ++;
		}
		
		length = (length >> 1);
		for(int i = 0 ; i < length ; ++ i) {
			if((x / sum) % 10 != x % 10)
				return false;
			x /= 10;
			sum /= 100;
		}
		
		return true;
    }
	
	public static void main(String[] args) {
		String test = "ABCDEFGHIJKLMN";
		Solution solution = new Solution();
//		System.out.println(solution.pureMathSolution(test, 10));
		System.out.println(solution.isPalindrome(313));
		System.out.println(solution.isPalindrome(1234554321));
	}
}
