package zigZag_conversion;

public class MathSolution {
	public String solution(String s , int nRows) {
		if(s == null || nRows <= 0)
			return null;
		StringBuffer result = new StringBuffer();
		if(s.isEmpty())
			return result.toString();
		if(nRows == 1)
			return s;
		int length = s.length();
		
		//每一个单元的字符数
		int len = (nRows << 1) - 2;
		//单元数，不包含最后一个可能未完的单元
		int counter = length / len;
		//最后一个可能未完的单元
		int last = length % len;
		
		for(int i = 0 ; i < nRows ; ++ i) {
			//根据last的数目判断改行最后未完的单元在该行是否有字符
			int count = counter + (last > i ? 1 : 0);
			for(int j = 0 ; j < count ; ++ j) {
				//每一个单元出现的第一个字符串肯定存在，直接加入到结果中，，这里是按照行输出的
				result.append(s.charAt(j * len + i));
				//第一行和最后一行不包含撇路径上的节点
				if(i > 0 && i < nRows - 1) {
					//对于 一撇上的点，判断是否存在
					int next = (j + 1) * len - i;
					if(next < length)
						result.append(s.charAt(next));
				}
			}
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		MathSolution solution = new MathSolution();
		System.out.println(solution.solution(test, 5));
	}
}
