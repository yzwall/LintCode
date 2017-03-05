/*
 * File name:PriorityQueueByHeap.c
 * Description: 1. 完全二叉堆实现优先队列，优先级设置为数值越小，优先级越高
 * 				2. 创建/插入二叉堆/上滤操作，删除堆顶元素/下滤操作
 * 				3. 堆排序
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

/* 完全二叉堆结构 */
typedef struct Heap {
	int capacity;
	int pqSize;	// 当前元素数目
	ElementType *vector;
} Heap, *PriorityQueue;

/* 初始化优先队列 */
void initial(PriorityQueue pq, int maxElements) {
	pq->capacity = maxElements;
	pq->pqSize = 0;
	pq->vector = (ElementType *)malloc(maxElements * sizeof(ElementType));
}

/* 销毁优先队列动态分配内存 */
void destory(PriorityQueue pq) {
	free(pq->vector);
	free(pq);
}

BOOL isHeapEmpty(PriorityQueue pq) {
	return pq->pqSize == 0;
}

/* 判断编号为x的结点是否有父亲 */
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


// 规定优先级为 “数值越小，优先级越高”
BOOL isHeapOrder(ElementType parent, ElementType child) {
	return parent <= child;	// 规定优先级
}

/*
 * 获取编号为x的结点的父亲结点
 * 默认结点编号从0开始
 */
int getParent(int index) {
	return (index-1) / 2;
}

/* 交换结点位置 */
void swap(PriorityQueue pq, int p, int *q) {
	ElementType temp = pq->vector[p];
	pq->vector[p] = pq->vector[*q];
	pq->vector[*q] = temp;
	*q = p;
}

/*
 * 上滤操作
 * 时间复杂度O(log(n))，与完全二叉树高正相关
 */
void percoleareUp(PriorityQueue pq, int pos) {
	while( isMax(pos) )	// 只要i有父亲（i尚未抵达堆顶）
	{
		int parent = getParent(pos); // 获取pos的父结点编号
		// 一旦父子不再逆序，上滤完成
		if(isHeapOrder(pq->vector[parent], pq->vector[pos]))
			break;
		swap(pq, parent, &pos);	// 交换父子位置，新结点上升一层
	}
}

/*
 * 下滤操作:对结点编号为pos的元素
 * 时间复杂度O(log(n))，与完全二叉树的深度正相关
 */
void percoleareDown(PriorityQueue pq, int pos) {
	int biggerPos, rPos, lPos;		// bigger表示优先级高
	BOOL lc = FALSE;
	BOOL rc = FALSE;
	while(TRUE)
	{
		if(hasLChild(pq, pos))	{ lPos = pos*2+1; lc = TRUE; }
		if(hasRChild(pq, pos))	{ rPos = pos*2+2; rc = TRUE; }
		if(lc && rc)	biggerPos = (pq->vector[rPos] < pq->vector[lPos])? rPos : lPos;
		else if(lc) 	biggerPos = lPos;	// 仅有左孩子
		else if(rc) 	biggerPos = rPos;	// 仅有右孩子
		else break;	// 叶节点自动满足堆序
		if(!isHeapOrder(pq->vector[pos], pq->vector[biggerPos])) { // 不满足堆序就下滤
			swap(pq, biggerPos, &pos);
			lc = rc = FALSE; // 复位下层有无孩子标记
		}
		else break;	// 一旦满足堆序，下滤完成
	}
}

/*
 * 插入元素到完全二叉堆
 * 时间复杂度O(log(n))
 */
void insert(PriorityQueue pq, ElementType e) {
	pq->vector[pq->pqSize] = e;	// 插入新元素e到向量
	int pos = pq->pqSize;
	pq->pqSize++;
	percoleareUp(pq, pos);		// 上滤检查
}

/* 获取优先级最高的元素 */
ElementType getMax(PriorityQueue pq) {
	return pq->vector[0];
}

