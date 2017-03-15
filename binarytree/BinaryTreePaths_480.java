/**
 * 分治法 & DFS递归遍历，找出所有二叉树中的路径(root->leaf)
 * http://www.lintcode.com/en/problem/binary-tree-paths/
 * @author yzwall
 */
package binarytree;

import java.util.List;
import java.util.ArrayList;

// 解法一：分治法
class Solution10 {
    /**
     * @param root the root of the binary tree
     * @return all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
    	List<String> results = new ArrayList<String>();

    	// 递归出口
    	if (root == null) {
    		return results;
    	}
    	
    	// divide & conquer
    	List<String> leftPaths = binaryTreePaths(root.left);
    	List<String> rightPaths = binaryTreePaths(root.right);
    	
    	// merge leaf
    	if (leftPaths.size() == 0 && rightPaths.size() == 0) {
    		results.add(String.valueOf(root.val));
    		return results;
    	}
    	
    	// merge leftPaths
    	if (leftPaths.size() > 0) {
        	for (String path : leftPaths) {
        		results.add(root.val + "->" + path);
        	}
    	} 

    	// merge rightPaths
    	if (rightPaths.size() > 0) {
        	for (String path : rightPaths) {
        		results.add(root.val + "->" + path);
        	}
    	}
    	
    	return results;
    }
}



// 解法二：递归遍历
//class Solution11 {
//    /**
//     * @param root the root of the binary tree
//     * @return all root-to-leaf paths
//     */
//    public List<String> binaryTreePaths(TreeNode root) {
//    	List<String> results = new ArrayList<String>();
//    	if (root == null) {
//    		return results;
//    	}
//    	
//    	findPaths(root, results);
//    	
//    	return results;
//    }
//
//	private void findPaths(TreeNode root, List<String> results) {
//		
//		if (root == null) {
//			return;
//		}
//		
//		StringBuilder path = new StringBuilder();
//		path.append(root.val)
//		
//	}
//}



public class BinaryTreePaths_480 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
