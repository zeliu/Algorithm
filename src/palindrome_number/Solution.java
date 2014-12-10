package palindrome_number;

public class Solution {
	public boolean isPalindrome(int x) {
		if(x < 0)
			return false;
		if(x == 0)
			return true;
		
		int length = 1;
		int sum = 1;
		int temp = x;
		//计算出小于x的最大的10的n次方
		while(temp >= 10) {
			temp /= 10;
			sum *= 10;
			length ++;
		}
		
		//前后同时查看是否相同，因此只需要查看一半次数
		length = (length >> 1);
		for(int i = 0 ; i < length ; ++ i) {
			//判断前面的数字和后面的数字是否相同，一旦出现不相同则说明不是
			if((x / sum) % 10 != x % 10)
				return false;
			x /= 10;
			sum /= 100;
		}
		
		return true;
    }
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.isPalindrome(313));
		System.out.println(solution.isPalindrome(1234554321));
	}
}
