/**
 * 分治法 构造ResultType 求二叉树中最长递增有序列长度
 * http://www.lintcode.com/en/problem/binary-tree-longest-consecutive-sequence/
 * @author yzwall
 */
package binarytree;

class Solution14 {
	
	private class ResultType {
		TreeNode root;
		TreeNode start;
		int maxLen;
		
		public ResultType(TreeNode root) {
			this.root = root;
			this.start = root;
			this.maxLen = 0;
		}
	} 
	
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
        // Write your code here
    	return findLCS(new ResultType(root)).maxLen;
    }
    
    private ResultType findLCS(ResultType result) {
    	// 递归出口
    	if (result.root == null) {
    		return result;
    	}
    	
    	// divide & conquer
    	ResultType left = findLCS(new ResultType(result.root.left));
    	ResultType right = findLCS(new ResultType(result.root.right));
    	
    	// merge 叶节点
    	if (left.root == null && right.root == null) {
    		result.maxLen++;
    	} 
    	else {
    		// merge后的left LCS长度
    		int tempLeftLen = 0;
    		
    		// merge后的right LCS长度
    		int tempRightLen = 0;
    		
    		// merge 左子树LCS
    		if (left.root != null) {
    			// result.root加入left序列
    			if(result.root.val + 1 == left.root.val) {
        			if (left.start == left.root) {
        				// left.root是left LCS成员
        				tempLeftLen = left.maxLen + 1;
        			} else {
        				tempLeftLen = 2;
        				// result.root加入后，比较<root, left>与left LCS长度，取较大者
        				tempLeftLen = Math.max(tempLeftLen, left.maxLen);
        			}
    			} else {
    				// result.root不加入left序列
    				tempLeftLen = left.maxLen;
    			}
    		}
    		
    		// merge 右子树LCS
    		if (right.root != null) {
    			if (result.root.val + 1 == right.root.val) {
        			// 判断right.root是否是LCS成员
        			if (right.start == right.root) {
        				tempRightLen = right.maxLen + 1;
        			} else {
        				tempRightLen = 2;
        				// root加入后，比较<root, right>与right LCS长度，取较大者
        				tempRightLen = Math.max(tempRightLen, right.maxLen);
        			}	
    			} else {
    				// result.root不加入right序列
    				tempRightLen = right.maxLen;
    			}
    		}

    		// merge 左右子树
    		if (tempLeftLen > tempRightLen) {
    			if (tempLeftLen == left.maxLen) {
    				result.start = left.start;
    			}
    			result.maxLen = tempLeftLen;
    		} else {
    			if (tempRightLen == right.maxLen) {
    				result.start = right.start;
    			}
    			result.maxLen = tempRightLen;
    		}
    	}
    	
    	return result;
    }
}


public class LongestConsecutive_595 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
