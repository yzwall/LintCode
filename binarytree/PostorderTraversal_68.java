/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-postorder-traversal/
 * �����������������������ݹ�ʵ�� & ���η��ݹ�ʵ�� & �ǵݹ�ʵ��
 * @author yzwall
 */

package binarytree;

import java.util.ArrayList;
import java.util.ArrayDeque;
/**
 * �������������-����ݹ�ʵ��
 */
class Solution5 {
	 /**
	  * @param root: The root of binary tree.
	  * @return: inorder in ArrayList which contains node values.
	  */
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
	 	if (root == null) {
	 		return new ArrayList<Integer>();
	    }
	     
	    ArrayList<Integer> results = new ArrayList<Integer>();
	    traverse(root, results);
	     
	    return results;
	}
	
	private void traverse(TreeNode root, ArrayList<Integer> results) {
		if (root == null) {
			return;
		}
	     
	    // ����������
	    traverse(root.left, results);
	    // ����������
	    traverse(root.right, results);
	    // �����������ڵ�
	    results.add(root.val);	
	}
}

/**
 * �������������-���η�ʵ��
 */
class Solution6 {
	 /**
	  * @param root: The root of binary tree.
	  * @return: inorder in ArrayList which contains node values.
	  */
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> results = new ArrayList<Integer>();
	 	if (root == null) {
	 		return results;
	    }
	 	
	 	// divide & conquer
	 	ArrayList<Integer> left = postorderTraversal(root.left);
	 	// merge
	 	results.addAll(left);
	 	
	 	// divide & conquer
	 	ArrayList<Integer> right = postorderTraversal(root.right);
	 	// merge
	 	results.addAll(right);
	 	
	 	// merge
	 	results.add(root.val);
	 	
	 	return results;
	}
}

/**
 * �������������-�ǵݹ�ʵ��
 * ������ջ��������
 */
class Solution19 {
	
	private class Pair {
		TreeNode root;
		boolean isVisited;
		public Pair(TreeNode root, boolean isVisited) {
			this.root = root;
			this.isVisited = isVisited;
		}
	}
	
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> results = new ArrayList<Integer>();
	 	if (root == null) {
	 		return results;
	    }
	 	
	 	ArrayDeque<Pair> stack = new ArrayDeque<>();
	 	stack.push(new Pair(root, false));
	 	while (!stack.isEmpty()) {
	 		Pair top = stack.pop();
	 		if (top.root == null) {
	 			continue;
	 		}
	 		if (top.isVisited) {
	 			results.add(top.root.val);
	 		} else {
	 			stack.push(new Pair(top.root, true));
	 			stack.push(new Pair(top.root.right, false));
	 			stack.push(new Pair(top.root.left, false));
	 		}
	 	}
	 	
	 	return results;
	}
}

public class PostorderTraversal_68 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
