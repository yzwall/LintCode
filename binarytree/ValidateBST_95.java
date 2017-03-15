/**
 * 分治法，构造ResultType，判断一颗二叉树是否是二叉查找树（BST）
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
			// 优先检查左右子树是否是BST
			if (!left.isBalanced || !right.isBalanced) {
				result.isBalanced = false;
			} else {
				if (left.root != null) {
					// 检查左子树
					if (left.root.val >= result.root.val) {
						result.isBalanced = false;
					} else if (result.parent != null && !isRootLeft(result.parent, result.root)) {
						// 保证BST节点的左子树所有节点 < 该节点
		 				if (left.root.val <= result.parent.val) {
		 					result.isBalanced = false;
		 				}
					}
				}
				if (right.root != null) {
					// 检查右子树
					if (right.root.val <= result.root.val) {
						result.isBalanced = false;
					} else if (result.parent != null && isRootLeft(result.parent, result.root)) {
						// 保证BST节点的右子树所有节点 > 该节点
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
	 * 判断一个节点是其父节点的左孩子还是右孩子
	 * @param parent root节点的父亲节点
	 * @return true/false root是其父亲节点的左孩子/右孩子
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
