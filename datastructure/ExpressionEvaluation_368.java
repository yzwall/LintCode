package datastructure;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ��������׺�����ʽ������ֵ�������ʽת��Ϊ�沨��ʽ��Ȼ����ֵ
 * http://www.lintcode.com/en/problem/expression-evaluation/
 * @author yzwall
 */
class Solution3 {
    public int evaluateExpression(String[] expression) {
    	int result = 0;
    	if (expression == null || expression.length == 0) {
    		return result;
    	}
    	
    	// 1. ת������׺�����ʽΪ�沨��ʽ
    	HashMap<String, Integer> op = new HashMap<>();
    	ArrayList<String> postfix = new ArrayList<>();
    	ArrayDeque<String> stack = new ArrayDeque<>();
		op.put("+", 1);
		op.put("-", 1);
		op.put("*", 2);
		op.put("/", 2);
		op.put("(", 3);
		op.put(")", 3);
		
		for (String token : expression) {
			if (!op.containsKey(token)) {
				postfix.add(token);
				continue;
			}
			if (!stack.isEmpty()) {
				if (token.equals(")")) {
					while (!stack.isEmpty()) {
						if (stack.peek().equals("(")) {
							stack.pop();
							break;
						}
						postfix.add(stack.pop());
					}
				} else if (op.get(stack.peek()) >= op.get(token)) {
					while (!stack.isEmpty() && op.get(stack.peek()) >= op.get(token)) {
						if (stack.peek().equals("(")) {
							break;
						}
						postfix.add(stack.pop());
					}
					stack.push(token);
				} else {
					stack.push(token);
				}
			} else {
				stack.push(token);
			}
		}

		while (!stack.isEmpty()) {
			postfix.add(stack.pop());
		}
		
		// ³���Լ�⣬���ʽ��û�в�����
		if (postfix.isEmpty()) {
			return result;
		}
		
		// 2. �����沨��ʽֵ
		ArrayDeque<Integer> stack1 = new ArrayDeque<>();
		for (String token : postfix) {
			if (!op.containsKey(token)) {
				stack1.push(Integer.parseInt(token));
				continue;
			}
			int first = stack1.pop();
			int second = stack1.pop();
			if (token.equals("+")) {
				stack1.push(second + first);
			} else if (token.equals("-")) {
				stack1.push(second - first);
			} else if (token.equals("*")) {
				stack1.push(second * first);
			} else {
				stack1.push(second / first);
			}
		}
		result = stack1.pop();
		
		return result;
    }
}




public class ExpressionEvaluation_368 {
	public static void main(String[] args) {
	}
}
