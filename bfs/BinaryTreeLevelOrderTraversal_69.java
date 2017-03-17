/**
 * 二叉树层序遍历，BFS保存每一层路径
 * http://www.lintcode.com/en/problem/binary-tree-level-order-traversal/
 * @author yzwall
 */
package bfs;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import binarytree.TreeNode;

class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    	ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    	if (root == null) {
    		return paths;
    	}
    	
    	Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
    	queue.offer(root);
    	while (!queue.isEmpty()) {
    		ArrayList<Integer> path = new ArrayList<Integer>();
    		// size 当前遍历层的节点个数
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
        		TreeNode front = queue.poll();
        		path.add(front.val);
        		
        		if (front.left != null) {
        		    queue.offer(front.left);
        		}
        		if (front.right != null) {
        		    queue.offer(front.right);
        		}
    		}
    		paths.add(new ArrayList<Integer>(path));
    	}
    	return paths;
    }
}

public class BinaryTreeLevelOrderTraversal_69 {
	public static void main(String[] args) {
	}
}
