/*
 * File name:QueueByList.c
 * Description: ��������ʵ�ֶ���,front->nextָ���ͷ�� ��β->nextָ��rear
 * Author: yzwall
 * Data: 2016/9/7 14:46
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
////////////////////////////////////////////
typedef int ElementType;

typedef struct QueueNode {
	ElementType element;
	struct QueueNode *next;
}QueueNode;

typedef struct LinkQueue {
	QueueNode *front;
	QueueNode *rear;
}LinkQueue;
////////////////////////////////////////////
// ������ʽ����
void traverseQueue(LinkQueue *Queue) {
	printf("\nTraverse Queue:\n");
	printf("front->next:%d rear:%d\n", Queue->front->next, Queue->rear);
	QueueNode *pIter = Queue->front->next;
	while(pIter != Queue->rear)
	{
		printf("%d %d\n", pIter, pIter->element);
		pIter = pIter->next;
	}
}

// �ж��Ƿ�ն���
int isEmpty(LinkQueue *Queue) {
	return Queue->front->next == Queue->rear;
}

// ������ʽ����
void createQueue(LinkQueue *Queue) {
	Queue->front = (QueueNode *)malloc(sizeof(QueueNode));
	Queue->rear = (QueueNode *)malloc(sizeof(QueueNode));
	if(Queue->front == NULL || Queue->rear == NULL) {
		printf("ptr memory allocate failed.\n");
		exit(-1);
	}
//	Queue->front->element = -1;	// �ս��Ԫ�س�ʼ����ֵ���岻��
	Queue->front->next = Queue->rear;
	Queue->rear->next = NULL;
}

// ��ӣ���β��,��ʽ���д�С����, ���ؼ���Ƿ�����
void enQueue(LinkQueue *Queue, ElementType element) {
	Queue->rear->element = element;
	QueueNode *pNew = (QueueNode *)malloc(sizeof(QueueNode));
//	pNew->element = -1;					// �½��Ԫ�س�ʼ����ֵ���岻��
	pNew->next = NULL;
	Queue->rear->next = pNew;
	Queue->rear = Queue->rear->next;	// rearָ��ά����β
	traverseQueue(Queue);
}

// ���ӣ���ͷ��
void deQueue(LinkQueue *Queue) {
	if(isEmpty(Queue)) {	// �ն��в��������
		printf("Queue front is Empty, dequeue failed.\n");
		return;
	} else {
		QueueNode *pTemp = Queue->front->next;
		Queue->front->next = pTemp->next;
		free(pTemp);
	}
	traverseQueue(Queue);
}

// ��ȡ��ͷԪ��
ElementType getFront(LinkQueue *Queue) {
	if(isEmpty(Queue)) {
		printf("Queue front is Empty, get front failed.\n");
		return NULL;
	} else {
		return Queue->front->next->element;
	}
}

// ��ȡ��ͷԪ�ز�����
ElementType getFrontAndDequeue(LinkQueue *Queue) {
	if(isEmpty(Queue)) {
		printf("Queue front is Empty, get front and dequeue failed.\n");
		return NULL;
	} else {
		ElementType element = Queue->front->next->element;
		QueueNode *pTemp = Queue->front->next;
		Queue->front->next = pTemp->next;
		free(pTemp);
		traverseQueue(Queue);
		return element;
	}
}

// �ͷŶ���
void disposeQueue(LinkQueue *Queue) {
	QueueNode *pIter = Queue->front->next;
	while(pIter != NULL)
	{
		QueueNode *pTemp = pIter->next;
		free(pIter);
		pIter = pTemp;
	}
}
////////////////////////////////////////////
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	LinkQueue *Queue = (LinkQueue *)malloc(sizeof(LinkQueue));
	createQueue(Queue);
	int i;
	for(i=1; i<6; i++) {	// ���1~5
		enQueue(Queue, i);
	}
	for(i=1; i<7; i++) {	// ����1~5�������Կն��г���
		deQueue(Queue);
	}
	for(i=1; i<4; i++) {	// ���1~3
		enQueue(Queue, i);
	}

	return 0;
}

