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

/********************** ����ADT ����ʵ�� **********************/
// ���н��
typedef struct QueueNode {
	VertexType element;	// ����Ԫ��Ϊͼ�ж���
	struct QueueNode *next;
} QueueNode, *QueueNodePtr;

// ����
typedef struct Queue {
	QueueNodePtr front;
	QueueNodePtr rear;
} Queue, *QueuePtr;

// �ж��Ƿ�ն���
BOOL isQueueEmtpy(QueuePtr Q) {
	return Q->front == Q->rear;
}

// ��ʼ������
void initQueue(QueuePtr *Q) {
	(*Q) = (QueuePtr)malloc(sizeof(Queue));
	(*Q)->front = (QueueNodePtr)malloc(sizeof(QueueNode));
	(*Q)->rear = (QueueNodePtr)malloc(sizeof(QueueNode));
	(*Q)->front = (*Q)->rear;
	(*Q)->rear->next = NULL;
}

// ���
void enqueue(QueuePtr Q, VertexType x) {
	QueueNodePtr pNew = (QueueNodePtr)malloc(sizeof(QueueNode));
	Q->rear->element = x;
	Q->rear->next = pNew;
	Q->rear = Q->rear->next;
}

// ����
VertexType dequeue(QueuePtr Q) {
	VertexType x = Q->front->element;
	QueueNodePtr temp = Q->front;
	Q->front = Q->front->next;
	free(temp);	// �ͷ�ԭ���׿ռ�
	return x;
}

/********************** ͼADT �ڽӾ���ʵ�� **********************/
// �ڽӾ���ʵ��-ͼ�ṹ
typedef struct MGraph {
	VertexType *vArray;	// �����
	EdgeType eMatrix[MAX_VEX_NUM][MAX_VEX_NUM];	// �ڽӾ���
	int vNum;	// ������
	int eNum;	// ����
} MGraph, *MGraphPtr;


// ����visit
void resetVisit(MGraphPtr graph, BOOL *visit) {
	memset(visit, FALSE, (graph->vNum) * sizeof(BOOL));
}

/*
 * �ڽӾ���ʵ��-��ʼ��ͼ
 * ʱ�临�Ӷ�O(MAX_VEX_NUM 2)
 */
void initMGraph(MGraphPtr *graph, BOOL **visit) {
	int i, j;
//	printf("Input vertexes num and edges num: \n");
//	scanf("%d%d", &graph->vNum, &graph->eNum);	// ���붥�����ͱ���
	/* ���ڲ��ԣ�ָ���������ͱ��� */
//	graph->vNum = 10;
//	graph->eNum = 13;
	(*graph) = (MGraphPtr)malloc(sizeof(MGraph));
	(*graph)->vNum = 7;
	(*graph)->eNum = 10;
	/* ��ʼ���㼯 */
	(*graph)->vArray = (VertexType *)malloc(((*graph)->vNum) * sizeof(VertexType));
	for(i=0; i<(*graph)->vNum; i++) {
		(*graph)->vArray[i] = i;	// Ĭ�϶���Ϊint����
	}
	/* ��ʼ������������� */
	*visit = (BOOL *)malloc(((*graph)->vNum) * sizeof(BOOL));
	/* ��ʼ���߼� */
	for(i=0; i<MAX_VEX_NUM; i++) {
		for(j=0; j<MAX_VEX_NUM; j++) {
			(*graph)->eMatrix[i][j] = INT_MAX;	// Ĭ�Ϲ�����ȨΪ����󣬱�ʾ�����߲�����
		}
	}
}

// �жϱ�(u,v)�Ƿ����
BOOL isEdgeExists(MGraphPtr graph, VertexType u, VertexType v) {
	return graph->eMatrix[u][v] != INT_MAX;
}

// ��ȡ��(u,v)Ȩ��
EdgeType getWeight(MGraphPtr graph, VertexType u, VertexType v) {
	return graph->eMatrix[u][v];
}

// �����(i,j)�Լ�Ȩw
void insertEdge(MGraphPtr graph, VertexType u, VertexType v, EdgeType w, BOOL isDirect) {
	graph->eMatrix[u][v] = w;
	if(isDirect == UNDIRECT)
		graph->eMatrix[v][u] = w;
}

// ɾ����(i,j)�Լ�Ȩw
void removeEdge(MGraphPtr graph, VertexType u, VertexType v, EdgeType w) {
	graph->eMatrix[u][v] = INT_MAX;
}

/*
 * �ڽӾ���ʵ��-��������ͼ
 * ʱ�临�Ӷ�O(vNum2+eNum)
 */
void createMGraph(MGraphPtr graph) {
//	int i, j;
//	initMGraph(graph, visit);
//	for(i=0; i<graph->vNum; i++) {	// ����㼯
//		scanf("%d", &graph->vArray[i]);
//	}
//	for(i=0; i<graph->eNum; i++) {
//		int u, v, w;
//		scanf("%d%d%d", &u, &v, &w);
//		if(isDirec == UNDIRECT) {	// ����ͼ�ڽӾ���Գ�
//			insert(graph, u, v, w);
//			insert(graph, v, u, w);
//		} else {		// ����ͼ
//			insert(graph, u, v, w);
//		}
//	}
	// ��������ͼָ����,��ͼ��������ͨ����
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

	// ��������ͼָ����
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

// ��������
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

/********************** ͼ�Ĺ�����ȱ���  **********************/
/*
 * ����ʵ�֣�ͼ���ڽӾ���ʵ�֣����и���
 * ������Χ��������Զ�����㿪ʼ��ͼ����ͨ����
 * ����ʵ�ʣ��ȼ��ڶ�ͼ��Ӧ��֧�����Ĳ�α���
 * ����ʱ�临�Ӷȣ�O(vNum+eNum)
 */
void BFS(MGraphPtr graph, BOOL *visit, VertexType v) {
	int i;
	QueuePtr Q;
	initQueue(&Q);
	enqueue(Q, graph->vArray[v]);
	visit[v] = TRUE;
	while(!isQueueEmtpy(Q))
	{
		VertexType v = dequeue(Q);	// ȡ�����׽�㣬�ۼ�ִ��O(vNum)��
		printf("%c ", 'A'+v);
		// �����v�����δ���ʹ��Ľ�㵽���У��ۼ�ִ��O(n*1+eNum)��
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
 * ������������ͼ&����ͼ
 * ����ʵ�ʣ���ȱ��������v��ʼ����ͨ����
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

/********************** ͼ��������ȱ���  **********************/
/*
 * ����ʵ�֣�ͼ���ڽӾ���ʵ�֣���AΪ��㣬ͬһ������ھ���A~ZΪ�����
 * ������Χ��������Զ�����㿪ʼ��ͼ����ͨ����
 * ����ʵ�ʣ��������ڶ�ͼ��Ӧ��֧�������������
 * ����ʱ�临�Ӷȣ�O(vNum+eNum)
 */
void DFS(MGraphPtr graph, BOOL *visit, VertexType v) {
	int i;
	printf("%c ", 'A'+v);
	visit[v] = TRUE; 				// ��ǵ�ǰ����v�ѷ���
	for(i=0; i<graph->vNum; i++) {	// ö����v��ӵ����ж���
		if(!visit[i] && isEdgeExists(graph, v, graph->vArray[i])) {
			DFS(graph, visit, graph->vArray[i]);	// �Ӷ���i����DFS����
		}
	}
	// ��v��ʼ��DFS����ȫ�������������ϲ�DFS
}

/*
 * ������������ͼ&����ͼ
 * ����ʵ�ʣ���ȱ��������v��ʼ����ͨ����
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
