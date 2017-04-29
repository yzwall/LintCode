package datastructure;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 给出（中缀）表达式，求表达值。将表达式转换为逆波兰式，然后求值
 * http://www.lintcode.com/en/problem/expression-evaluation/
 * @author yzwall
 */
class Solution3 {
    public int evaluateExpression(String[] expression) {
    	int result = 0;
    	if (expression == null || expression.length == 0) {
    		return result;
    	}
    	
    	// 1. 转换（中缀）表达式为逆波兰式
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
		
		// 鲁棒性检测，表达式中没有操作数
		if (postfix.isEmpty()) {
			return result;
		}
		
		// 2. 计算逆波兰式值
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
