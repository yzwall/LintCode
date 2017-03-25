/**
 * 无向图中找到最近点
 * http://www.lintcode.com/en/problem/search-graph-nodes/
 * @author yzwall
 */
package bfs;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;

//// 无向图定义
//class UndirectedGraphNode {
//	int label;
//	ArrayList<UndirectedGraphNode> neighbors;
//	public UndirectedGraphNode(int x) { 
//		label = x; 
//		neighbors = new ArrayList<UndirectedGraphNode>(); 
//	}
//}

class Solution8 {
    /**
     * @param graph a list of Undirected graph node
     * @param values a hash mapping, <UndirectedGraphNode, (int)value>
     * @param node an Undirected graph node
     * @param target an integer
     * @return the a node
     */
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {
                                              
        Queue<UndirectedGraphNode> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            if (values.get(head) == target) {
                return head;
            }
            for (UndirectedGraphNode tempNode : head.neighbors) {
                if (values.get(tempNode) == target) {
                    return tempNode;
                }
                queue.offer(tempNode);
            }
        }
        return null;
    }
}

public class SearchGraphNodes_618 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
