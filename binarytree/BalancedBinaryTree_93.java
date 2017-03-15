/**
 * ���η�������ResultType, �ж�һ�Ŷ������Ƿ�ƽ��
 * http://www.lintcode.com/en/problem/balanced-binary-tree/
 * @author yzwall
 */

package binarytree;


class Solution11 {
	
   private class ResultType {
	   public TreeNode root;
	   // ��ǰ�������Ƿ�ƽ��
	   public boolean isBalanced;
	   // ������������
	   public int maxDepth;
	   
	   // ��ʼ��
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
	   
	   // �ݹ����
	   if (result.root == null) {
		   return result; 
	   }
	   
	   // divide & conquer
	   ResultType leftResult = findBalanced(new ResultType(result.root.left));
	   ResultType rightResult = findBalanced(new ResultType(result.root.right));
	   
	   /**
	    * merge������
	    * 1. ����������ƽ�� || ��������ƽ�⣬��Ȳ�>1�����������Ŷ�������ƽ��
	    * 2. ��������ƽ�� && ����������Ȳ� <=1, merge��������ƽ����Ϣ 
	    */
	   if (!leftResult.isBalanced || !rightResult.isBalanced) {
		   // ����������ƽ�⣬result.root��ƽ��
		   result.isBalanced = false;
	   } else {
		   // ��������ƽ�⣬result.root��ƽ��
		   if (Math.abs(leftResult.maxDepth - rightResult.maxDepth) > 1) {
			   result.isBalanced = false;
		   } else {
			   // result.rootƽ��
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
