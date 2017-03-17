/*
 * File name:PriorityQueueByHeap.c
 * Description: 1. ��ȫ�����ʵ�����ȶ��У����ȼ�����Ϊ��ֵԽС�����ȼ�Խ��
 * 				2. ����/��������/���˲�����ɾ���Ѷ�Ԫ��/���˲���
 * 				3. ������
 * Author: yzwall
 * Data: 2016/10/20 22:13
 */
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#define TRUE 1
#define FALSE 0
#define MAX_ELE_NUM 10
#define UNVALID INT_MIN

typedef int BOOL;
typedef int ElementType;

/* ��ȫ����ѽṹ */
typedef struct Heap {
	int capacity;
	int pqSize;	// ��ǰԪ����Ŀ
	ElementType *vector;
} Heap, *PriorityQueue;

/* ��ʼ�����ȶ��� */
void initial(PriorityQueue pq, int maxElements) {
	pq->capacity = maxElements;
	pq->pqSize = 0;
	pq->vector = (ElementType *)malloc(maxElements * sizeof(ElementType));
}

/* �������ȶ��ж�̬�����ڴ� */
void destory(PriorityQueue pq) {
	free(pq->vector);
	free(pq);
}

BOOL isHeapEmpty(PriorityQueue pq) {
	return pq->pqSize == 0;
}

/* �жϱ��Ϊx�Ľ���Ƿ��и��� */
BOOL isMax(int index) {
	return index != 0;
}

BOOL hasLChild(PriorityQueue pq, int index) {
	int prol = 2*index+1;
	return prol<pq->pqSize && pq->vector[prol] != UNVALID;
}

BOOL hasRChild(PriorityQueue pq, int index) {
	int pror = 2*index+2;
	return pror<pq->pqSize && pq->vector[pror] != UNVALID;
}


// �涨���ȼ�Ϊ ����ֵԽС�����ȼ�Խ�ߡ�
BOOL isHeapOrder(ElementType parent, ElementType child) {
	return parent <= child;	// �涨���ȼ�
}

/*
 * ��ȡ���Ϊx�Ľ��ĸ��׽��
 * Ĭ�Ͻ���Ŵ�0��ʼ
 */
int getParent(int index) {
	return (index-1) / 2;
}

/* �������λ�� */
void swap(PriorityQueue pq, int p, int *q) {
	ElementType temp = pq->vector[p];
	pq->vector[p] = pq->vector[*q];
	pq->vector[*q] = temp;
	*q = p;
}

/*
 * ���˲���
 * ʱ�临�Ӷ�O(log(n))������ȫ�������������
 */
void percoleareUp(PriorityQueue pq, int pos) {
	while( isMax(pos) )	// ֻҪi�и��ף�i��δ�ִ�Ѷ���
	{
		int parent = getParent(pos); // ��ȡpos�ĸ������
		// һ�����Ӳ��������������
		if(isHeapOrder(pq->vector[parent], pq->vector[pos]))
			break;
		swap(pq, parent, &pos);	// ��������λ�ã��½������һ��
	}
}

/*
 * ���˲���:�Խ����Ϊpos��Ԫ��
 * ʱ�临�Ӷ�O(log(n))������ȫ����������������
 */
void percoleareDown(PriorityQueue pq, int pos) {
	int biggerPos, rPos, lPos;		// bigger��ʾ���ȼ���
	BOOL lc = FALSE;
	BOOL rc = FALSE;
	while(TRUE)
	{
		if(hasLChild(pq, pos))	{ lPos = pos*2+1; lc = TRUE; }
		if(hasRChild(pq, pos))	{ rPos = pos*2+2; rc = TRUE; }
		if(lc && rc)	biggerPos = (pq->vector[rPos] < pq->vector[lPos])? rPos : lPos;
		else if(lc) 	biggerPos = lPos;	// ��������
		else if(rc) 	biggerPos = rPos;	// �����Һ���
		else break;	// Ҷ�ڵ��Զ��������
		if(!isHeapOrder(pq->vector[pos], pq->vector[biggerPos])) { // ��������������
			swap(pq, biggerPos, &pos);
			lc = rc = FALSE; // ��λ�²����޺��ӱ��
		}
		else break;	// һ����������������
	}
}

/*
 * ����Ԫ�ص���ȫ�����
 * ʱ�临�Ӷ�O(log(n))
 */
