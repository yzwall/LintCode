package datastructure;
import java.util.ArrayDeque;

/**
 * 逆波兰表达式 or 后缀表达式求值
 * @author yzwall
 */
class Solution1 {
	public int evalRPN(String[] tokens) {
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		String Operations = "+-*/";
		for (String token : tokens) {
			if (!Operations.contains(token)) {
				stack.push(Integer.parseInt(token));
				continue;
			}
			int first = stack.pop();
			int second = stack.pop();
			if (token.equals("+")) {
				stack.push(second + first);
			} else if (token.equals("-")) {
				stack.push(second - first);
			} else if (token.equals("*")) {
				stack.push(second * first);
			} else {
				stack.push(second / first);
			}
		}
		return stack.pop();
	}
}


public class EvaluateReversePolishNotation_424 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
