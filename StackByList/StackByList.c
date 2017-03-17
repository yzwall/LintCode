/*
 * File name:StackByList.c
 * Description: ��������ʵ��ջ
 * Author: yzwall
 * Data: 2016/8/30 11:32
 */
#include <stdio.h>
#include <stdlib.h>
//////////////////////////////////////////////
typedef int ElementType;
typedef struct Node {
	ElementType element;
	struct Node *next;
}Node;
//////////////////////////////////////////////
// �ж��Ƿ��ջ
int isEmpty(Node *pStack) {
	return pStack->next == NULL;
}

// pushͨ��ʵ��������ǰ�β���ʵ��
void push(Node *pStack, ElementType element) {
	Node *p = (Node *)malloc(sizeof(Node));
	p->element = element;
	if( isEmpty(pStack) ) { // ��ջpush
		pStack->next = p;
		p->next = NULL;
	} else {
		p->next = pStack->next;
		pStack->next = p;
	}
}

// ��ջ ɾ��/�ͷ�ջ��Ԫ������/�ڴ�ռ�
void pop(Node *pStack) {
	if( !isEmpty(pStack) ) {
		Node *pTop = pStack->next;  // ��������ջָ�룬���ڳ�ջ
		pStack->next = pTop->next;  // ����ջ��
		free(pTop);
	}
}


// ��ȡջ��Ԫ��
ElementType getTop(Node *pStack) {
	if( isEmpty(pStack) ) {
		return NULL;
	} else {
		return pStack->next->element;
	}
}

// ��ʼ��ջ
void initStack(Node *pStack) {
	pStack->next = NULL;
}

// ���ջ
void clearStack(Node *pStack) {
	if( isEmpty(pStack) )
		return;
	while( !isEmpty(pStack) ) // ����popջ��ֱ��ջ��
	{
		pop(pStack);
	}
}

// ��ӡ����
void printList(Node *pStack) {
	Node *pIter = pStack->next;
	printf("\nPrint Start\n");
	ElementType top = getTop(pStack);
	if(top != NULL) {
		printf("top: 0x%x %d\n", (unsigned int)pIter, pIter->element);
		pIter = pIter->next;
		while(pIter != NULL)
		{
			printf("     0x%x %d\n", (unsigned int)pIter, pIter->element);
			pIter = pIter->next;
		}
		printf("Print End\n");
	} else {
		printf("top: NULL");
	}
}
///////////////////////////////////////////////////////
int main() {
	Node *pStack = (Node *)malloc(sizeof(Node));
	initStack(pStack);
	push(pStack, 1);
	push(pStack, 2);
	pop(pStack);
	push(pStack, 4);
	printList(pStack);

	clearStack(pStack);
	printList(pStack);

	return 0;
}