void insert(PriorityQueue pq, ElementType e) {
	pq->vector[pq->pqSize] = e;	// ������Ԫ��e������
	int pos = pq->pqSize;
	pq->pqSize++;
	percoleareUp(pq, pos);		// ���˼��
}

/* ��ȡ���ȼ���ߵ�Ԫ�� */
ElementType getMax(PriorityQueue pq) {
	return pq->vector[0];
}

// �������ȶ��� & ��α�����ȫ�����
void travPQ(PriorityQueue pq) {
	int i;
	printf("pq: ");
	for(i=0; i<pq->pqSize; i++) {
		printf("%d ", pq->vector[i]);
	}
	printf("\n\n");
}

/*
 * �Ѷ���ĩԪ�ضԻ�������
 * ɾ�����ȼ���ߵ�Ԫ��
 */
ElementType delMax(PriorityQueue pq) {
	int pos = pq->pqSize-1;
	ElementType max = pq->vector[0]; 	// ��ȡɾ��ǰ�Ѷ�
	swap(pq, 0, &pos);				 	// �����Ѷ���ĩԪ��
	pq->vector[pq->pqSize-1] = UNVALID;	// ɾ���Ѷ�
	pq->pqSize--;
	if(!isHeapEmpty(pq))
		percoleareDown(pq, pos);		// ���¶ѽ�������
	return max;
}

/*
 * ����ָ����������[lo, hi)���������
 * �������������������˲���
 * �ʱ�临�Ӷȣ�O(nlogn)
 */
void creaHeapForce(PriorityQueue pq, ElementType *arr, int lo, int hi) {
	int i;
	pq->vector[0] = arr[lo];
	pq->pqSize++;
	for(i=lo+1; i<hi; i++)
		insert(pq, arr[i]);
}

/*
 * ����ָ����������[lo, hi)���������
 * Floyd�㷨������Ԫ��������ˣ����ϲ������Ӷѣ�ֱ��������������
 * ʱ�临�Ӷ�O(n)
 */
void creaHeapFloyd(PriorityQueue pq, int *arr, int lo, int hi) {
	int i, j;
	pq->pqSize = hi-lo;
	for(i=0, j=lo; j<hi; i++, j++)
		pq->vector[i] = arr[j];
	int lastPos = pq->pqSize/2-1;	 // ��ȫ���������ڲ�ĩ����š� = [n/2]-1
	while(lastPos > -1)				 // ������Ԫ��������˺���ɴ��������
	{
		percoleareDown(pq, lastPos); // �����ڲ�Ԫ��������ˣ����ϲ������Ӷ�
		lastPos--;				     // ��������Ԫ�� & ��Ӧ��ȫ�������ڲ����
	}
	printf("After create Heap:\n");
	travPQ(pq);
}

/*
 * ������ָ����������[lo, hi)
 * �����������佨������ѣ���ͣɾ���Ѷ�Ԫ�ص�����������ֱ���ѿ�
 * ʱ�临�Ӷ�O(nlogn)
 */
void heapSort(PriorityQueue pq, int *arr, int lo, int hi) {
	creaHeapFloyd(pq, arr, lo, hi);	// �����������佨�������
	int i=0;
	while(!isHeapEmpty(pq))	// ��ͣɾ���Ѷ�Ԫ�ص�����������ֱ���ѿ�
	{
		arr[i++] = delMax(pq);
	}
	int j;
	printf("After HeapSort:\n");
	for(j=0; j<i; j++)
		printf("%d ", arr[j]);
}

/************************** ������ ***************************/
int main() {
	PriorityQueue pq = (PriorityQueue)malloc(sizeof(Heap));
	ElementType arr[] = {2, 1, 6, 3, 9, 7, 4, 8, 5};
//	ElementType arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	initial(pq, MAX_ELE_NUM);		// ����Ѷ�̬�����ڴ�
	creaHeapForce(pq, arr, 0, 9);	// ���������������
	travPQ(pq);
	creaHeapFloyd(pq, arr, 0, 9);	// floyd�㷨���������
	travPQ(pq);
	int i, max;
	for(i=9; i>0; i--) { // ����ɾ�������Ԫ�� && ���ȶ��ж�ͷ����
		max = delMax(pq);
		printf("max:%d, after del:\n", max);
		travPQ(pq);
	}
	heapSort(pq, arr, 4, 9);	// ������ָ����������

	destory(pq);	// ���ٶ���Ѷ�̬�����ڴ�
	return 0;
}
