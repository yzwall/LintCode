/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-postorder-traversal/
 * 二叉树后序遍历，经典遍历递归实现 & 分治法递归实现 & 非递归实现
 * @author yzwall
 */

package binarytree;

import java.util.ArrayList;
import java.util.ArrayDeque;
/**
 * 二叉树后序遍历-经典递归实现
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
	     
	    // 遍历左子树
	    traverse(root.left, results);
	    // 遍历右子树
	    traverse(root.right, results);
	    // 遍历子树根节点
	    results.add(root.val);	
	}
}

/**
 * 二叉树后序遍历-分治法实现
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
 * 二叉树后序遍历-非递归实现
 * 逆序入栈：根右左
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
