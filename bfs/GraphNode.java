package bfs;

import java.util.ArrayList;

// 无向图定义
class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;
	public UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
}

// 有向图定义
class DirectedGraphNode {
	int label;
	ArrayList<DirectedGraphNode> neighbors;
	public DirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<DirectedGraphNode>(); 
	}
}