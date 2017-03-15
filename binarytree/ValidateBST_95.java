/**
 * ���η�������ResultType���ж�һ�Ŷ������Ƿ��Ƕ����������BST��
 * http://www.lintcode.com/en/problem/validate-binary-search-tree/
 * @author yzwall
 */
package binarytree;

class Solution13 {
	
	private class ResultType {
		TreeNode parent;
		TreeNode root;
		boolean isBalanced;
		
		public ResultType(TreeNode root, TreeNode parent) {
			this.parent = parent;
			this.root = root;
			this.isBalanced = true;
		}
	}
	
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
    	
    	return findBST(new ResultType(root, null)).isBalanced;
    }

	private ResultType findBST(ResultType result) {
		
		if (result.root == null) {
			return result;
		}
		
		// divide & conquer
		ResultType left = findBST(new ResultType(result.root.left, result.root));
		ResultType right = findBST(new ResultType(result.root.right, result.root));
		
		// merge
		if (left.root != null || right.root != null) {
			// ���ȼ�����������Ƿ���BST
			if (!left.isBalanced || !right.isBalanced) {
				result.isBalanced = false;
			} else {
				if (left.root != null) {
					// ���������
					if (left.root.val >= result.root.val) {
						result.isBalanced = false;
					} else if (result.parent != null && !isRootLeft(result.parent, result.root)) {
						// ��֤BST�ڵ�����������нڵ� < �ýڵ�
		 				if (left.root.val <= result.parent.val) {
		 					result.isBalanced = false;
		 				}
					}
				}
				if (right.root != null) {
					// ���������
					if (right.root.val <= result.root.val) {
						result.isBalanced = false;
					} else if (result.parent != null && isRootLeft(result.parent, result.root)) {
						// ��֤BST�ڵ�����������нڵ� > �ýڵ�
		 				if (right.root.val >= result.parent.val) {
		 					result.isBalanced = false;
		 				}
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * �ж�һ���ڵ����丸�ڵ�����ӻ����Һ���
	 * @param parent root�ڵ�ĸ��׽ڵ�
	 * @return true/false root���丸�׽ڵ������/�Һ���
	 */
	private boolean isRootLeft(TreeNode parent, TreeNode root) {
		return parent.val > root.val ? true : false;
	}
}


public class ValidateBST_95 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
