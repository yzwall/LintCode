package datastructure;

/**
 * 用两个队列实现一个栈
 * http://www.lintcode.com/en/problem/implement-stack-by-two-queues/
 * @author yzwall
 */
import java.util.ArrayDeque;

class Stack {
	private ArrayDeque<Integer> queue1;
	private ArrayDeque<Integer> queue2;
	int top;
	
	Stack() {
		this.queue1 = new ArrayDeque<>();
		this.queue2 = new ArrayDeque<>();
	}
	
	// queue1始终负责实现栈的push操作
	public void push(int element) {
		queue1.offer(element);
	}
	
	// 将from队列元素出队，并依次入队到to队列，直到from队列中只有一个元素
	public void move(ArrayDeque<Integer> from,
					 ArrayDeque<Integer> to) {
		while (from.size() > 1) {
			to.offer(from.poll());
		}
	}
	
	public void pop() {
		if (!isEmpty()) {
			if (!queue1.isEmpty()) {
				move(queue1, queue2);
				// queue1队列pop后为空
				queue1.poll();
			} else if (!queue2.isEmpty()) {
				move(queue2, queue1);
				// queue1队列pop后为空
				queue2.poll();
			}
		}
	}
	
	public int top() {
		if (!isEmpty()) {
			if (!queue1.isEmpty()) {
				move(queue1, queue2);
				// 获取模拟栈顶元素，清空栈顶所在队列
				top = queue1.peek();
				queue2.offer(queue1.poll());
			} else if (!queue2.isEmpty()) {
				move(queue2, queue1);
				// 获取模拟栈顶元素，清空栈顶所在队列
				top = queue2.peek();
				queue1.offer(queue2.poll());
			}
			return top;
		}
		return 0;
	}
	
	public boolean isEmpty() {
		return queue1.isEmpty() && queue2.isEmpty();
	}
}



public class ImplementStackbyTwoQueues_494 {

	public static void main(String[] args) {
	}

}
