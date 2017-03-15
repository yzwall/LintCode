/**
 * ���η��������������
 * http://www.lintcode.com/en/problem/maximum-depth-of-binary-tree/
 * @author yzwall
 */
package binarytree;


class Solution7 {
    /**
     * �����rootΪ���Ķ�����������
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
    	int maxDepth = 0;
    	
    	// �ݹ����
    	if (root == null) {
    		return maxDepth;
    	}
    	
    	// divide & conquer
    	int leftMaxDepth = maxDepth(root.left);
    	int rightMaxDepth = maxDepth(root.right);
    	
    	/**
    	 * merge
    	 * + 1�������ݹ��Ե����ϸ���maxDepth
    	 */
    	maxDepth = Math.max(leftMaxDepth, rightMaxDepth) + 1;
    	return maxDepth;
    }
}

public class MaximumDepth_97 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
