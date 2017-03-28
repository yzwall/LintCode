/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-inorder-traversal/
 * �����������������������ݹ�ʵ�� & ���η��ݹ�ʵ��
 * @author yzwall
 */

package binarytree;

import java.util.ArrayList;

//// �������ڵ㶨��
//class TreeNode1 {
//	public int val;
//	public TreeNode1 left, right;
//	public TreeNode1(int val) {
//		this.val = val;
//		this.left = this.right = null;
//	}
//}

// ����ݹ�ʵ��
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
	  * �����������ݹ�ʵ�֣�������ҡ�
	  */
	private void traverse(TreeNode root, ArrayList<Integer> results) {
		if (root == null) {
			return;
		}
	     
	    // ����������
	    traverse(root.left, results);
	     
	    // �����������ڵ�
	    results.add(root.val);	
	     
	    // ����������
	    traverse(root.right, results);
	}
}

// ���η��ݹ�ʵ��
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
