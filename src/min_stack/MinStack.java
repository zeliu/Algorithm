package min_stack;

//OJ未通过，暂时找不出来哪里有错误！
public class MinStack {
	private static final int MAX_LENGTH = 1 << 16;
	private Element[] stack = new Element[MAX_LENGTH];
	private int stackSize = 0;
	
	public void push(int x) {
		Element element = null;
        if(stackSize == 0) {
        	element = new Element(x, 0);
        } else {
        	int index = stack[stackSize - 1].getIndex();
        	int minValue = stack[index].getValue();
        	if(minValue >= x) 
        		index = stackSize;
        	element = new Element(x, index);
        }
        stack[stackSize ++] = element;
    }

    public void pop() {
    	if(stackSize == 0)
    		return ;
        stack[stackSize - 1] = null;
        stackSize --;
    }

    public int top() {
    	if(stackSize == 0)
    		return Integer.MIN_VALUE;
        return stack[stackSize - 1].getValue();
    }

    public int getMin() {
        int index = stack[stackSize - 1].getIndex();
        return stack[index].getValue();
    }
    
    public String toString() {
    	StringBuffer buffer = new StringBuffer("[");
    	for(int i = 0 ; i < stackSize ; ++ i){
    		buffer.append("(" + stack[i].getValue() + "," + stack[i].getIndex() + ")" + ",");
    	}
    	buffer.setCharAt(buffer.length() - 1, ']');
    	return buffer.toString();
    }
    
    private class Element {
    	private int value;
    	private int index;
    	public Element(int value, int index) {
    		this.value = value;
    		this.index = index;
    	}
    	
    	public int getValue() {
    		return this.value;
    	}
    	
    	public int getIndex() {
    		return this.index;
    	}
    }
    
    public static void main(String[] args) {
    	int[] values = new int[]{10,5,6,8,7,16,4,9};
    	MinStack stack = new MinStack();
    	for(int i = 8311 ; i <= 10000 ; ++ i) {
    		stack.push(-1 * i);
    	}
    	
    	System.out.println(stack + " min " + stack.getMin());
    	System.out.println("top : " + stack.top());
    	stack.pop();
    	System.out.println("top : " + stack.top());
    	stack.pop();
    	System.out.println("top : " + stack.top());
    	stack.pop();
    	System.out.println("top : " + stack.top());
    	System.out.println(stack + " min " + stack.getMin());
    }
}
