package datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ������׺�����ʽת��Ϊ�沨��ʽ
 * http://www.lintcode.com/zh-cn/problem/convert-expression-to-reverse-polish-notation/
 * @author yzwall
 */
class Solution2 {
	ArrayList<String> convertToRPN(String[] expression) {
		// ³���Լ��
		ArrayList<String> postfix = new ArrayList<>();
		if (expression == null || expression.length == 0) {
			return postfix;
		}
		
		// �涨��������ȼ�
		HashMap<String, Integer> op = new HashMap<>();
		op.put("+", 1);
		op.put("-", 1);
		op.put("*", 2);
		op.put("/", 2);
		op.put("(", 3);
		op.put(")", 3);
		
		// stackΪ�������ջ
		ArrayDeque<String> stack = new ArrayDeque<>();
		for (String token : expression) {
			// ����ֱ��������沨��ʽ
			if (!op.containsKey(token)) {
				postfix.add(token);
				continue;
			}
			if (!stack.isEmpty()) {
				// ���������ţ�һֱ��ջ��������沨��ʽ����ջ��Ϊ�����Ż��ջ
				if (token.equals(")")) {
					while (!stack.isEmpty()) {
						if (stack.peek().equals("(")) {
							stack.pop();
							break;
						}
						postfix.add(stack.pop());
					}
				// ��ǰ�������ȼ�����ջ�����ţ�һֱ��ջ��ջ���������ȼ����ڵ�ǰ���Ż��ջ
				} else if (op.get(stack.peek()) >= op.get(token)) {
					while (!stack.isEmpty() && op.get(stack.peek()) >= op.get(token)) {
						// ������ֻ��tokenΪ�ҿպ�ʱ�ų�ջ
						if (stack.peek().equals("(")) {
							break;
						}
						postfix.add(stack.pop());
					}
					stack.push(token);
				} else { // ��ǰ�������ȼ�����ջ�����ţ�ֱ����ջ
					stack.push(token);
				}
			} else {
				// ��ջ��ֱ����ջ
				stack.push(token);
			}
		}
		
		// ���ʽɨ����ϣ���ջ�ڷ���ȫ����ջ���沨��ʽ
		while (!stack.isEmpty()) {
			postfix.add(stack.pop());
		}		
		
		return postfix;
	}
}

public class ConvertToRPN_370 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
