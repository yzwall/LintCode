/**
 * n���ڵ㣬n-1���ߣ�BFS����޻������Ȼ��һ����
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
    	// ��֤n�������n-1����
    	if (n == 0 || edges.length != n - 1) {
    		return false;
    	}
    	
    	// ����ͼ����HashSet����ȥ��
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
    	
    	// ����Ƿ��л���
    	// ��n-1���ߵ�ǰ���£�����л���Ȼ�й����ڵ㲻����ͨ�����У�nodeSet.size() < n
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
