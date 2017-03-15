/**
 * /**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-preorder-traversal/
 * 二叉树前序遍历，非递归实现
 * @author yzwall
 */
package binarytree;

import java.util.ArrayList;

class Solution2 {
    /**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
    	
    	ArrayList<Integer> results = new ArrayList<Integer>();
    	
    	// 递归出口
        if (root == null) {
            return results;
        }
        
        // divide & conquer
        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);
        
        // merge
        results.add(root.val);
        results.addAll(left);
        results.addAll(right);
        
        return results;
    }
}


public class PreorderTraversaIterative_66 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
