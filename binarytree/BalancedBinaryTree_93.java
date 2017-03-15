/**
 * 分治法，构造ResultType, 判断一颗二叉树是否平衡
 * http://www.lintcode.com/en/problem/balanced-binary-tree/
 * @author yzwall
 */

package binarytree;


class Solution11 {
	
   private class ResultType {
	   public TreeNode root;
	   // 当前二叉树是否平衡
	   public boolean isBalanced;
	   // 二叉树最大深度
	   public int maxDepth;
	   
	   // 初始化
	   public ResultType (TreeNode root) {
		   this.root = root;
		   this.isBalanced = true;
		   this.maxDepth = 0;
	   }
   }
	
/**
* @param root: The root of binary tree.
* @return: True if this Binary tree is Balanced, or false.
*/
   public boolean isBalanced(TreeNode root) {
	   if (root == null) {
		   return true;
	   }
	   
	   return findBalanced(new ResultType(root)).isBalanced; 
   }

   
   private ResultType findBalanced(ResultType result) {
	   
	   // 递归出口
	   if (result.root == null) {
		   return result; 
	   }
	   
	   // divide & conquer
	   ResultType leftResult = findBalanced(new ResultType(result.root.left));
	   ResultType rightResult = findBalanced(new ResultType(result.root.right));
	   
	   /**
	    * merge操作，
	    * 1. 左右子树不平衡 || 左右子树平衡，深度差>1，都导致整颗二叉树不平衡
	    * 2. 左右子树平衡 && 左右子树深度差 <=1, merge左右子树平衡信息 
	    */
	   if (!leftResult.isBalanced || !rightResult.isBalanced) {
		   // 左右子树不平衡，result.root不平衡
		   result.isBalanced = false;
	   } else {
		   // 左右子树平衡，result.root不平衡
		   if (Math.abs(leftResult.maxDepth - rightResult.maxDepth) > 1) {
			   result.isBalanced = false;
		   } else {
			   // result.root平衡
			   result.isBalanced = true;
			   result.maxDepth = leftResult.maxDepth > rightResult.maxDepth ? leftResult.maxDepth : rightResult.maxDepth;
			   result.maxDepth++;
		   }
	   } 
	   
	   return result;
   }	
}



public class BalancedBinaryTree_93 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
