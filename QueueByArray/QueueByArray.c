/*
 * File name:QueueByArray.c
 * Description: ѭ������ʵ�ֶ���
 * Author: yzwall
 * Data: 2016/9/6 22:46
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MIN_QUEUE_SIZE 3
#define MAX_QUEUE_SIZE 5
typedef int ElementType;
typedef struct Queue{
	int capacity;		// �����������
	int front;			// ��ͷ
	int rear;			// ��β
	int size;			// ���д�С
	ElementType *array;	// ������������
}Queue;
//////////////////////////////////////
// �ж϶����Ƿ�Ϊ��
int isEmpty(Queue *queue) {
	return queue->size == 0;
}

// �ж϶����Ƿ�����
int isFull(Queue *queue) {
	// Ԥ��һ�����鵥Ԫ����֤������rear == front
	return (queue->rear + 1) % (queue->capacity) == queue->front;
}

// ��ն���
void clearQueue(Queue *queue) {
	queue->size = 0;
	queue->front = 0;
	queue->rear = 0;
}

// �������Ԫ�ظ���
int getQueueSize(Queue *queue) {
	return (queue->rear - queue->front + queue->capacity) % (queue->capacity);
}

// ��������
Queue *createQueue(int maxElements) {
	if(maxElements < MIN_QUEUE_SIZE) {
		printf("Queue size is too small.\n");
		exit(-1);
	}
	Queue *queue = (Queue *)malloc(sizeof(Queue));
	if(queue == NULL) {
		printf("Queue memory allocate failed.\n");
		exit(-1);
	}
	queue->capacity = maxElements;
	queue->size = 0;
	queue->front = 0;
	queue->rear = 0;
	queue->array = (ElementType *)malloc(sizeof(ElementType) * maxElements);
	memset(queue->array, -1, sizeof(ElementType) * queue->capacity);	// ����Ԫ�س�ʼ����ֵ-1
	return queue;
}

// �ͷŶ���
void disposeQueue(Queue *queue) {
	if(queue != NULL) {
		free(queue->array);
		free(queue);
	}
}

// ���
void enQueue(Queue *queue, ElementType element) {
	if(isFull(queue)) {
		printf("Queue is full, enqueue failed.\n");
		return;
	}
	queue->array[queue->rear] = element;	// Ԫ�����
	/* rearָ������ƶ�һ��λ�ã�
	 * �������һ��λ�ã���ת������ͷ����ѭ����
	 */
	queue->rear = (queue->rear + 1) % (queue->capacity);
	queue->size = getQueueSize(queue);
	traverseQueue(queue);
}

// ����
void deQueue(Queue *queue) {
	if(isEmpty(queue)) {
		printf("Queue is empty, dequeue failed.\n");
		exit(-1);
	}
	/* queue[front]��ֵ-1���ȴ��´����Ԫ�ظ���
	 * frontָ������ƶ�һ��λ�ã�
	 * �������һ��λ�ã���ת������ͷ����ѭ����
	 */
	queue->array[queue->front] = -1;
	queue->front = (queue->front + 1) % (queue->capacity);
	queue->size = getQueueSize(queue);
	traverseQueue(queue);
}

// ��ȡ����Ԫ�ز�����
ElementType getFontAndDequeue(Queue *queue) {
	if(isEmpty(queue)) {
		printf("Queue is empty, dequeue failed.\n");
		exit(-1);
	}
	ElementType element  = queue->array[queue->front];
	queue->front = (queue->front + 1) % (queue->capacity);
	queue->size = getQueueSize(queue);
	return element;
}

// ����ѭ������
void traverseQueue(Queue *queue) {
	printf("\nfront:%d Rear:%d\n", queue->front, queue->rear);
	int i;
	for(i=0; i<queue->capacity; i++) {
		printf("%d %d\n", i, queue->array[i]);
	}
}

//////////////////////////////////////
int main() {
	Queue *queue = createQueue(MAX_QUEUE_SIZE);
	int i;
	for(i=1; i<MAX_QUEUE_SIZE; i++) {	// ��11/22/33���
		enQueue(queue, i*11);
	}
	deQueue(queue);	// ����
	deQueue(queue); // ����
	for(i=5; i<8; i++) {	// ��55/66/77���
		enQueue(queue, i*11);
	}
	return 0;
}
