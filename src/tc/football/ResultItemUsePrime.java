package tc.football;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sun.tools.jar.resources.jar;

//使用素数相乘来判断两个数组（返回的结果）是否包含相同的元素，根据任意一个素数的乘积只能由唯一的表示方式的原理。
//策略：每次向结果集中加入一个元素的时候就向乘积中乘以该元素对应的素数，如果根据总的乘积判断两个集合是否包含相同的元素。
//限制：限制用int保存全部的乘积，可能存在long溢出的情况，尽量使用比较小的素数进行相乘，但是是否溢出取决于数组的长度。
//int可能有溢出的危险，应该对正确性没有什么影响，因为任意一个数（包括溢出之后的数）也能保证它是一个唯一的素数序列相乘的结果
public class ResultItemUsePrime extends ResultItem {
	private int[] args;
	private int sumPrime;
	private Map<Integer, Integer> valueToPrime;
	private static List<Integer> primes;
	public ResultItemUsePrime(int[] args) {
		super();
		this.args = args;
		sumPrime = 1;
		valueToPrime = getPrimes(this.args);
	}
	
	public ResultItemUsePrime(ResultItemUsePrime prev) {
		result = new LinkedList<Integer>(prev.result);
		this.args = prev.args;
		sumPrime = prev.sumPrime;
		valueToPrime = getPrimes(this.args);
	}
	
	public void addItem(Integer value) {
		if(value == null) {
			return ;
		}
		result.add(value);
		Integer prime = valueToPrime.get(value);
		if(prime == null) {
			System.err.println("never happen : can not find prime");
			return ;
		}
		sumPrime *= prime;
	}
	
	//需要满足自反性、传递性、交换性和一致性，该类不满足一致性，但一般只比较一次，不对动态改变。
	public boolean equals(Object obj) {
		if(!(obj instanceof ResultItemUsePrime))
			return false;
		ResultItemUsePrime item = (ResultItemUsePrime)obj;
		if(this.result.size() != item.result.size())
			return false;
		
		boolean result = (item.sumPrime == this.sumPrime);
		if(result) {
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
		}
		return result;
	}
	
	//需要保证equals返回true的时候两个对象的hashCode相同
	//需要将long转换成int返回，long可能存在溢出，转换成int不一定能保证唯一性，所以可能存在bug。
	public int hashCode() {
		return this.sumPrime;
	}
	
	private static int getNextPrime(int start) {
		for(int i = start + 1 ; true ; i ++) {
			int j = 2;
			for( ; (j <= (i >> 1)) && (i % j != 0); ++ j);
			if(j > (i >> 1))
				return i;			
		}
	}
	
	private static Map<Integer, Integer> getPrimes(int[] args) {
		Map<Integer, Integer> valueToPrimeMap = new HashMap<>();
		if(args == null)
			return valueToPrimeMap;
		if(primes == null) {
			primes = new ArrayList<>(args.length);
			//第一个素数是3,之所以使用3开始是因为如果使用2可能导致溢出之后变成了0
			primes.add(3);
		}
		//如果不够，则继续寻找素数
		if(primes.size() < args.length) {
			int need = args.length - primes.size();
			for(int i = 0 ; i < need ; ++ i) {
				primes.add(getNextPrime(primes.get(primes.size() - 1)));
			}
		}
		
		int i = 0;
		for(int value : args) {
			valueToPrimeMap.put(value, primes.get(i));
			i ++;
		}
		return valueToPrimeMap;
	}
	
	public static void main(String[] args) {
		System.out.println(getPrimes(new int[] {1, 2, 3}));
	}
}
