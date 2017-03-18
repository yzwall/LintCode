/**
 * ���������������BFS����ÿһ��·��
 * BFS�㼶����ģ�����
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
    	ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();
    	if (root == null) {
    		return levels;
    	}
    	
    	Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
    	queue.offer(root);
    	while (!queue.isEmpty()) {
    		ArrayList<Integer> level = new ArrayList<Integer>();
    		// size ��ǰ������Ľڵ����
    		int size = queue.size();
    		// ������ǰ��
    		for (int i = 0; i < size; i++) {
        		TreeNode head = queue.poll();
        		level.add(head.val);
        		
        		if (head.left != null) {
        		    queue.offer(head.left);
        		}
        		if (head.right != null) {
        		    queue.offer(head.right);
        		}
    		}
    		levels.add(new ArrayList<Integer>(level));
    	}
    	return levels;
    }
}

public class BinaryTreeLevelOrderTraversal_69 {
	public static void main(String[] args) {
	}
}
