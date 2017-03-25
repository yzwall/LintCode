/**
 * 拓扑排序，根据输入构造图
 * http://www.lintcode.com/en/problem/course-schedule-ii/
 * @author yzwall
 */
package bfs;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;

class Solution6 {
	private class Node {
		int label;
		ArrayList<Node> neightbors;
		public Node (int label) {
			this.label = label;
			this.neightbors = new ArrayList<>();
		}
	}
	
    /**
     * @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	int[] topOrder = new int[numCourses];
    	if (numCourses <= 0 || prerequisites == null) {
    		return new int[0];
    	}
    	
    	ArrayDeque<Node> queue = new ArrayDeque<>();
    	HashMap<Node, Integer> indegree = new HashMap<>();
    	ArrayList<Node> graph = new ArrayList<>();
    	
    	// 构造图
    	for (int i = 0; i < numCourses; i++) {
    		graph.add(new Node(i));
    		// 默认节点入度均为0
    		indegree.put(graph.get(i), 0);
    	}
    	
    	// 根据输入更新节点入度
    	for (int i = 0; i < prerequisites.length; i++) {
    		int from = prerequisites[i][1];
    		int to = prerequisites[i][0];
    		graph.get(from).neightbors.add(graph.get(to));
    		indegree.put(graph.get(to), indegree.get(graph.get(to)) + 1);
    	}
    	
    	int index = 0;
    	for (Node node : graph) {
    		if (indegree.get(node) == 0) {
    			// 找出所有入度为0的节点，入队
    			queue.offer(node);
    			topOrder[index++] = node.label;
    		}
    	}
    	
    	while (!queue.isEmpty()) {
    		Node head = queue.poll();
    		for (Node neighbor : head.neightbors) {
    			int tempIndegree = indegree.get(neighbor);
    			indegree.put(neighbor, --tempIndegree);
    			if (tempIndegree == 0) {
    				queue.offer(neighbor);
    				topOrder[index++] = neighbor.label;
    			}
    		}
    	}
    	
    	if (index == graph.size()) {
    		return topOrder;
    	}
    	
    	return new int[0];
    }
}

public class CourseScheduleII_616 {
	public static void main(String[] args) {
		int[][] prerequisites = new int[][]{{1,0},{2,0},{3,1},{3,2}};
		System.out.println(new Solution6().findOrder(4, prerequisites));
	}
}
