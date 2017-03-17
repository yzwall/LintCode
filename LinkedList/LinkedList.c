/*
 * File name:LinkedList.c
 * Description: 单向链表各项操作
 * Author: yzwall
 * Data: 2016/9/1 13:46
 */
#include <stdio.h>
#include <stdlib.h>
/////////////////////////
#define MAX_INPUT_NUM 3
typedef int ElementType;

struct Node {
	ElementType element;
	struct Node *next;
};
typedef struct Node Node;

// 判断链表是否为空
int isEmpty(Node *pList) {
	return pList->next == NULL;
}

// 判断指针是否有效
int isNull(Node *p) {
	return p == NULL;
}

// 创建链表
void createList(Node *pList) {
	Node *pNew = (Node *)malloc(sizeof(Node)); // pNew保存新节点地址
	Node *pLast = NULL;	// pLast保存尾节点地址
	if( isNull(pNew) ) {
		printf("memory failed\n");
		exit(0);
	}
	printf("Please input element:\n");
	int i;
	for(i=0; i<MAX_INPUT_NUM; i++) {
		scanf("%d", &pNew->element);
		if(isEmpty(pList)) { 		// 链表为空
			pList->next = pNew;
			pLast = pNew;
			pLast->next = NULL;
		} else {
			pLast->next = pNew; // 尾节点指向新节点
			pLast = pNew; 		// pLast保存新节点地址，pNew开辟下一个新节点
		}
		pNew = (Node *)malloc(sizeof(Node)); // pNew开辟下一个新节点
	}
	pLast->next = NULL;	// 尾节点指向空
	/* malloc如果分配成功返回指向被分配内存的指针,否则返回NULL
	 * free(p)的结果是p指向的地址不变，但是释放指针指向的内存块数据
	 */
	free(pNew);
}

// 顺序遍历单向链表
void traverseList(Node *pList) {
	Node *pIter = pList->next;
	printf("\nPrint start\n");
	while(pIter != NULL)
	{
		printf("0x%x %d\n", pIter, pIter->element); // 打印指针和数据
		pIter = pIter->next;
	}
	printf("Print end\n");
}

// 返回第一个查找匹配节点的前一个节点指针
Node *findPrevious(Node *pList, ElementType element) {
	Node *pIter = pList->next;
	Node *pre = pList;
	while(pIter != NULL && pIter->element != element)
	{
		pre = pIter;
		pIter = pIter->next;
	}
	if(pIter->element != element) {
		return NULL;
	}
	return pre;
}


// 返回指向第一个查找匹配节点的指针，空链表返回NULL
Node *find(Node *pList, ElementType element) {
	Node *pIter = pList->next;
	while(pIter != NULL && pIter->element != element)
	{
		pIter = pIter->next;
	}
	return pIter;
}

// 删除指定元素
void delete(Node *pList, ElementType element) {
	Node *pre = findPrevious(pList, element); 	// pre->next为删除区域地址
	if(pre == pList && pre->next->next == NULL) {	// 删除表头后链表为空链表
		free(pre->next);
		pre->next = NULL;
	} else if(pre->next->next == NULL) {		// 删除表尾后链表非空
		free(pre->next);
		pre->next = NULL;	// 释放数据后防止野指针
	} else {				// 删除表头后链表非空或删除中间节点
		Node *tmp = pre->next;
		pre->next = pre->next->next;
		free(tmp);
	}
}

// 插入表头
void insertHeader(Node *pList, ElementType element) {
	Node *p = (Node *)malloc(sizeof(Node));
	p->element = element;
	if( isEmpty(pList) ) { // 空链表插入表头
		pList->next = p;
		p->next = NULL;
	} else {
		p->next = pList->next;
		pList->next = p;
	}
	traverseList(pList);
}

// 插入表尾
void insertFooter(Node *pList, ElementType element) {
	Node *p = (Node *)malloc(sizeof(Node));
	p->element = element;
	if( isEmpty(pList) ) { // 空链表插入表尾
		pList->next = p;
		p->next = NULL;
	} else {
		Node *pIter = pList->next;
		while( pIter != NULL ) // o(n)时间复杂度查找表尾
		{
			pIter = pIter->next;
		}
		pIter->next = p;
		p->next = NULL;
	}
	traverseList(pList);
}


// 反转单向链表
void reverse(Node *pList) {
	Node *pIter = pList->next;
	Node *pre = NULL;
	Node *pNext = NULL;
	while(pIter != NULL) //
	{
		pNext = pIter->next;	// 保证更改pIter前获取pNext,保证可遍历链表
		pIter->next = pre;		// 反转单向链表
		pre = pIter;			// 反转完成更新pre，进行下一组反转
		pIter = pNext;          // 反转完成更新pIter，进行下一组反转
	}
	pList->next = pre;			// 更新表头
}
//////////////////////////////////////////////////////////
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	Node *pList = (Node *)malloc(sizeof(Node *));
	pList->next = NULL; // 规定pList->next指向表头

	createList(pList);
	traverseList(pList);
	delete(pList, 2);
	insertFooter(pList, 2);
	delete(pList, 3);
	insertFooter(pList, 3);
	delete(pList, 1);
	insertFooter(pList, 1);
	reverse(pList);
	traverseList(pList);

	return 0;
}

