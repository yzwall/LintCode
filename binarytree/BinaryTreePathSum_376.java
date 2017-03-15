/**
 * 分治法+回溯操作+构造合并信息ResultType，找出二叉树中所有路径和等于target的路径
 * http://www.lintcode.com/en/problem/binary-tree-path-sum/
 * @author yzwall
 */


package binarytree;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class Solution15 {
	// merge信息
	private class ResultType {
		TreeNode root;
		List <Integer> path;
		List<List<Integer>> paths;
		
		public ResultType(TreeNode root) {
			this.root = root;
			// LinkedList插入效率比ArrayList高
			this.path = new LinkedList<Integer>();
			this.paths = new ArrayList<List<Integer>>();
		}
	}
	
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
    	
    	return findPaths(new ResultType(root), target).paths;
    }
    
    	
	private ResultType findPaths(ResultType result, int target) {
		
		// 递归出口
		if (result.root == null) {
			return result;
		}
		
		/**
		 * divide & conquer
		 * 分别交给左子树和右子树去搜索路径和等于target - root.val的所有路径
		 */
		ResultType left = findPaths(new ResultType(result.root.left), target - result.root.val);
		ResultType right = findPaths(new ResultType(result.root.right), target - result.root.val);
		
		// 试探性添加进路径
		result.path.add(result.root.val);
		
		// merge leaf
		if (left.root == null && right.root == null) {
			// merge 合法路径
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
				// 将root.val插入到left.path表头，确保from root to child顺序
				tPath.add(0, result.root.val);
				// merge paths
				result.paths.add(new ArrayList<>(tPath));
			}
		}
		
		// merge right paths
		if (right.root != null) {
			for (int i = 0; i < right.paths.size(); i++) {
				ArrayList<Integer> tPath = new ArrayList<>(right.paths.get(i));
				// 将root.val插入到right.path表头，确保from root to child顺序
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
