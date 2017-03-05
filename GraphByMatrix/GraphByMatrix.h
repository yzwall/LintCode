#ifndef _GRAPHBYMATRIX_H_
#define _GRAPHBYMATRIX_H_
#include <stdio.h>
#include <limits.h>
#include <stdlib.h>
#include <string.h>
#define MAX_VEX_NUM 100
#define TRUE 1
#define FALSE 0
#define DIRECT 1
#define UNDIRECT 0
typedef int ElementType;
typedef int BOOL;
typedef int VertexType;
typedef int EdgeType;

/********************** 队列ADT 链表实现 **********************/
// 队列结点
typedef struct QueueNode {
	VertexType element;	// 队列元素为图中顶点
	struct QueueNode *next;
} QueueNode, *QueueNodePtr;

// 队列
typedef struct Queue {
	QueueNodePtr front;
	QueueNodePtr rear;
} Queue, *QueuePtr;

// 判断是否空队列
BOOL isQueueEmtpy(QueuePtr Q) {
	return Q->front == Q->rear;
}

// 初始化队列
void initQueue(QueuePtr *Q) {
	(*Q) = (QueuePtr)malloc(sizeof(Queue));
	(*Q)->front = (QueueNodePtr)malloc(sizeof(QueueNode));
	(*Q)->rear = (QueueNodePtr)malloc(sizeof(QueueNode));
	(*Q)->front = (*Q)->rear;
	(*Q)->rear->next = NULL;
}

// 入队
void enqueue(QueuePtr Q, VertexType x) {
	QueueNodePtr pNew = (QueueNodePtr)malloc(sizeof(QueueNode));
	Q->rear->element = x;
	Q->rear->next = pNew;
	Q->rear = Q->rear->next;
}

// 出队
VertexType dequeue(QueuePtr Q) {
	VertexType x = Q->front->element;
	QueueNodePtr temp = Q->front;
	Q->front = Q->front->next;
	free(temp);	// 释放原队首空间
	return x;
}

/********************** 图ADT 邻接矩阵实现 **********************/
// 邻接矩阵实现-图结构
typedef struct MGraph {
	VertexType *vArray;	// 顶点表
	EdgeType eMatrix[MAX_VEX_NUM][MAX_VEX_NUM];	// 邻接矩阵
	int vNum;	// 顶点数
	int eNum;	// 边数
} MGraph, *MGraphPtr;


// 重置visit
void resetVisit(MGraphPtr graph, BOOL *visit) {
	memset(visit, FALSE, (graph->vNum) * sizeof(BOOL));
}

/*
 * 邻接矩阵实现-初始化图
 * 时间复杂度O(MAX_VEX_NUM 2)
 */
void initMGraph(MGraphPtr *graph, BOOL **visit) {
	int i, j;
//	printf("Input vertexes num and edges num: \n");
//	scanf("%d%d", &graph->vNum, &graph->eNum);	// 输入顶点数和边数
	/* 便于测试，指定顶点数和边数 */
//	graph->vNum = 10;
//	graph->eNum = 13;
	(*graph) = (MGraphPtr)malloc(sizeof(MGraph));
	(*graph)->vNum = 7;
	(*graph)->eNum = 10;
	/* 初始化点集 */
	(*graph)->vArray = (VertexType *)malloc(((*graph)->vNum) * sizeof(VertexType));
	for(i=0; i<(*graph)->vNum; i++) {
		(*graph)->vArray[i] = i;	// 默认顶点为int类型
	}
	/* 初始化顶点访问数组 */
	*visit = (BOOL *)malloc(((*graph)->vNum) * sizeof(BOOL));
	/* 初始化边集 */
	for(i=0; i<MAX_VEX_NUM; i++) {
		for(j=0; j<MAX_VEX_NUM; j++) {
			(*graph)->eMatrix[i][j] = INT_MAX;	// 默认关联边权为无穷大，表示关联边不存在
		}
	}
}

// 判断边(u,v)是否存在
BOOL isEdgeExists(MGraphPtr graph, VertexType u, VertexType v) {
	return graph->eMatrix[u][v] != INT_MAX;
}

// 获取边(u,v)权重
EdgeType getWeight(MGraphPtr graph, VertexType u, VertexType v) {
	return graph->eMatrix[u][v];
}

// 插入边(i,j)以及权w
void insertEdge(MGraphPtr graph, VertexType u, VertexType v, EdgeType w, BOOL isDirect) {
	graph->eMatrix[u][v] = w;
	if(isDirect == UNDIRECT)
		graph->eMatrix[v][u] = w;
}

// 删除边(i,j)以及权w
void removeEdge(MGraphPtr graph, VertexType u, VertexType v, EdgeType w) {
	graph->eMatrix[u][v] = INT_MAX;
}

/*
 * 邻接矩阵实现-创建无向图
 * 时间复杂度O(vNum2+eNum)
 */
