package datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 将（中缀）表达式转换为逆波兰式
 * http://www.lintcode.com/zh-cn/problem/convert-expression-to-reverse-polish-notation/
 * @author yzwall
 */
class Solution2 {
	ArrayList<String> convertToRPN(String[] expression) {
		// 鲁棒性检测
		ArrayList<String> postfix = new ArrayList<>();
		if (expression == null || expression.length == 0) {
			return postfix;
		}
		
		// 规定运算符优先级
		HashMap<String, Integer> op = new HashMap<>();
		op.put("+", 1);
		op.put("-", 1);
		op.put("*", 2);
		op.put("/", 2);
		op.put("(", 3);
		op.put(")", 3);
		
		// stack为运算符号栈
		ArrayDeque<String> stack = new ArrayDeque<>();
		for (String token : expression) {
			// 数字直接输出到逆波兰式
			if (!op.containsKey(token)) {
				postfix.add(token);
				continue;
			}
			if (!stack.isEmpty()) {
				// 遇到右括号，一直出栈（输出到逆波兰式）到栈顶为左括号或空栈
				if (token.equals(")")) {
					while (!stack.isEmpty()) {
						if (stack.peek().equals("(")) {
							stack.pop();
							break;
						}
						postfix.add(stack.pop());
					}
				// 当前符号优先级低于栈顶符号，一直出栈到栈顶符号优先级低于当前符号或空栈
				} else if (op.get(stack.peek()) >= op.get(token)) {
					while (!stack.isEmpty() && op.get(stack.peek()) >= op.get(token)) {
						// 左括号只有token为右空号时才出栈
						if (stack.peek().equals("(")) {
							break;
						}
						postfix.add(stack.pop());
					}
					stack.push(token);
				} else { // 当前符号优先级高于栈顶符号，直接入栈
					stack.push(token);
				}
			} else {
				// 空栈，直接入栈
				stack.push(token);
			}
		}
		
		// 表达式扫描完毕，将栈内符号全部出栈到逆波兰式
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
