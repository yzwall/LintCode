/**
 * 二叉树的序列化（树结构->字符串）与反序列化（字符串->树结构）
 * 利用完全二叉树节点编号思想
 * http://www.lintcode.com/en/problem/binary-tree-serialization/
 * @author yzwall
 */
package bfs;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import binarytree.TreeNode;

class Solution1 {
	
	private class NodeInfo {
		// 树节点
		TreeNode root;
		// 节点在完全二叉树中的标号
		int index;
		public NodeInfo(TreeNode root, int index) {
			this.root = root;
			this.index = index;
		}
	}
	
	// 序列化（树结构->字符串）
    public String serialize(TreeNode root) {
    	StringBuilder treeSerial = new StringBuilder();
    	if (root == null) {
    		return new String(treeSerial);
    	}
    	
    	int[] indexList = new int[500];
    	for (int i=0; i < indexList.length; i++) {
    		indexList[i] = Integer.MAX_VALUE;
    	}
    	int maxIndex = 0;
    	Queue<NodeInfo> queue = new ArrayDeque<>();
    	queue.offer(new NodeInfo(root, 0));
    	
    	/**
    	 * 层序遍历树，按照完全二叉树形式记录树的编号
    	 * 节点编号k，对应左孩子编号为2k+1，右孩子编号为2k+2
    	 */
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
        		NodeInfo head = queue.poll();
        		indexList[head.index] = head.root.val;
        		maxIndex = Math.max(maxIndex, head.index);
        		
        		if (head.root.left != null) {
        			queue.offer(new NodeInfo(head.root.left, 2 * head.index + 1));
        		} else {
        			indexList[2 * head.index + 1] = Integer.MIN_VALUE;
        			maxIndex = Math.max(maxIndex, 2 * head.index + 1);
        		}
        		if (head.root.right != null) {
        			queue.offer(new NodeInfo(head.root.right, 2 * head.index + 2));
        		} else {
        			indexList[2 * head.index + 2] = Integer.MIN_VALUE;
        			maxIndex = Math.max(maxIndex, 2 * head.index + 2);
        		}
    		}
    	}
    	
    	// 将完全二叉树编号列表转换为序列字符串
    	for (int i = 0; i <= maxIndex; i++) {
    		if (indexList[i] == Integer.MIN_VALUE) {
    			treeSerial.append("," + "#");
    		} else if(indexList[i] == Integer.MAX_VALUE) {
    			treeSerial.append("," + "*");
    		} else {
    			if (i > 0) {
    				treeSerial.append(",");
    			}
    			treeSerial.append(String.valueOf(indexList[i]));
    		}
    	}
    	
    	return new String(treeSerial);
    }
    
    // 反序列化（字符串->树结构）
    public TreeNode deserialize(String data) {
    	
    	if (data == null || data.length() == 0) {
    		return null;
    	}
    	
    	// 解析序列字符串为完全二叉树编号列表
		String[] dataArray = data.split(",");
		List<Integer> indexList = new ArrayList<>(dataArray.length);
		Queue<NodeInfo> queue = new ArrayDeque<>();
		for (int i = 0; i < dataArray.length; i++) {
			if (dataArray[i].equals("#") || dataArray[i].equals("*")) {
				indexList.add(Integer.MIN_VALUE);
			} else {
				indexList.add(Integer.parseInt(dataArray[i]));
			}
		}
		
		TreeNode root = new TreeNode(indexList.get(0));
		queue.offer(new NodeInfo(root, 0));
		while (!queue.isEmpty()) {
			NodeInfo head = queue.poll();
			int leftIndex = 2 * head.index + 1;
			int rightIndex = 2 * head.index + 2;
			// 左孩子存在，创建左孩子，入队列
			if (isLegal(indexList, leftIndex)) { 
				TreeNode left = new TreeNode(indexList.get(leftIndex));
				head.root.left = left;
				queue.offer(new NodeInfo(left, leftIndex));
			}
			// 右孩子存在，创建右孩子，入队列
			if (isLegal(indexList, rightIndex)) { 
				TreeNode right = new TreeNode(indexList.get(rightIndex));
				head.root.right = right;
				queue.offer(new NodeInfo(right, rightIndex));
			}		
		}
		return root;
    }
    
    // 判断编号为index的节点在树结构中是否存在
    private boolean isLegal(List<Integer> indexList, int index) {
    	if (index < indexList.size() && indexList.get(index) != Integer.MIN_VALUE) {
    		return true;
    	}
    	return false;
    }
}

public class BinaryTreeSerialization_7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
