/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-postorder-traversal/
 * 二叉树后序遍历，经典遍历递归实现 & 分治法递归实现
 * @author yzwall
 */

package binarytree;

import java.util.ArrayList;

//// 二叉树节点定义
//class TreeNode {
//	public int val;
//	public TreeNode left, right;
//	public TreeNode(int val) {
//		this.val = val;
//		this.left = this.right = null;
//	}
//}

// 经典递归实现
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

	 /**
	  * 后序遍历经典递归实现，“左右根”
	  */
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

// 分治法递归实现
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

public class PostorderTraversalRecursive_68 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
