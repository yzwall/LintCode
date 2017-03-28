/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-postorder-traversal/
 * �����������������������ݹ�ʵ�� & ���η��ݹ�ʵ��
 * @author yzwall
 */

package binarytree;

import java.util.ArrayList;

//// �������ڵ㶨��
//class TreeNode {
//	public int val;
//	public TreeNode left, right;
//	public TreeNode(int val) {
//		this.val = val;
//		this.left = this.right = null;
//	}
//}

// ����ݹ�ʵ��
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
	  * �����������ݹ�ʵ�֣������Ҹ���
	  */
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

// ���η��ݹ�ʵ��
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
