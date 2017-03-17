/*
 * File name:QueueByArray.c
 * Description: 循环数组实现队列
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
	int capacity;		// 队列最大容量
	int front;			// 队头
	int rear;			// 队尾
	int size;			// 队列大小
	ElementType *array;	// 队列数据区域
}Queue;
//////////////////////////////////////
// 判断队列是否为空
int isEmpty(Queue *queue) {
	return queue->size == 0;
}

// 判断队列是否已满
int isFull(Queue *queue) {
	// 预留一个数组单元，保证不出现rear == front
	return (queue->rear + 1) % (queue->capacity) == queue->front;
}

// 清空队列
void clearQueue(Queue *queue) {
	queue->size = 0;
	queue->front = 0;
	queue->rear = 0;
}

// 计算队列元素个数
int getQueueSize(Queue *queue) {
	return (queue->rear - queue->front + queue->capacity) % (queue->capacity);
}

// 创建队列
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
	memset(queue->array, -1, sizeof(ElementType) * queue->capacity);	// 队列元素初始化赋值-1
	return queue;
}

// 释放队列
void disposeQueue(Queue *queue) {
	if(queue != NULL) {
		free(queue->array);
		free(queue);
	}
}

// 入队
void enQueue(Queue *queue, ElementType element) {
	if(isFull(queue)) {
		printf("Queue is full, enqueue failed.\n");
		return;
	}
	queue->array[queue->rear] = element;	// 元素入队
	/* rear指针向后移动一个位置，
	 * 若到最后一个位置，则转到数组头部（循环）
	 */
	queue->rear = (queue->rear + 1) % (queue->capacity);
	queue->size = getQueueSize(queue);
	traverseQueue(queue);
}

// 出队
void deQueue(Queue *queue) {
	if(isEmpty(queue)) {
		printf("Queue is empty, dequeue failed.\n");
		exit(-1);
	}
	/* queue[front]赋值-1，等待下次入队元素覆盖
	 * front指针向后移动一个位置，
	 * 若到最后一个位置，则转到数组头部（循环）
	 */
	queue->array[queue->front] = -1;
	queue->front = (queue->front + 1) % (queue->capacity);
	queue->size = getQueueSize(queue);
	traverseQueue(queue);
}

// 获取队首元素并出队
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

// 遍历循环队列
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
	for(i=1; i<MAX_QUEUE_SIZE; i++) {	// 将11/22/33入队
		enQueue(queue, i*11);
	}
	deQueue(queue);	// 出队
	deQueue(queue); // 出队
	for(i=5; i<8; i++) {	// 将55/66/77入队
		enQueue(queue, i*11);
	}
	return 0;
}
