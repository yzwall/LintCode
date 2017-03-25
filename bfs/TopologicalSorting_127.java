/**
 * ��������BFSʵ��
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
    	
    	// Ĭ�����нڵ����ȶ�Ϊ0
    	for (DirectedGraphNode node : graph) {
    		indegree.put(node, 0);
    	}
    	
    	// ��������ͼ��¼ÿ���ڵ�����
    	for (DirectedGraphNode node : graph) {
    		for (DirectedGraphNode neighbor : node.neighbors) {
    			indegree.put(neighbor, indegree.get(neighbor) + 1);
    		}
    	}
    	
    	// ��ȡ���Ϊ0 �Ľڵ㣬�����
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
    			// ȥ���ȵ��ڵ�node�������ھӵ�������ϵ�������ھӵ������-1
    			indegree.put(neighbor, --tempIndegree);
    			// �ҵ��µ��ȵ��ڵ�
    			if (tempIndegree == 0) {
    				queue.offer(neighbor);
    				topOrder.add(neighbor);
    			}
    		}
    	}
    	
    	if (topOrder.size() == graph.size()) {
    		return topOrder;
    	}
    	
    	// ����ͼ�޷�������������
    	return null;
    }
}

public class TopologicalSorting_127 {

	public static void main(String[] args) {
		
	}

}

