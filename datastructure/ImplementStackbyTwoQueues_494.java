package datastructure;

/**
 * ����������ʵ��һ��ջ
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
	
	// queue1ʼ�ո���ʵ��ջ��push����
	public void push(int element) {
		queue1.offer(element);
	}
	
	// ��from����Ԫ�س��ӣ���������ӵ�to���У�ֱ��from������ֻ��һ��Ԫ��
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
				// queue1����pop��Ϊ��
				queue1.poll();
			} else if (!queue2.isEmpty()) {
				move(queue2, queue1);
				// queue1����pop��Ϊ��
				queue2.poll();
			}
		}
	}
	
	public int top() {
		if (!isEmpty()) {
			if (!queue1.isEmpty()) {
				move(queue1, queue2);
				// ��ȡģ��ջ��Ԫ�أ����ջ�����ڶ���
				top = queue1.peek();
				queue2.offer(queue1.poll());
			} else if (!queue2.isEmpty()) {
				move(queue2, queue1);
				// ��ȡģ��ջ��Ԫ�أ����ջ�����ڶ���
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
