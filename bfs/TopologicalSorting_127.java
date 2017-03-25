/**
 * 拓扑排序，BFS实现
 * http://www.lintcode.com/en/problem/topological-sorting/
 * @author yzwall
 */
package bfs;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;

class Solution5 {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
    	
    	HashMap<DirectedGraphNode, Integer> indegree = new HashMap<>();
    	ArrayDeque<DirectedGraphNode> queue = new ArrayDeque<>();
    	ArrayList<DirectedGraphNode> topOrder = new ArrayList<>();
    	
    	// 默认所有节点的入度都为0
    	for (DirectedGraphNode node : graph) {
    		indegree.put(node, 0);
    	}
    	
    	// 根据有向图记录每个节点的入度
    	for (DirectedGraphNode node : graph) {
    		for (DirectedGraphNode neighbor : node.neighbors) {
    			indegree.put(neighbor, indegree.get(neighbor) + 1);
    		}
    	}
    	
    	// 获取入度为0 的节点，入队列
    	for (DirectedGraphNode node : graph) {
    		if (indegree.get(node) == 0) {
    			queue.offer(node);
    			topOrder.add(node);
    		}
    	}
    	
    	while (!queue.isEmpty()) {
    		DirectedGraphNode head = queue.poll();
    		for (DirectedGraphNode neighbor : head.neighbors) {
    			int tempIndegree = indegree.get(neighbor);
    			// 去除先导节点node与所有邻居的依赖关系，所有邻居的入度数-1
    			indegree.put(neighbor, --tempIndegree);
    			// 找到新的先导节点
    			if (tempIndegree == 0) {
    				queue.offer(neighbor);
    				topOrder.add(neighbor);
    			}
    		}
    	}
    	
    	if (topOrder.size() == graph.size()) {
    		return topOrder;
    	}
    	
    	// 有向图无法进行拓扑排序
    	return null;
    }
}

public class TopologicalSorting_127 {

	public static void main(String[] args) {
		
	}

}

