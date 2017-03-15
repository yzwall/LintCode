/**
 * һֱwa����������
 * ���η�������ResultType��������������ڵ�������������
 * ע��㣺�������нڵ�value���ظ��������ҽڵ���ܲ������У�����ͻ�Ƶ��ڽڵ��λ��
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
    	// �ݹ����
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
    	 * AB�����������У�
    	 * 1. AB���������� or ��������
    	 * 2. A,B�ֱ������� or ����������
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
