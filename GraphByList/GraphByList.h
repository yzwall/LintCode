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

/********************** ͼADT �ڽӱ�ʵ�� **********************/
/* ���� */
typedef struct VertexNode {
	VertexType data;			// ��������
	struct EdgeNode *firstEdge; // �߱�ͷ��ָ���һ���ڽӵ�
	struct EdgeNode *lastEdge;	// �߱�β��ָ�����һ���ڽӵ㣬��������µ��ڽӵ�
} VertexNode, *VertexNodePtr;

/* �߱��洢�������Ӷ��㣩 */
typedef struct EdgeNode {
	int adjvexIndex;		// �ڽӶ����ڶ��������е��±�
	EdgeType weight;		// ��Ȩ��
	struct EdgeNode *next;	// ָ����һ���ڽӶ���
} EdgeNode, *EdgeNodePtr;

/* ͼ���ڽӱ�ʵ�� */
typedef struct LGraph {
	VertexNodePtr vArray;	// һά����������洢ͼ��ÿ������
	int vNum;				// ������Ŀ
	int eNum;				// ����Ŀ
} LGraph, *LGraphPtr;

/********************** ����ADT ����ʵ�� **********************/
// ���н��
typedef struct QueueNode {
	VertexNodePtr element;	// ����Ԫ��Ϊͼ�ж���
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
void enqueue(QueuePtr Q, VertexNodePtr x) {
	QueueNodePtr pNew = (QueueNodePtr)malloc(sizeof(QueueNode));
	Q->rear->element = x;
	Q->rear->next = pNew;
	Q->rear = Q->rear->next;
}

// ����
VertexNodePtr dequeue(QueuePtr Q) {
	VertexNodePtr x = Q->front->element;
	QueueNodePtr temp = Q->front;
	Q->front = Q->front->next;
	free(temp);	// �ͷ�ԭ���׿ռ�
	return x;
}


// �����(u, v, w)
void insertEdge(LGraphPtr graph, VertexType u, VertexType v, int w, BOOL isDirect) {
	EdgeNodePtr pNewADJ1 = (EdgeNodePtr)malloc(sizeof(EdgeNode));
	graph->vArray[u].lastEdge->adjvexIndex = v;
	graph->vArray[u].lastEdge->weight = w;
	graph->vArray[u].lastEdge->next = pNewADJ1;
	graph->vArray[u].lastEdge = graph->vArray[u].lastEdge->next;
	if(isDirect == UNDIRECT) {	// �����
		EdgeNodePtr pNewADJ2 = (EdgeNodePtr)malloc(sizeof(EdgeNode));
		graph->vArray[v].lastEdge->adjvexIndex = u;
		graph->vArray[v].lastEdge->weight = w;
		graph->vArray[v].lastEdge->next = pNewADJ2;
		graph->vArray[v].lastEdge = graph->vArray[v].lastEdge->next;
	}
}

// �ж϶���v���ڽӱ��Ƿ�Ϊ��
BOOL isEdgeListEmpty(VertexNodePtr v) {
	return v->firstEdge == v->lastEdge;
}

void resetVisit(LGraphPtr graph, BOOL *visit) {
	memset(visit, FALSE, (graph->vNum) * sizeof(BOOL));
}

// ����ͼ
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
//	for(j=0; j<(*graph)->eNum; j++) {	// �����(u, v, w)
//		scanf("%d%d%d", &u, &v, &w);
//		insert((*graph), isDirect, u, v, w);
//	}

	(*graph) = (LGraphPtr)malloc(sizeof(LGraph));
	// ���ڲ��ԣ�ָ������ͼ��������
	(*graph)->vNum = 7;
	(*graph)->eNum = 10;
	// ���ڲ��ԣ�ָ������ͼ��������

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
	// ��������ͼָ����,��ͼ��������ͨ����
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
	// ��������ͼָ����
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

/************************* ͼ�Ĺ�����ȱ��� *************************/
// ��ӡBFS�������У�front->rear��
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
 * ����ʵ�֣�ͼ���ڽӱ�ʵ�֣����и���
 * ������Χ��������Զ�����㿪ʼ��ͼ����ͨ����
 * ����ʵ�ʣ��ȼ��ڶ�ͼ��Ӧ���������Ĳ�α���
 * ����ʱ�临�Ӷȣ�O()
 */
void BFS(LGraphPtr graph, BOOL *visit, VertexNodePtr v) {
	QueuePtr Q;
	initQueue(&Q);	// ��*Q����ָ���ڴ�ռ䲢��ʼ��
	enqueue(Q, v);
	visit[v->data] = TRUE;
	while(!isQueueEmtpy(Q))
	{
		tarvQueue(Q);
		VertexNodePtr x = dequeue(Q);		// ���ӽ��
		printf("%c\n", 'A'+(x->data));
		EdgeNodePtr pIter = x->firstEdge;	// ����x���ڽӱ�
		while(pIter && pIter != x->lastEdge)
		{
			if(!visit[pIter->adjvexIndex]) {	// ��ǰ�ڽӽ��δ����
				enqueue(Q, &graph->vArray[pIter->adjvexIndex]); // ���
				visit[pIter->adjvexIndex] = TRUE;	// ���
			}
			pIter = pIter->next; // ÿ�ζ���...
		}
	}
}

/*
 * ������������ͼ&����ͼ
 * ����ʵ�ʣ�������ȱ��������v��ʼ����ͨ����
 */
void BFSTrav(LGraphPtr graph, BOOL *visit) {
	int v;
	for(v=0; v<graph->vNum; v++) {
		if(!visit[v])
			BFS(graph, visit, &graph->vArray[v]);
	}
	// BFS(graph, visit, &graph->vArray[0]);
}

/************************* ͼ��������ȱ��� *************************/
/*
 * ����ʵ�֣�ͼ���ڽӾ���ʵ�֣���AΪ��㣬ͬһ������ھ���A~ZΪ�����
 * ������Χ��������Զ�����㿪ʼ��ͼ����ͨ����
 * ����ʵ�ʣ��������ڶ�ͼ��Ӧ��֧�������������
 * ����ʱ�临�Ӷȣ�O()
 */
void DFS(LGraphPtr graph, BOOL *visit, VertexNodePtr v) {
	visit[v->data] = TRUE;
	printf("%c\n", 'A'+(v->data));
	EdgeNodePtr pIter = v->firstEdge;			// ����x���ڽӱ�
	while(pIter && pIter != v->lastEdge)
	{
		if(!visit[pIter->adjvexIndex]) {		// ��ǰ�ڽӽ��δ����
			DFS(graph, visit, &graph->vArray[pIter->adjvexIndex]);// ����DFS
			visit[pIter->adjvexIndex] = TRUE;	// ���
		}
		pIter = pIter->next;					// ÿ�ζ���...
	}
}

/*
 * ������������ͼ&����ͼ
 * ����ʵ�ʣ�������ȱ��������v��ʼ����ͨ����
 */
void DFSTrav(LGraphPtr graph, BOOL *visit) {
	int v;
	for(v=0; v<graph->vNum; v++) {
		if(!visit[v])
			DFS(graph, visit, &graph->vArray[v]);
	}
}

#endif
