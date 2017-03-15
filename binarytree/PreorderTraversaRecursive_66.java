/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-preorder-traversal/
 * 二叉树前序遍历，经典遍历递归实现 & 分治法递归实现
 * @author yzwall
 */
package binarytree;
import java.util.ArrayList;

// 经典递归实现
class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
    	
        if (root == null) {
            return new ArrayList<Integer>();
        }
        
        ArrayList<Integer> results = new ArrayList<Integer>();
        traverse(root, results);
        
        return results;
    }
    
    /**
     * 前序遍历经典递归实现，“根左右”
     */
    private void traverse(TreeNode root, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }
        // 遍历子树根节点
        path.add(root.val);	
        
        // 遍历左子树
        traverse(root.left, path); 
        
        // 遍历右子树
        traverse(root.right, path);
    }
}

// 分治法递归实现
class Solution1 {
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


public class PreorderTraversaRecursive_66 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
