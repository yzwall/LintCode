/**
 * ���η� & DFS�ݹ�������ҳ����ж������е�·��(root->leaf)
 * http://www.lintcode.com/en/problem/binary-tree-paths/
 * @author yzwall
 */
package binarytree;

import java.util.List;
import java.util.ArrayList;

// �ⷨһ�����η�
class Solution10 {
    /**
     * @param root the root of the binary tree
     * @return all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
    	List<String> results = new ArrayList<String>();

    	// �ݹ����
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



// �ⷨ�����ݹ����
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
