/**
 * http://www.lintcode.com/en/problem/sequence-reconstruction/
 * BFS求拓扑排序，判断是否存在唯一拓扑排序序列， 判断方法是队列中有且只能有一个入度为0的节点
 * @author yzwall
 */
package bfs;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

class Solution15 {
	private class Node {
		int val;
		ArrayList<Node> neighbors;
		public Node(int val) {
			this.val = val;
			this.neighbors = new ArrayList<>();
		}
	} 
	
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
    	if (org.length == 0 && seqs[0].length == 0) {
    	    return true;
    	}
    	if (org == null || org.length == 0) {
    		return false;
    	}
    	if (seqs == null || seqs.length == 0 ||seqs[0].length == 0) {
    		return false;
    	}
    	HashMap<Node, Integer> indegree = new HashMap<>();
    	ArrayList<Node> graph = new ArrayList<>();
    	ArrayList<Node> topOrder = new ArrayList<>();
    	ArrayList<Integer> order = new ArrayList<>();
    	
    	int n = org.length;
    	graph.add(new Node(0));
    	for (int i = 1; i <= n; i++) {
    		graph.add(new Node(i));
    		indegree.put(graph.get(i), 0);
    	}
    	
    	for (int i = 0; i < seqs.length; i++) {
    	    if (seqs[i].length == 1 && !isValid(seqs[i][0], n)) {
    	        return false;
    	    }
    		for (int k = 0; k < seqs[i].length - 1; k++) {
    			if (!isValid(seqs[i][k], n) || !isValid(seqs[i][k + 1], n)) {
    				return false;
    			}
    			Node u = graph.get(seqs[i][k]);
    			Node v = graph.get(seqs[i][k + 1]);
    			if (!u.neighbors.contains(v)) {
        			u.neighbors.add(v);
        			indegree.put(v, indegree.get(v) + 1);
    			}
    		}
    	}
    	
    	Queue<Node> queue = new ArrayDeque<>();
    	for (int i = 1; i <= n; i++) {
    		Node node = graph.get(i);
    		if (indegree.get(node) == 0) {
    			topOrder.add(node);
    			order.add(node.val);
    			queue.offer(node);
    		}
    	}
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		if (size > 1) {
    			return false;
    		}
    		for (int i = 0; i < size; i++) {
    			Node head = queue.poll();
        		for (Node neighbor : head.neighbors) {
        			int temp = indegree.get(neighbor);
        			indegree.put(neighbor, --temp);
        			if (temp == 0) {
        				queue.offer(neighbor);
        				order.add(neighbor.val);
        			}
        		}
    		}
    	}
    	
    	return order.size() == n && isEqualOrg(order, org);
    }
    
    boolean isValid(int i, int n) {
        return i >= 1 && i <= n;
    }
    
    
    boolean isEqualOrg(ArrayList<Integer>order, int[] org) {
    	for (int i = 0; i < order.size(); i++) {
    		if (order.get(i) != org[i]) {
    			return false;
    		}
    	}
    	return true;
    }
}



public class SequenceReconstruction_605 {

	public static void main(String[] args) {
		int[] org = new int[]{5,3,2,4,1};
		int[][] seqs = new int[][]{{5,3,2,4}, {4,1}, {1}, {3}, {2,4}, {1000000000}};
		
		System.out.println(new Solution15().sequenceReconstruction(org, seqs));
	}

}
