package zigZag_conversion;

import java.util.ArrayList;
import java.util.List;

public class NormalSolution {
	public String solution(String s, int nRows) {
		if(s == null || nRows <= 0)
			return null;
		StringBuffer result = new StringBuffer();
		if(s.isEmpty())
			return result.toString();
		if(nRows == 1)
			return s;
		//字符串长度
		int length = s.length();
		//计算出每一行最多可能存在多少个字符，只是用来创建List的是指定capacity，可以不用
		int maxLengthPerLine = (length / nRows + 1) << 1;
		List<List<Character>> allChars = new ArrayList<List<Character>>(nRows);
		for(int i = 0 ; i < nRows ; ++ i) {
			allChars.add(new ArrayList<Character>(maxLengthPerLine));
		}
		
		//将一竖和一撇(从下向上，不包含最后一个节点)，上的所有节点当做一个单元，例如nRows=N，那么一竖包含的节点数
		//就是N,一撇的长度则是N - 2(不包含起点和终点)，总共的节点数为2 * N - 2
		int len = (nRows << 1) - 2;
		//最多包含lines个单元。但是最后一个单元不一定包含完整的一竖和一撇，甚至一竖都不完全。
		int lines = length / nRows;
		//对于每一个单元，计算出这个单元所经过的点和所有相同位置(右移之后)其它节点出现的位置。
		for(int i = 0 ; i < lines + 1 ; ++ i) {
			for(int j = 0 ; j < nRows ; ++ j) {
				//计算出每一个字符应该出在的二维数组的位置
				//对于每一个单元的一竖需要计算出每一个节点的出现在二维数组的位置
				int next = i * len + j;
				if(next < length) {
					allChars.get(j).add(s.charAt(next));
				}
				//对于一撇上的每一个点，不包含开头和结尾两个点，计算出其它点所在的位置
				if(j > 0 && j < nRows - 1) {
					next = (i + 1) * len - j;
					if(next < length)
						allChars.get(j).add(s.charAt(next));
				}
			}
		}
		
		//按照Z字形输出新排序的字符串
		for(int i = 0 ; i < nRows ; ++ i) {
			List<Character> chars = allChars.get(i);
			StringBuffer buf = new StringBuffer();
			if(i == 0 || i == nRows - 1) {				
				int space = nRows - 2;				
				for(Character character : chars) {
					buf.append(character);
					for(int j = 0 ; j < space ; ++ j)
						buf.append(' ');
				}
			} else {
				int prevSpace = nRows - (i + 2);
				int nextSpace = i - 1;
				int half = chars.size() % 2;
				int units = (half == 0) ? chars.size() / 2 : chars.size() / 2 + 1;
				
				for(int j = 0 ; j < units ; ++ j) {
					buf.append(chars.get(2 * j));
					for(int k = 0 ; k < prevSpace ; ++ k)
						buf.append(' ');
					if(2 * j + 1 < chars.size()) {
						buf.append(chars.get(2 * j + 1));
						for(int k = 0 ; k < nextSpace ; ++ k)
							buf.append(' ');
					}
				}
			}
			System.out.println(buf);
		}
		
		//得到的二维数组之后就可以按照行输出转换之后的字符串了。
		for(List<Character> chars : allChars) {
			for(Character ch : chars)
				result.append(ch);
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		NormalSolution solution = new NormalSolution();
		System.out.println(solution.solution(test, 5));
	}
}
