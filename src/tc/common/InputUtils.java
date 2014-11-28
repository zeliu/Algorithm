package tc.common;

import java.util.Scanner;

final public class InputUtils {
	private static Scanner sc= new Scanner(System.in);
	private static String[] quits = new String[] {"bye","quit"};	
	
	public static Integer getNextInt() {
		while(true) {
			String input = sc.next();
			Integer value = null;
			try {
				for(String q : quits) {
					if(input.equalsIgnoreCase(q))
						return null;
				}
				value = Integer.valueOf(input);
			} catch (Exception e) { 
				System.err.println("input [" + input + "] can not to int !");
				continue ;
			}
			return value;
		}
	}
}
