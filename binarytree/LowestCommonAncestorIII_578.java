/**
 * 一直wa，后续更正
 * 分治法，构造ResultType求二叉树中两个节点的最近公共祖先
 * 注意点：二叉树中节点value不重复，待查找节点可能不在树中，分治突破点在节点的位置
 * http://www.lintcode.com/en/problem/lowest-common-ancestor-iii/
 * @author yzwall
 */
package binarytree;

class Solution17 {
	
	private class ResultType {
		TreeNode root;
		TreeNode lca;
		boolean isContainsA;
		boolean isContainsB;
		
		public ResultType(TreeNode root) {
			this.root = root;
			this.lca = null;
			this.isContainsA = false;
			this.isContainsB = false;
		}
		
		public void print() {
			if (root != null) {
				System.out.println("root: " + root.val);
				String str = lca != null ? String.valueOf(lca.val) : "null";
				System.out.println("lca: " + str);
				System.out.println(isContainsA + " " + isContainsB + "\n");
			}
		}
	}
	
    /**
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {

    	return findLCA(new ResultType(root), A, B).lca;   	
    }
    
    private ResultType findLCA(ResultType result, TreeNode A, TreeNode B) {
    	// 递归出口
    	if (result.root == null) {
    		return result;
    	}
    	
    	// divide & conquer
    	ResultType left = findLCA(new ResultType(result.root.left), A, B);
    	ResultType right = findLCA(new ResultType(result.root.right), A, B);
    	
    	// merge leaf
	    if (result.root == A) {
		    result.isContainsA = true;
		    result.lca = result.root;
		    return result;
	    } else if(result.root == B) {
			result.isContainsB = true;
			result.lca = result.root;
			return result;
	    }
    	
    	/**
    	 * AB在左右子树中：
    	 * 1. AB都在左子树 or 右子树中
    	 * 2. A,B分别在左右 or 右左子树中
    	 */
	    if (left.lca != null && right.lca != null) {
    		result.isContainsA = true;
    		result.isContainsB = true;
    		result.lca = result.root; 
    		return result;
	    } else if (left.lca != null) {
    		result.isContainsA = true;
    		result.lca = left.lca; 
    		return result;	    	
	    } else if (right.lca != null) {
    		result.isContainsB = true;
    		result.lca = right.lca; 
    		return result;	    	
	    }
	    
    	//result.print();
    	return result;
    }
}

public class LowestCommonAncestorIII_578 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
