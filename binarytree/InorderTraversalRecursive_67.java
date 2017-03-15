/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-inorder-traversal/
 * 二叉树中序遍历，经典遍历递归实现 & 分治法递归实现
 * @author yzwall
 */

package binarytree;

import java.util.ArrayList;

//// 二叉树节点定义
//class TreeNode1 {
//	public int val;
//	public TreeNode1 left, right;
//	public TreeNode1(int val) {
//		this.val = val;
//		this.left = this.right = null;
//	}
//}

// 经典递归实现
class Solution3 {
	 /**
	  * @param root: The root of binary tree.
	  * @return: inorder in ArrayList which contains node values.
	  */
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
	 	if (root == null) {
	 		return new ArrayList<Integer>();
	    }
	     
	    ArrayList<Integer> results = new ArrayList<Integer>();
	    traverse(root, results);
	     
	    return results;
	}
 
	 /**
	  * 中序遍历经典递归实现，“左根右”
	  */
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

// 分治法递归实现
class Solution4 {
	 /**
	  * @param root: The root of binary tree.
	  * @return: inorder in ArrayList which contains node values.
	  */
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


public class InorderTraversalRecursive_67 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
