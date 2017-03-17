#ifndef _GRAPHBYLIST_H_
#define _GRAPHBYLIST_H_
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TRUE 1
#define FALSE 0
#define DIRECT 1
#define UNDIRECT 0
typedef int ElementType;
typedef int BOOL;
typedef int VertexType;
typedef int EdgeType;

/********************** 图ADT 邻接表实现 **********************/
/* 顶点 */
typedef struct VertexNode {
	VertexType data;			// 顶点数据
	struct EdgeNode *firstEdge; // 边表头，指向第一个邻接点
	struct EdgeNode *lastEdge;	// 边表尾，指向最后一个邻接点，便于添加新的邻接点
} VertexNode, *VertexNodePtr;

/* 边表（存储顶点的领接顶点） */
typedef struct EdgeNode {
	int adjvexIndex;		// 邻接顶点在顶点数组中的下标
	EdgeType weight;		// 边权重
	struct EdgeNode *next;	// 指向下一个邻接顶点
} EdgeNode, *EdgeNodePtr;

/* 图的邻接表实现 */
typedef struct LGraph {
	VertexNodePtr vArray;	// 一维向量，按序存储图中每个顶点
	int vNum;				// 顶点数目
	int eNum;				// 边数目
} LGraph, *LGraphPtr;

/********************** 队列ADT 链表实现 **********************/
// 队列结点
typedef struct QueueNode {
	VertexNodePtr element;	// 队列元素为图中顶点
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
void enqueue(QueuePtr Q, VertexNodePtr x) {
	QueueNodePtr pNew = (QueueNodePtr)malloc(sizeof(QueueNode));
	Q->rear->element = x;
	Q->rear->next = pNew;
	Q->rear = Q->rear->next;
}

// 出队
VertexNodePtr dequeue(QueuePtr Q) {
	VertexNodePtr x = Q->front->element;
	QueueNodePtr temp = Q->front;
	Q->front = Q->front->next;
	free(temp);	// 释放原队首空间
	return x;
}


// 插入边(u, v, w)
void insertEdge(LGraphPtr graph, VertexType u, VertexType v, int w, BOOL isDirect) {
	EdgeNodePtr pNewADJ1 = (EdgeNodePtr)malloc(sizeof(EdgeNode));
	graph->vArray[u].lastEdge->adjvexIndex = v;
	graph->vArray[u].lastEdge->weight = w;
	graph->vArray[u].lastEdge->next = pNewADJ1;
	graph->vArray[u].lastEdge = graph->vArray[u].lastEdge->next;
	if(isDirect == UNDIRECT) {	// 无向边
		EdgeNodePtr pNewADJ2 = (EdgeNodePtr)malloc(sizeof(EdgeNode));
		graph->vArray[v].lastEdge->adjvexIndex = u;
		graph->vArray[v].lastEdge->weight = w;
		graph->vArray[v].lastEdge->next = pNewADJ2;
		graph->vArray[v].lastEdge = graph->vArray[v].lastEdge->next;
	}
}

// 判断顶点v的邻接表是否为空
BOOL isEdgeListEmpty(VertexNodePtr v) {
	return v->firstEdge == v->lastEdge;
}

void resetVisit(LGraphPtr graph, BOOL *visit) {
	memset(visit, FALSE, (graph->vNum) * sizeof(BOOL));
}

// 创建图
void createLGraph(LGraphPtr *graph, BOOL **visit, int isDirect) {
//	int i, j, k;
//	printf("Input vertexes num and edges num:\n");
//	scanf("%d%d", (*graph)->vNum, (*graph)->eNum);
//	(*graph)->vArray = (VertexNodePtr)malloc(((*graph)->vNum) * sizeof(VertexNode));
//	for(i=0; i<(*graph)->vNum; i++) {
//		scanf("%d", &(*graph)->vArray[i]->data);
//		(*graph)->vArray[i]->firstEdge = (EdgeNodePtr)malloc(sizeof(EdgeNode));
//		(*graph)->vArray[i]->lastEdge = (*graph)->vArray[i]->firstEdge;
//		(*graph)->vArray[i]->lastEdge->next = NULL;
//	}
//	int u, v, w;
//	for(j=0; j<(*graph)->eNum; j++) {	// 输入边(u, v, w)
//		scanf("%d%d%d", &u, &v, &w);
//		insert((*graph), isDirect, u, v, w);
//	}

	(*graph) = (LGraphPtr)malloc(sizeof(LGraph));
	// 便于测试，指定有向图测试用例
	(*graph)->vNum = 7;
	(*graph)->eNum = 10;
	// 便于测试，指定无向图测试用例

//	(*graph)->vNum = 10;
//	(*graph)->eNum = 13;
	int i;
	(*graph)->vArray = (VertexNodePtr)malloc(((*graph)->vNum) * sizeof(VertexNode));
	visit = (BOOL *)malloc(((*graph)->vNum) * sizeof(BOOL));
	for(i=0; i<(*graph)->vNum; i++) {
		(*graph)->vArray[i].data = i;
		(*graph)->vArray[i].firstEdge = (EdgeNodePtr)malloc(sizeof(EdgeNode));
		(*graph)->vArray[i].lastEdge = (*graph)->vArray[i].firstEdge;
		(*graph)->vArray[i].lastEdge->next = NULL;
	}
	// 插入有向图指定边,该图有两个连通分量
	insertEdge((*graph), 0, 1, 1, DIRECT);
	insertEdge((*graph), 0, 5, 1, DIRECT);
	insertEdge((*graph), 0, 2, 1, DIRECT);
	insertEdge((*graph), 1, 2, 1, DIRECT);
	insertEdge((*graph), 5, 6, 1, DIRECT);
	insertEdge((*graph), 6, 2, 1, DIRECT);
	insertEdge((*graph), 6, 0, 1, DIRECT);
	insertEdge((*graph), 3, 0, 1, DIRECT);
	insertEdge((*graph), 3, 4, 1, DIRECT);
	insertEdge((*graph), 4, 5, 1, DIRECT);
	// 插入无向图指定边
//	insertEdge((*graph), 0, 1, 1, UNDIRECT);
//	insertEdge((*graph), 0, 3, 1, UNDIRECT);
//	insertEdge((*graph), 0, 4, 1, UNDIRECT);
//	insertEdge((*graph), 1, 2, 1, UNDIRECT);
//	insertEdge((*graph), 1, 4, 1, UNDIRECT);
//	insertEdge((*graph), 2, 5, 1, UNDIRECT);
//	insertEdge((*graph), 3, 6, 1, UNDIRECT);
//	insertEdge((*graph), 3, 8, 1, UNDIRECT);
//	insertEdge((*graph), 5, 7, 1, UNDIRECT);
//	insertEdge((*graph), 5, 6, 1, UNDIRECT);
//	insertEdge((*graph), 6, 8, 1, UNDIRECT);
//	insertEdge((*graph), 6, 7, 1, UNDIRECT);
//	insertEdge((*graph), 6, 9, 1, UNDIRECT);
}

/************************* 图的广度优先遍历 *************************/
// 打印BFS辅助队列（front->rear）
void tarvQueue(QueuePtr Q) {
	QueueNodePtr pIter = Q->front;
	printf("\nQueue: ");
	while(pIter && pIter != Q->rear)
	{
		printf("%c ", 'A'+pIter->element->data);
		pIter = pIter->next;
	}
	printf("\n");

}

/*
 * 遍历实现：图的邻接表实现，队列辅助
 * 遍历范围：针对以自定义起点开始的图中连通分量
 * 遍历实质：等价于对图对应的生成树的层次遍历
 * 遍历时间复杂度：O()
 */
void BFS(LGraphPtr graph, BOOL *visit, VertexNodePtr v) {
	QueuePtr Q;
	initQueue(&Q);	// 给*Q申请指向内存空间并初始化
	enqueue(Q, v);
	visit[v->data] = TRUE;
	while(!isQueueEmtpy(Q))
	{
		tarvQueue(Q);
		VertexNodePtr x = dequeue(Q);		// 出队结点
		printf("%c\n", 'A'+(x->data));
		EdgeNodePtr pIter = x->firstEdge;	// 遍历x的邻接表
		while(pIter && pIter != x->lastEdge)
		{
			if(!visit[pIter->adjvexIndex]) {	// 当前邻接结点未访问
				enqueue(Q, &graph->vArray[pIter->adjvexIndex]); // 入队
				visit[pIter->adjvexIndex] = TRUE;	// 标记
			}
			pIter = pIter->next; // 每次都忘...
		}
	}
}

/*
 * 遍历对象：无向图&有向图
 * 遍历实质：广度优先遍历以起点v开始的连通分量
 */
void BFSTrav(LGraphPtr graph, BOOL *visit) {
	int v;
	for(v=0; v<graph->vNum; v++) {
		if(!visit[v])
			BFS(graph, visit, &graph->vArray[v]);
	}
	// BFS(graph, visit, &graph->vArray[0]);
}

/************************* 图的深度优先遍历 *************************/
/*
 * 遍历实现：图的邻接矩阵实现，以A为起点，同一顶点的邻居以A~Z为序访问
 * 遍历范围：针对以自定义起点开始的图中连通分量
 * 遍历实质：类似于于对图对应的支撑树的先序遍历
 * 遍历时间复杂度：O()
 */
void DFS(LGraphPtr graph, BOOL *visit, VertexNodePtr v) {
	visit[v->data] = TRUE;
	printf("%c\n", 'A'+(v->data));
	EdgeNodePtr pIter = v->firstEdge;			// 遍历x的邻接表
	while(pIter && pIter != v->lastEdge)
	{
		if(!visit[pIter->adjvexIndex]) {		// 当前邻接结点未访问
			DFS(graph, visit, &graph->vArray[pIter->adjvexIndex]);// 继续DFS
			visit[pIter->adjvexIndex] = TRUE;	// 标记
		}
		pIter = pIter->next;					// 每次都忘...
	}
}

/*
 * 遍历对象：无向图&有向图
 * 遍历实质：深度优先遍历以起点v开始的连通分量
 */
void DFSTrav(LGraphPtr graph, BOOL *visit) {
	int v;
	for(v=0; v<graph->vNum; v++) {
		if(!visit[v])
			DFS(graph, visit, &graph->vArray[v]);
	}
}

#endif
