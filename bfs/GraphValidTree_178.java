/**
 * n个节点，n-1条边，BFS检查无环，则必然是一颗树
 * http://www.lintcode.com/en/problem/graph-valid-tree/
 * @author yzwall
 */
package bfs;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;
class Solution7 {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
    	// 保证n个顶点和n-1条边
    	if (n == 0 || edges.length != n - 1) {
    		return false;
    	}
    	
    	// 无向图，用HashSet顶点去重
    	Set<Integer> nodeSet = new HashSet<>();
    	Queue<Integer> queue = new ArrayDeque<>();
    	Map<Integer, Set<Integer>> graph = new HashMap<>();
    	for (int i = 0; i < n; i++) {
    		graph.put(i, new HashSet<Integer>());
    	}
    	for (int i = 0; i < edges.length; i++) {
    		int u = edges[i][0];
    		int v = edges[i][1];
    		graph.get(u).add(v);
    		graph.get(v).add(u);
    	}
    	
    	// 检查是否有环，
    	// 在n-1条边的前提下，如果有环必然有孤立节点不在连通分量中，nodeSet.size() < n
    	queue.offer(0);
    	nodeSet.add(0);
    	while (!queue.isEmpty()) {
    		int head = queue.poll();
    		for (int node : graph.get(head)) {
    			if (!nodeSet.contains(node)) {
    				nodeSet.add(node);
    				queue.offer(node);
    			}
    		}
    	}
    	
    	return nodeSet.size() == n;
    }
}

public class GraphValidTree_178 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
