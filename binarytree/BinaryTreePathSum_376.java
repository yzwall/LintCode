/**
 * ���η�+���ݲ���+����ϲ���ϢResultType���ҳ�������������·���͵���target��·��
 * http://www.lintcode.com/en/problem/binary-tree-path-sum/
 * @author yzwall
 */


package binarytree;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class Solution15 {
	// merge��Ϣ
	private class ResultType {
		TreeNode root;
		List <Integer> path;
		List<List<Integer>> paths;
		
		public ResultType(TreeNode root) {
			this.root = root;
			// LinkedList����Ч�ʱ�ArrayList��
			this.path = new LinkedList<Integer>();
			this.paths = new ArrayList<List<Integer>>();
		}
	}
	
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
    	
    	return findPaths(new ResultType(root), target).paths;
    }
    
    	
	private ResultType findPaths(ResultType result, int target) {
		
		// �ݹ����
		if (result.root == null) {
			return result;
		}
		
		/**
		 * divide & conquer
		 * �ֱ𽻸���������������ȥ����·���͵���target - root.val������·��
		 */
		ResultType left = findPaths(new ResultType(result.root.left), target - result.root.val);
		ResultType right = findPaths(new ResultType(result.root.right), target - result.root.val);
		
		// ��̽����ӽ�·��
		result.path.add(result.root.val);
		
		// merge leaf
		if (left.root == null && right.root == null) {
			// merge �Ϸ�·��
			if (result.root.val == target) {
				result.paths.add(new ArrayList<Integer>(result.path));
			} 
			
			// backtracking from leaf
			result.path.remove(result.path.size() - 1);
		}		
		
		// merge left paths
		if (left.root != null) {
			for (int i = 0; i < left.paths.size(); i++) {
				ArrayList<Integer> tPath = new ArrayList<>(left.paths.get(i));
				// ��root.val���뵽left.path��ͷ��ȷ��from root to child˳��
				tPath.add(0, result.root.val);
				// merge paths
				result.paths.add(new ArrayList<>(tPath));
			}
		}
		
		// merge right paths
		if (right.root != null) {
			for (int i = 0; i < right.paths.size(); i++) {
				ArrayList<Integer> tPath = new ArrayList<>(right.paths.get(i));
				// ��root.val���뵽right.path��ͷ��ȷ��from root to child˳��
				tPath.add(0, result.root.val);
				// merge paths
				result.paths.add(new ArrayList<>(tPath));
			}	
		}	
		
		return result;
	}
}


public class BinaryTreePathSum_376 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
