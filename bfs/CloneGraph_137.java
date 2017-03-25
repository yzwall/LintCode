/**
 * ͼ����ȿ�����ͨ��BFS��ȡԭͼ�㼯��Ȼ������㼯���п���
 * http://www.lintcode.com/en/problem/clone-graph/
 * @author yzwall
 */
package bfs;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution10 {
    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	if (node == null) {
    		return node;
    	}
    	
    	// BFS��ȡԭͼ�㼯
    	Set<UndirectedGraphNode> nodeSet = new HashSet<>();
    	Queue<UndirectedGraphNode> queue = new ArrayDeque<>();
    	queue.offer(node);
    	nodeSet.add(node);
    	while (!queue.isEmpty()) {
    		UndirectedGraphNode head = queue.poll();
    		for (UndirectedGraphNode tempNode : head.neighbors) {
    			if (!nodeSet.contains(tempNode)) {
    				queue.offer(tempNode);
    				nodeSet.add(tempNode);
    			}
    		}
    	}
    	
    	// ÿ���½ڵ㿽��
    	Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    	for (UndirectedGraphNode tempNode : nodeSet) {
    		map.put(tempNode, new UndirectedGraphNode(tempNode.label));
    	}
    	
    	for (UndirectedGraphNode tempNode : nodeSet) {
    		UndirectedGraphNode nodeNew = map.get(tempNode);
    		// ����ÿ���ڵ���ھ�
    		for (UndirectedGraphNode neighbor : tempNode.neighbors) {
    			UndirectedGraphNode neighborNew = map.get(neighbor);
    			nodeNew.neighbors.add(neighborNew);
    		}
    	}
    	
    	return map.get(node);
    }
}

public class CloneGraph_137 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
