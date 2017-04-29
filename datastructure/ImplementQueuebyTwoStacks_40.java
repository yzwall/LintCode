package datastructure;

import java.util.ArrayDeque;

/**
 * 用两个栈实现一个队列
 * http://www.lintcode.com/en/problem/implement-queue-by-two-stacks/
 * @author yzwall
 */
class MyQueue {
	private ArrayDeque<Integer> stack1;
	private ArrayDeque<Integer> stack2;
	
	MyQueue() {
		this.stack1 = new ArrayDeque<>();
		this.stack2 = new ArrayDeque<>();
	}
	
	public void push(int element) {
		stack1.push(element);
	}
	
	public int pop() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
	
	public int top() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}			
		}
		return stack2.peek();
	}
}

public class ImplementQueuebyTwoStacks_40 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
