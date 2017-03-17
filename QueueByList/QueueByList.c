/*
 * File name:QueueByList.c
 * Description: 单向链表实现队列,front->next指向表头， 表尾->next指向rear
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
// 遍历链式队列
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

// 判断是否空队列
int isEmpty(LinkQueue *Queue) {
	return Queue->front->next == Queue->rear;
}

// 创建链式队列
void createQueue(LinkQueue *Queue) {
	Queue->front = (QueueNode *)malloc(sizeof(QueueNode));
	Queue->rear = (QueueNode *)malloc(sizeof(QueueNode));
	if(Queue->front == NULL || Queue->rear == NULL) {
		printf("ptr memory allocate failed.\n");
		exit(-1);
	}
//	Queue->front->element = -1;	// 空结点元素初始化赋值意义不大
	Queue->front->next = Queue->rear;
	Queue->rear->next = NULL;
}

// 入队（队尾）,链式队列大小不定, 不必检查是否已满
void enQueue(LinkQueue *Queue, ElementType element) {
	Queue->rear->element = element;
	QueueNode *pNew = (QueueNode *)malloc(sizeof(QueueNode));
//	pNew->element = -1;					// 新结点元素初始化赋值意义不大
	pNew->next = NULL;
	Queue->rear->next = pNew;
	Queue->rear = Queue->rear->next;	// rear指针维护队尾
	traverseQueue(Queue);
}

// 出队（队头）
void deQueue(LinkQueue *Queue) {
	if(isEmpty(Queue)) {	// 空队列不允许出队
		printf("Queue front is Empty, dequeue failed.\n");
		return;
	} else {
		QueueNode *pTemp = Queue->front->next;
		Queue->front->next = pTemp->next;
		free(pTemp);
	}
	traverseQueue(Queue);
}

// 获取队头元素
ElementType getFront(LinkQueue *Queue) {
	if(isEmpty(Queue)) {
		printf("Queue front is Empty, get front failed.\n");
		return NULL;
	} else {
		return Queue->front->next->element;
	}
}

// 获取队头元素并出队
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

// 释放队列
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
	for(i=1; i<6; i++) {	// 入队1~5
		enQueue(Queue, i);
	}
	for(i=1; i<7; i++) {	// 出队1~5，并测试空队列出队
		deQueue(Queue);
	}
	for(i=1; i<4; i++) {	// 入队1~3
		enQueue(Queue, i);
	}

	return 0;
}

