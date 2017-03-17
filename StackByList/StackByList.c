/*
 * File name:StackByList.c
 * Description: 单向链表实现栈
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
// 判断是否空栈
int isEmpty(Node *pStack) {
	return pStack->next == NULL;
}

// push通过实现向链表前段插入实现
void push(Node *pStack, ElementType element) {
	Node *p = (Node *)malloc(sizeof(Node));
	p->element = element;
	if( isEmpty(pStack) ) { // 空栈push
		pStack->next = p;
		p->next = NULL;
	} else {
		p->next = pStack->next;
		pStack->next = p;
	}
}

// 出栈 删除/释放栈顶元素数据/内存空间
void pop(Node *pStack) {
	if( !isEmpty(pStack) ) {
		Node *pTop = pStack->next;  // 保留待出栈指针，便于出栈
		pStack->next = pTop->next;  // 更新栈顶
		free(pTop);
	}
}


// 获取栈顶元素
ElementType getTop(Node *pStack) {
	if( isEmpty(pStack) ) {
		return NULL;
	} else {
		return pStack->next->element;
	}
}

// 初始化栈
void initStack(Node *pStack) {
	pStack->next = NULL;
}

// 清空栈
void clearStack(Node *pStack) {
	if( isEmpty(pStack) )
		return;
	while( !isEmpty(pStack) ) // 不断pop栈顶直到栈空
	{
		pop(pStack);
	}
}

// 打印链表
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
