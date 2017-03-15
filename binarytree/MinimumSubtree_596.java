/**
 * ���η�������ResultType������С�����ĸ��ڵ�
 * http://www.lintcode.com/en/problem/minimum-subtree/
 * @author yzwall
 */
package binarytree;

class Solution9 {
	
	// ResultType
	private class ResultType {
		// ��������С�����ĸ��ڵ�
		public TreeNode root; 
		// �������ڵ��ܺ�
		public int totalSum;
		// ����������С������
		public int minSum;
		
		public ResultType(TreeNode root) {
			this.root = root;
			if (root != null) {
				this.totalSum = root.val;
				this.minSum = root.val;
			} else {
				// �սڵ㲻������������С������
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
     * �ҵ���result.rootΪ�����Ķ���������С����
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
    	 * merge: ���㵱ǰ�����ڵ��ܺ�
    	 * Ĭ����С������ = ���ڵ��ܺ�
    	 * result.minSum = min(result.minSum, leftResult.minSum, rightResult.minSum)
    	 */
    	result.totalSum += leftResult.totalSum + rightResult.totalSum;
    	result.minSum = result.totalSum;
    	
    	TreeNode tempMinNode;
    	int tempMinSum;
    	if (leftResult.root != null && rightResult.root == null) {
    		// ֻ��������Ϊ�գ���һ�αȽ�
    		tempMinNode = leftResult.root;
        	tempMinSum = leftResult.minSum;
    	} else if (leftResult.root == null && rightResult.root != null) {
    		// ֻ��������Ϊ�գ���һ�αȽ�
    		tempMinNode = rightResult.root;
        	tempMinSum = rightResult.minSum;
    	} else if (leftResult.root != null && rightResult.root != null){
    		// �������������е���С�������ڵ�
    		tempMinNode = leftResult.minSum < rightResult.minSum ? leftResult.root : rightResult.root;
    		
    		// �������������е���С������
    		tempMinSum = leftResult.minSum < rightResult.minSum ? leftResult.minSum : rightResult.minSum;
    	} else {
    		// Ҷ�ڵ� ����merge
    		return result;
    	}
    	
    	// merge: �ϲ����������͸��ڵ����С������
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