// 遍历优先队列 & 层次遍历完全二叉堆
void travPQ(PriorityQueue pq) {
	int i;
	printf("pq: ");
	for(i=0; i<pq->pqSize; i++) {
		printf("%d ", pq->vector[i]);
	}
	printf("\n\n");
}

/*
 * 堆顶与末元素对换后下滤
 * 删除优先级最高的元素
 */
ElementType delMax(PriorityQueue pq) {
	int pos = pq->pqSize-1;
	ElementType max = pq->vector[0]; 	// 获取删除前堆顶
	swap(pq, 0, &pos);				 	// 交换堆顶与末元素
	pq->vector[pq->pqSize-1] = UNVALID;	// 删除堆顶
	pq->pqSize--;
	if(!isHeapEmpty(pq))
		percoleareDown(pq, pos);		// 对新堆进行下滤
	return max;
}

/*
 * 根据指定向量区间[lo, hi)创建二叉堆
 * 蛮力法：批量进行上滤操作
 * 最坏时间复杂度：O(nlogn)
 */
void creaHeapForce(PriorityQueue pq, ElementType *arr, int lo, int hi) {
	int i;
	pq->vector[0] = arr[lo];
	pq->pqSize++;
	for(i=lo+1; i<hi; i++)
		insert(pq, arr[i]);
}

/*
 * 根据指定向量区间[lo, hi)创建二叉堆
 * Floyd算法：向量元素逐个下滤，逐层合并左右子堆，直到根结点完成下滤
 * 时间复杂度O(n)
 */
void creaHeapFloyd(PriorityQueue pq, int *arr, int lo, int hi) {
	int i, j;
	pq->pqSize = hi-lo;
	for(i=0, j=lo; j<hi; i++, j++)
		pq->vector[i] = arr[j];
	int lastPos = pq->pqSize/2-1;	 // 完全二叉树“内部末结点编号” = [n/2]-1
	while(lastPos > -1)				 // 向量首元素完成下滤后，完成创建二叉堆
	{
		percoleareDown(pq, lastPos); // 向量内部元素逐个下滤，逐层合并左右子堆
		lastPos--;				     // 迭代向量元素 & 对应完全二叉树内部结点
	}
	printf("After create Heap:\n");
	travPQ(pq);
}

/*
 * 堆排序指定向量区间[lo, hi)
 * 根据向量区间建立二叉堆，不停删除堆顶元素到有序向量，直到堆空
 * 时间复杂度O(nlogn)
 */
void heapSort(PriorityQueue pq, int *arr, int lo, int hi) {
	creaHeapFloyd(pq, arr, lo, hi);	// 根据向量区间建立二叉堆
	int i=0;
	while(!isHeapEmpty(pq))	// 不停删除堆顶元素到有序向量，直到堆空
	{
		arr[i++] = delMax(pq);
	}
	int j;
	printf("After HeapSort:\n");
	for(j=0; j<i; j++)
		printf("%d ", arr[j]);
}

/************************** 主程序 ***************************/
int main() {
	PriorityQueue pq = (PriorityQueue)malloc(sizeof(Heap));
	ElementType arr[] = {2, 1, 6, 3, 9, 7, 4, 8, 5};
//	ElementType arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	initial(pq, MAX_ELE_NUM);		// 二叉堆动态分配内存
	creaHeapForce(pq, arr, 0, 9);	// 蛮力法创建二叉堆
	travPQ(pq);
	creaHeapFloyd(pq, arr, 0, 9);	// floyd算法创建二叉堆
	travPQ(pq);
	int i, max;
	for(i=9; i>0; i--) { // 批量删除“最大元” && 优先队列队头出队
		max = delMax(pq);
		printf("max:%d, after del:\n", max);
		travPQ(pq);
	}
	heapSort(pq, arr, 4, 9);	// 堆排序指定向量区间

	destory(pq);	// 销毁二叉堆动态分配内存
	return 0;
}
