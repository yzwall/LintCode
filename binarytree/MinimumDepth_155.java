/**
 * 分治法求二叉树最小深度
 * http://www.lintcode.com/en/problem/minimum-depth-of-binary-tree/
 * @author yzwall
 */
package binarytree;


class Solution8 {
    /**
     * 求出以root为根的二叉树最小深度
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int minDepth(TreeNode root) {
    	int minDepth = 0;
    	
    	// 递归出口
    	if (root == null) {
    		return minDepth;
    	}
    	
    	// divide & conquer
    	int leftMinDepth = minDepth(root.left);
    	int rightMinDepth = minDepth(root.right);
    	
    	// merge
    	if (leftMinDepth == 0 && rightMinDepth > 0) {
    		// 左子树为空，当前树最小深度 = 右子树最小深度+根节点深度1
    	    minDepth = rightMinDepth + 1;
    	} else if(rightMinDepth == 0 && leftMinDepth > 0) {
    		// 右子树为空，当前树最小深度 = 左子树最小深度+根节点深度1
    	    minDepth = leftMinDepth + 1;
    	} else {
    		// root为leaf节点 or 左右子树均不空
    	    minDepth = Math.min(leftMinDepth, rightMinDepth) + 1;
    	}
    	
    	return minDepth;
    }
}


public class MinimumDepth_155 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
