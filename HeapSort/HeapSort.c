/*
 * File name:HeapSort.c
 * Description: ∂—≈≈–Ú
 * Author: yzwall
 * Data: 16/10/24 18:03
 */
#include <stdio.h>
#include <stdlib.h>
#define TRUE 1
#define FALSE 0
#define MAX_ELE_NUM 10
typedef int BOOL;
typedef int ElementType;

typedef struct Heap {
	int capacity;
	int size;
	ElementType *vector;
} Heap, HeapPtr;


void initial(HeapPtr heap, int num) {
	heap->capacity = num;
	heap->size = 0;
	heap->vector = (ElementType *)malloc(num * sizeof(ElementType));
}

void percoleareDown(HeapPtr heap) {

}


void insert(HeapPtr heap, ElementType element) {


}


ElementType delMax(HeapPtr heap) {

}

void heapSort(HeapPtr heap, ElementType *arr) {
	int i;
	for(i=0; i<)

}


void travArray(ElementType *arr) {
	int i;
	for(i=0; i<)
}

/************************ ÷˜≥Ã–Ú ************************/
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);

	ElementType arr[] = {2, 1, 6, 3, 9, 7, 4, 8, 5, 0};
	HeapPtr heap = (HeapPtr)malloc(sizeof(Heap));
	initial(heap, MAX_ELE_NUM);
	heapSort(heap, arr);
	travArray(arr);

	return 0;
}
