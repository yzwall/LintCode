/**
 * ���η� ����ResultType �������������������г���
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
    	// �ݹ����
    	if (result.root == null) {
    		return result;
    	}
    	
    	// divide & conquer
    	ResultType left = findLCS(new ResultType(result.root.left));
    	ResultType right = findLCS(new ResultType(result.root.right));
    	
    	// merge Ҷ�ڵ�
    	if (left.root == null && right.root == null) {
    		result.maxLen++;
    	} 
    	else {
    		// merge���left LCS����
    		int tempLeftLen = 0;
    		
    		// merge���right LCS����
    		int tempRightLen = 0;
    		
    		// merge ������LCS
    		if (left.root != null) {
    			// result.root����left����
    			if(result.root.val + 1 == left.root.val) {
        			if (left.start == left.root) {
        				// left.root��left LCS��Ա
        				tempLeftLen = left.maxLen + 1;
        			} else {
        				tempLeftLen = 2;
        				// result.root����󣬱Ƚ�<root, left>��left LCS���ȣ�ȡ�ϴ���
        				tempLeftLen = Math.max(tempLeftLen, left.maxLen);
        			}
    			} else {
    				// result.root������left����
    				tempLeftLen = left.maxLen;
    			}
    		}
    		
    		// merge ������LCS
    		if (right.root != null) {
    			if (result.root.val + 1 == right.root.val) {
        			// �ж�right.root�Ƿ���LCS��Ա
        			if (right.start == right.root) {
        				tempRightLen = right.maxLen + 1;
        			} else {
        				tempRightLen = 2;
        				// root����󣬱Ƚ�<root, right>��right LCS���ȣ�ȡ�ϴ���
        				tempRightLen = Math.max(tempRightLen, right.maxLen);
        			}	
    			} else {
    				// result.root������right����
    				tempRightLen = right.maxLen;
    			}
    		}

    		// merge ��������
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
