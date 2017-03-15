/**
 * 分治法求二叉树最大深度
 * http://www.lintcode.com/en/problem/maximum-depth-of-binary-tree/
 * @author yzwall
 */
package binarytree;


class Solution7 {
    /**
     * 求出以root为根的二叉树最大深度
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
    	int maxDepth = 0;
    	
    	// 递归出口
    	if (root == null) {
    		return maxDepth;
    	}
    	
    	// divide & conquer
    	int leftMaxDepth = maxDepth(root.left);
    	int rightMaxDepth = maxDepth(root.right);
    	
    	/**
    	 * merge
    	 * + 1操作，递归自底向上更新maxDepth
    	 */
    	maxDepth = Math.max(leftMaxDepth, rightMaxDepth) + 1;
    	return maxDepth;
    }
}

public class MaximumDepth_97 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
