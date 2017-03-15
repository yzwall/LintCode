/**
 * 分治法+构造合并信息ResultType，将二叉树转换为单链表（斜右二叉树形式）
 * http://www.lintcode.com/en/problem/flatten-binary-tree-to-linked-list/
 * @author yzwall
 */
package binarytree;

class Solution16 {
	
	private class ResultType {
		TreeNode root;
		TreeNode next;
		
		public ResultType(TreeNode root) {
			this.root = root;
			this.next = root;
		}
		
/*		public void print() {
			System.out.println("root: " + root.val);
			if (root.left != null) {
				System.out.println("root.left: " + root.left.val);
			} else {
				System.out.println("root.left: null");
			}
			if (root.right != null) {
				System.out.println("root.right: " + root.right.val);
			} else {
				System.out.println("root.right: null");
			}
			//System.out.println("root.right: " + root.right.val);
			System.out.println("root.leaf: " + next.val + "\n");
		}*/
	}
	
    public void flatten(TreeNode root) {
    	
    	helper(new ResultType(root));
    }
    
    private ResultType helper(ResultType result) {
    	
    	// 递归出口
    	if (result.root == null) {
    		return result;
    	}
    	
    	// divide & conquer
    	ResultType left = helper(new ResultType(result.root.left));
    	ResultType right = helper(new ResultType(result.root.right));
    	
    	// merge
    	if (left.root != null) {
    		result.next = left.next;
    		if (right.root != null) {
    			// 左右子树不空，右子树成为左子树叶节点的右子树并更新next
    			result.next.right = right.root;
    			result.next = right.next;
    		}
    		// 改变左子树为右子树
    		result.root.right = result.root.left;
			result.root.left = null;
    	} else if(right.root != null) {
			result.next = right.next;
    	}
    	
    	//result.print();
    	return result;
    }
}


public class FlattenBinaryTreeToLinkedList_453 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	
	
}
