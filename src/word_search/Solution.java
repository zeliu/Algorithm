package word_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 非递归思路：首先还是将每一个字符串出现的位置保存在一个map中，并且标记这些位置都是未使用过的
 * 然后依次遍历待查找的字符串，对于每一个字符串，查看该字符串出现的所有位置，如果该位置能够在相邻
 * 的位置中找到下一个字符，那么就标记该位置已使用并进栈，如果能够遍历到最后一个字符，那么说明能找到该路径
 * 否则让该位置出栈，继续尝试下一个位置，如果某个字符所有的位置都已经被尝试，则说明不存在该路径。
 * 
 */

public class Solution {
	private class Point {
		private int x;
		private int y;
		private boolean used;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.used = false;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public boolean isUsed() {
			return this.used;
		}
		
		public void setUsed(boolean used) {
			this.used = used;
		}
		
		public boolean adjacent(Point p) {
			if(p == null)
				return false;

			return (p.x - this.x == 1 && p.y == this.y) || 
					(this.x - p.x == 1 && p.y == this.y) || 
					(p.y - this.y == 1 && p.x == this.x) || 
					(this.y - p.y == 1 && p.x == this.x);
		}
		
		public String toString() {
			return "(" + x + " , " + y + ")";
		}
	}
	
	private class Element {
		private char ch;
		private int index;
		
		public Element(char ch, int index) {
			this.ch = ch;
			this.index = index;
		}
		
		public int getIndex(){
			return this.index;
		}
		
		public char getChar() {
			return this.ch;
		}		
	}
	
	private class Stack {
		private Element[] stack;
		private int top;
		public Stack(int size) {
			stack = new Element[size];
			this.top = 0;
		}
		
		public void push(Element e) {
			stack[top ++] = e;
		}
		
		public Element pop() {
			top --;
			if(top < 0)
				return null;
			return stack[top];
		}
		
		public int getTop() {
			return top;
		}
		
		public Element top() {
			return stack[top - 1];
		}
	}
	
	private class Unit {
		private List<Point> points;
		
		public Unit() {
			points = new ArrayList<Point>();
		}
		
		public void addPoint(Point p) {
			points.add(p);
		}

		public List<Point> getPoints() {
			return points;
		}
	}
	public boolean exist(char[][] board, String word) {
		if(board == null || word == null)
			return false;
		if(word.isEmpty())
			return true;
		
        Map<Character, Unit> indexMap = new HashMap<Character, Unit>();
        int row = board.length;
        int col = board[0].length;
        for(int i = 0 ; i < row ; ++ i) {
        	for(int j = 0 ; j < col ; ++ j) {
        		char ch = board[i][j];
        		Unit points = indexMap.get(ch);
        		if(points == null) {
        			points = new Unit();
        			indexMap.put(ch, points);
        		}
        		points.addPoint(new Point(i, j));
        	}
        }
        
        int length = word.length();
        Stack stack = new Stack(length);
        Unit head = indexMap.get(word.charAt(0));
        for(int i = 0 ; i < head.getPoints().size() ; ++ i) {
        	stack.push(new Element(word.charAt(0), i));
        	head.getPoints().get(i).setUsed(true);
        	while(stack.getTop() > 0) {
        		int index = stack.getTop() - 1;
        		if(index == length - 1)
        			return true;
        		Element top = stack.top();
        		Element next = getNextElement(indexMap, word, index, top.getIndex());
        		if(next == null) {
        			Element e = stack.pop();
        			indexMap.get(e.getChar()).getPoints().get(e.getIndex()).setUsed(false);
        		}
        		else {
        			stack.push(next);
        		}
        	}
        }
        return false;
	}
	
	private Element getNextElement(Map<Character, Unit> map, String word, int index, int start) {
		if(index >= word.length() - 1)
			return null;
		
		char first = word.charAt(index);
		char second = word.charAt(index + 1);
		
		Unit cur = map.get(first);
		Unit next = map.get(second);
		if(cur == null || next == null)
			return null;
		
		Point point = cur.getPoints().get(start);
		int size = next.getPoints().size();
		for(int i = 0 ; i < size ; ++ i) {
			Point nextPoint = next.getPoints().get(i);
			if(nextPoint.isUsed())
				continue;
			if(point.adjacent(nextPoint)) {
				nextPoint.setUsed(true);
				return new Element(second, i);
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		char[] a = new char[]{'A','B','C','E'};
		char[] b = new char[]{'S','F','C','S'};
		char[] c = new char[]{'A','D','E','E'};
		
		/*
		char[] a = new char[]{'a','a','a'};
		char[] b = new char[]{'a','a','a'};
		char[] c = new char[]{'a','a','a'};
		*/
		char[][] board = new char[][]{a,b,c};
		Solution exist = new Solution();
		System.out.println(exist.exist(board , "ABCB"));
	}
}
