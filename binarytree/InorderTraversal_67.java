/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-inorder-traversal/
 * 二叉树中序遍历，经典遍历递归实现 & 分治法递归实现 & 非递归实现
 * @author yzwall
 */
package binarytree;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 二叉树中序遍历-经典递归实现
 */
class Solution3 {
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
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
	    // 遍历子树根节点
	    results.add(root.val);	
	    // 遍历右子树
	    traverse(root.right, results);
	}
}

/**
 * 二叉树中序遍历-分治法实现
 */
class Solution4 {
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> results = new ArrayList<Integer>();
	 	if (root == null) {
	 		return results;
	    }
	 	
	 	// divide & conquer
	    ArrayList<Integer> left = inorderTraversal(root.left);
	    
	    // merge
	    results.addAll(left);
	    results.add(root.val);
	    
	    // divide & conquer
	    ArrayList<Integer> right = inorderTraversal(root.right);
	    // merge
	    results.addAll(right);
	    
	    return results;
	}
}


/**
 * 二叉树中序遍历-非递归实现
 * 逆序入栈
 */
class Solution18 {
	private class Pair {
		TreeNode root;
		boolean isVisited;
		public Pair(TreeNode root, boolean isVisited) {
			this.root = root;
			this.isVisited = isVisited;
		}
	} 
	
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
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
	 			// 入栈顺序：右，根，左
	 			stack.push(new Pair(top.root.right, false)); 
	 			stack.push(new Pair(top.root, true));
	 			stack.push(new Pair(top.root.left, false));
	 		}
	 	}
	    return results;
	}	
}

public class InorderTraversal_67 {
	public static void main(String[] args) {
	}
}
