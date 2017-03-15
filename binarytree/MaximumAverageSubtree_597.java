/**
 * ���η�������ResultType����Сƽ������
 * http://www.lintcode.com/en/problem/subtree-with-maximum-average/
 * @author yzwall
 */

package binarytree;

import javax.swing.RootPaneContainer;

class Solution12 {
	
	private class ResultType {
		public TreeNode root;
		public int totalSum;
		public int totalNum;
		public int maxAverSum;
		public int maxAverNum;
		
		public ResultType(TreeNode root) {
			this.root = root;
			this.totalSum = 0;
			this.totalNum = 0;
			this.maxAverSum = 0;
			this.maxAverNum = 0;
		}
		
		// ���¶�����maxAver��Ϣ
		public void update(ResultType x) {
			this.root = x.root;
			this.maxAverSum = x.maxAverSum;
			this.maxAverNum = x.maxAverNum;
		}
	}
	
    /**
     * @param root the root of binary tree
     * @return the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
    	if (root == null) {
    		return null;
    	} 
    	
    	return findMaxAverSubtree(new ResultType(root)).root;
    }

	private ResultType findMaxAverSubtree(ResultType result) {
		
		// �ݹ����
		if (result.root == null) {
			return result;
		}
		
		// divide & conquer
		ResultType left = findMaxAverSubtree(new ResultType(result.root.left));
		ResultType right = findMaxAverSubtree(new ResultType(result.root.right));
		
		// merge ��������total��Ϣ
		result.totalSum += left.totalSum + right.totalSum + result.root.val;
		result.totalNum += left.totalNum + right.totalNum + 1;
		result.maxAverSum = result.totalSum;
		result.maxAverNum = result.totalNum;
		
		// �����merge��������maxAver��Ϣ
		
		// merge Ҷ�ڵ�
		if (left.root == null && right.root == null) {
			result.maxAverSum = result.totalSum;
			result.maxAverNum = result.totalNum;
		} else if (left.root == null && right.root != null) {
			// merge ������
			if (isLess(result, right)) {
				result.update(right);
			}
		} else if (left.root != null && right.root == null) {
			// merge ������
			if (isLess(result, left)) {
				result.update(left);
			}		
		} else {
			// merge ��������
			ResultType temp = isLess(left, right) ? right : left;
			if (isLess(result, temp)) {
				result.update(temp);
			}
		}
		
		return result;
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isLess(ResultType x, ResultType y) {
		if (x.maxAverSum == 0) {
			return true;
		}
		return x.maxAverSum * y.maxAverNum < y.maxAverSum * x.maxAverNum ? true : false;
	}
}

public class MaximumAverageSubtree_597 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