void createMGraph(MGraphPtr graph) {
//	int i, j;
//	initMGraph(graph, visit);
//	for(i=0; i<graph->vNum; i++) {	// 输入点集
//		scanf("%d", &graph->vArray[i]);
//	}
//	for(i=0; i<graph->eNum; i++) {
//		int u, v, w;
//		scanf("%d%d%d", &u, &v, &w);
//		if(isDirec == UNDIRECT) {	// 无向图邻接矩阵对称
//			insert(graph, u, v, w);
//			insert(graph, v, u, w);
//		} else {		// 有向图
//			insert(graph, u, v, w);
//		}
//	}
	// 插入有向图指定边,该图有两个连通分量
	insertEdge(graph, 0, 1, 1, DIRECT);
	insertEdge(graph, 0, 5, 1, DIRECT);
	insertEdge(graph, 0, 2, 1, DIRECT);
	insertEdge(graph, 1, 2, 1, DIRECT);
	insertEdge(graph, 5, 6, 1, DIRECT);
	insertEdge(graph, 6, 2, 1, DIRECT);
	insertEdge(graph, 6, 0, 1, DIRECT);
	insertEdge(graph, 3, 0, 1, DIRECT);
	insertEdge(graph, 3, 4, 1, DIRECT);
	insertEdge(graph, 4, 5, 1, DIRECT);

	// 插入无向图指定边
//	insertEdge(graph, 0, 1, 1, UNDIRECT);
//	insertEdge(graph, 0, 3, 1, UNDIRECT);
//	insertEdge(graph, 0, 4, 1, UNDIRECT);
//	insertEdge(graph, 1, 2, 1, UNDIRECT);
//	insertEdge(graph, 1, 4, 1, UNDIRECT);
//	insertEdge(graph, 2, 5, 1, UNDIRECT);
//	insertEdge(graph, 3, 6, 1, UNDIRECT);
//	insertEdge(graph, 3, 8, 1, UNDIRECT);
//	insertEdge(graph, 5, 7, 1, UNDIRECT);
//	insertEdge(graph, 5, 6, 1, UNDIRECT);
//	insertEdge(graph, 6, 8, 1, UNDIRECT);
//	insertEdge(graph, 6, 7, 1, UNDIRECT);
//	insertEdge(graph, 6, 9, 1, UNDIRECT);
}

// 遍历队列
void travQueue(QueuePtr Q) {
	QueueNodePtr p = Q->front;
	printf("Queue: ");
	while(p != NULL && p != Q->rear)
	{
		printf("%c ", 'A'+(p->element));
		p = p->next;
	}
	printf("\n");
}

/********************** 图的广度优先遍历  **********************/
/*
 * 遍历实现：图的邻接矩阵实现，队列辅助
 * 遍历范围：针对以自定义起点开始的图中连通分量
 * 遍历实质：等价于对图对应的支撑树的层次遍历
 * 遍历时间复杂度：O(vNum+eNum)
 */
void BFS(MGraphPtr graph, BOOL *visit, VertexType v) {
	int i;
	QueuePtr Q;
	initQueue(&Q);
	enqueue(Q, graph->vArray[v]);
	visit[v] = TRUE;
	while(!isQueueEmtpy(Q))
	{
		VertexType v = dequeue(Q);	// 取出队首结点，累计执行O(vNum)次
		printf("%c ", 'A'+v);
		// 添加与v领接且未访问过的结点到队列，累计执行O(n*1+eNum)次
		for(i=0; i<graph->vNum; i++) {
			if(isEdgeExists(graph, v, graph->vArray[i]) && !visit[i]) {
				enqueue(Q, i);
				visit[i] = TRUE;
			}
		}
	}
	free(Q);
}

/*
 * 遍历对象：无向图&有向图
 * 遍历实质：广度遍历以起点v开始的连通分量
 */
void BFSTrav(MGraphPtr graph, BOOL *visit) {
	int v;
	printf("BFS Traverse: ");
	for(v=0; v<graph->vNum; v++) {
		if(!visit[v]) {
			BFS(graph, visit, v);
		}
	}
	printf("\n");
}

/********************** 图的深度优先遍历  **********************/
/*
 * 遍历实现：图的邻接矩阵实现，以A为起点，同一顶点的邻居以A~Z为序访问
 * 遍历范围：针对以自定义起点开始的图中连通分量
 * 遍历实质：类似于于对图对应的支撑树的先序遍历
 * 遍历时间复杂度：O(vNum+eNum)
 */
void DFS(MGraphPtr graph, BOOL *visit, VertexType v) {
	int i;
	printf("%c ", 'A'+v);
	visit[v] = TRUE; 				// 标记当前顶点v已访问
	for(i=0; i<graph->vNum; i++) {	// 枚举与v领接的所有顶点
		if(!visit[i] && isEdgeExists(graph, v, graph->vArray[i])) {
			DFS(graph, visit, graph->vArray[i]);	// 从顶点i继续DFS遍历
		}
	}
	// 从v开始的DFS遍历全部结束，返回上层DFS
}

/*
 * 遍历对象：无向图&有向图
 * 遍历实质：深度遍历以起点v开始的连通分量
 */
void DFSTrav(MGraphPtr graph, BOOL *visit) {
	int v;
	printf("DFS Traverse: ");
	for(v=0; v<graph->vNum; v++) {
		if(!visit[v])
			DFS(graph, visit, graph->vArray[v]);
	}
	printf("\n");
}

#endif
