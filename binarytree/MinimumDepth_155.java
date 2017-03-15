/**
 * ���η����������С���
 * http://www.lintcode.com/en/problem/minimum-depth-of-binary-tree/
 * @author yzwall
 */
package binarytree;


class Solution8 {
    /**
     * �����rootΪ���Ķ�������С���
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int minDepth(TreeNode root) {
    	int minDepth = 0;
    	
    	// �ݹ����
    	if (root == null) {
    		return minDepth;
    	}
    	
    	// divide & conquer
    	int leftMinDepth = minDepth(root.left);
    	int rightMinDepth = minDepth(root.right);
    	
    	// merge
    	if (leftMinDepth == 0 && rightMinDepth > 0) {
    		// ������Ϊ�գ���ǰ����С��� = ��������С���+���ڵ����1
    	    minDepth = rightMinDepth + 1;
    	} else if(rightMinDepth == 0 && leftMinDepth > 0) {
    		// ������Ϊ�գ���ǰ����С��� = ��������С���+���ڵ����1
    	    minDepth = leftMinDepth + 1;
    	} else {
    		// rootΪleaf�ڵ� or ��������������
    	    minDepth = Math.min(leftMinDepth, rightMinDepth) + 1;
    	}
    	
    	return minDepth;
    }
}


public class MinimumDepth_155 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
