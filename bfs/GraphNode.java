package bfs;

import java.util.ArrayList;

// ����ͼ����
class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;
	public UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
}

// ����ͼ����
class DirectedGraphNode {
	int label;
	ArrayList<DirectedGraphNode> neighbors;
	public DirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<DirectedGraphNode>(); 
	}
}