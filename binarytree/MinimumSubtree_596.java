/**
 * 分治法，构造ResultType计算最小子树的根节点
 * http://www.lintcode.com/en/problem/minimum-subtree/
 * @author yzwall
 */
package binarytree;

class Solution9 {
	
	// ResultType
	private class ResultType {
		// 二叉树最小子树的根节点
		public TreeNode root; 
		// 二叉树节点总和
		public int totalSum;
		// 二叉树的最小子树和
		public int minSum;
		
		public ResultType(TreeNode root) {
			this.root = root;
			if (root != null) {
				this.totalSum = root.val;
				this.minSum = root.val;
			} else {
				// 空节点不存在子树，最小子树和
				this.totalSum = 0;
				this.minSum = 0;
			}
		}
	}
	
	/**
     * @param root the root of binary tree
     * @return the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
    	
    	if (root == null) {
    		return null;
    	}
    	
    	ResultType result =  findMinSum(new ResultType(root));
    	return result.root;
    }
    
    /**
     * 找到以result.root为根部的二叉树的最小子树
     * @param result
     * @return
     */
    private ResultType findMinSum(ResultType result) {
    	
    	if (result.root == null) {
    		return new ResultType(null);
    	}
    	
    	// divide & conquer
    	ResultType leftResult = findMinSum(new ResultType(result.root.left));
    	ResultType rightResult = findMinSum(new ResultType(result.root.right));
    	
    	/**
    	 * merge: 计算当前子树节点总和
    	 * 默认最小子树和 = 树节点总和
    	 * result.minSum = min(result.minSum, leftResult.minSum, rightResult.minSum)
    	 */
    	result.totalSum += leftResult.totalSum + rightResult.totalSum;
    	result.minSum = result.totalSum;
    	
    	TreeNode tempMinNode;
    	int tempMinSum;
    	if (leftResult.root != null && rightResult.root == null) {
    		// 只有右子树为空，少一次比较
    		tempMinNode = leftResult.root;
        	tempMinSum = leftResult.minSum;
    	} else if (leftResult.root == null && rightResult.root != null) {
    		// 只有左子树为空，少一次比较
    		tempMinNode = rightResult.root;
        	tempMinSum = rightResult.minSum;
    	} else if (leftResult.root != null && rightResult.root != null){
    		// 计算左右子树中的最小子树根节点
    		tempMinNode = leftResult.minSum < rightResult.minSum ? leftResult.root : rightResult.root;
    		
    		// 计算左右子树中的最小子树和
    		tempMinSum = leftResult.minSum < rightResult.minSum ? leftResult.minSum : rightResult.minSum;
    	} else {
    		// 叶节点 不作merge
    		return result;
    	}
    	
    	// merge: 合并左右子树和根节点的最小子树和
    	if (result.minSum > tempMinSum) {
    		result.root = tempMinNode;
    		result.minSum = tempMinSum;
    	}
    	
    	return result;
    }
}


public class MinimumSubtree_596 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
