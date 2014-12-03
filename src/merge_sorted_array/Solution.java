package merge_sorted_array;

public class Solution {
	public void merge(int A[], int m, int B[], int n) {
		if(A == null || m < 0)
			return ;
		if(B == null || n < 0)
			return ;
		if(m == 0) {
			for(int i = 0 ; i < n ; ++ i)
				A[i] = B[i];
		}
		if(n == 0) {
			return ;
		}

		int indexA = m - 1;
		int indexB = n - 1;
		int cur = m + n - 1;
		while(indexA >= 0 && indexB >= 0) {
			int a = A[indexA];
			int b = B[indexB];
			if(a > b) {
				A[cur] = a;
				indexA --;
			}
			else {
				A[cur] = b;
				indexB -- ;
			}
			cur --;
		}
		for(int i = indexA ; i >= 0 ; -- i) 
			A[cur --] = A[i];
		for(int i = indexB ; i >= 0 ; -- i)
			A[cur --] = B[i];
	}
	
	public static void main(String[] args) {
		Solution test = new Solution();
		
		int A[] = new int[20];
		int B[] = new int[]{2,5,6};
		for(int i = 0 ; i < 3; ++ i)
			A[i] = i + 1;
		test.merge(A, 3, B, 3);
		for(int i = 0 ; i < A.length ; ++ i)
			System.out.print(A[i] + ",");
		System.out.println();
	}
}
