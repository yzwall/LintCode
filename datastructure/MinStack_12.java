package datastructure;

/**
 * 实现一个最小栈，要求push,pop,min操作均为O(1)复杂度
 * http://www.lintcode.com/en/problem/min-stack/
 * @author yzwall
 */
import java.util.ArrayDeque;

class MinStack {
	private ArrayDeque<Integer> stack;
	private ArrayDeque<Integer> minStack;
	
	MinStack() {
		this.stack = new ArrayDeque<>();
		this.minStack = new ArrayDeque<>();
	}
	
	public void push(int number) {
		stack.push(number);
		if (minStack.isEmpty()) {
			minStack.push(number);
		} else {
			// 实现最大栈时，只需push(Math.max(number, minStack.peek())
			minStack.push(Math.min(number, minStack.peek()));
		}
	}
	
	public int pop() {
		minStack.pop();
		return stack.pop();
	}
	
	public int min() {
		return minStack.peek();
	}
}


public class MinStack_12 {

	public static void main(String[] args) {

	}

}
