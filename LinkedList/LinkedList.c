/*
 * File name:LinkedList.c
 * Description: ��������������
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

// �ж������Ƿ�Ϊ��
int isEmpty(Node *pList) {
	return pList->next == NULL;
}

// �ж�ָ���Ƿ���Ч
int isNull(Node *p) {
	return p == NULL;
}

// ��������
void createList(Node *pList) {
	Node *pNew = (Node *)malloc(sizeof(Node)); // pNew�����½ڵ��ַ
	Node *pLast = NULL;	// pLast����β�ڵ��ַ
	if( isNull(pNew) ) {
		printf("memory failed\n");
		exit(0);
	}
	printf("Please input element:\n");
	int i;
	for(i=0; i<MAX_INPUT_NUM; i++) {
		scanf("%d", &pNew->element);
		if(isEmpty(pList)) { 		// ����Ϊ��
			pList->next = pNew;
			pLast = pNew;
			pLast->next = NULL;
		} else {
			pLast->next = pNew; // β�ڵ�ָ���½ڵ�
			pLast = pNew; 		// pLast�����½ڵ��ַ��pNew������һ���½ڵ�
		}
		pNew = (Node *)malloc(sizeof(Node)); // pNew������һ���½ڵ�
	}
	pLast->next = NULL;	// β�ڵ�ָ���
	/* malloc�������ɹ�����ָ�򱻷����ڴ��ָ��,���򷵻�NULL
	 * free(p)�Ľ����pָ��ĵ�ַ���䣬�����ͷ�ָ��ָ����ڴ������
	 */
	free(pNew);
}

// ˳�������������
void traverseList(Node *pList) {
	Node *pIter = pList->next;
	printf("\nPrint start\n");
	while(pIter != NULL)
	{
		printf("0x%x %d\n", pIter, pIter->element); // ��ӡָ�������
		pIter = pIter->next;
	}
	printf("Print end\n");
}

// ���ص�һ������ƥ��ڵ��ǰһ���ڵ�ָ��
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


// ����ָ���һ������ƥ��ڵ��ָ�룬��������NULL
Node *find(Node *pList, ElementType element) {
	Node *pIter = pList->next;
	while(pIter != NULL && pIter->element != element)
	{
		pIter = pIter->next;
	}
	return pIter;
}

// ɾ��ָ��Ԫ��
void delete(Node *pList, ElementType element) {
	Node *pre = findPrevious(pList, element); 	// pre->nextΪɾ�������ַ
	if(pre == pList && pre->next->next == NULL) {	// ɾ����ͷ������Ϊ������
		free(pre->next);
		pre->next = NULL;
	} else if(pre->next->next == NULL) {		// ɾ����β������ǿ�
		free(pre->next);
		pre->next = NULL;	// �ͷ����ݺ��ֹҰָ��
	} else {				// ɾ����ͷ������ǿջ�ɾ���м�ڵ�
		Node *tmp = pre->next;
		pre->next = pre->next->next;
		free(tmp);
	}
}

// �����ͷ
void insertHeader(Node *pList, ElementType element) {
	Node *p = (Node *)malloc(sizeof(Node));
	p->element = element;
	if( isEmpty(pList) ) { // ����������ͷ
		pList->next = p;
		p->next = NULL;
	} else {
		p->next = pList->next;
		pList->next = p;
	}
	traverseList(pList);
}

// �����β
void insertFooter(Node *pList, ElementType element) {
	Node *p = (Node *)malloc(sizeof(Node));
	p->element = element;
	if( isEmpty(pList) ) { // ����������β
		pList->next = p;
		p->next = NULL;
	} else {
		Node *pIter = pList->next;
		while( pIter != NULL ) // o(n)ʱ�临�ӶȲ��ұ�β
		{
			pIter = pIter->next;
		}
		pIter->next = p;
		p->next = NULL;
	}
	traverseList(pList);
}


// ��ת��������
void reverse(Node *pList) {
	Node *pIter = pList->next;
	Node *pre = NULL;
	Node *pNext = NULL;
	while(pIter != NULL) //
	{
		pNext = pIter->next;	// ��֤����pIterǰ��ȡpNext,��֤�ɱ�������
		pIter->next = pre;		// ��ת��������
		pre = pIter;			// ��ת��ɸ���pre��������һ�鷴ת
		pIter = pNext;          // ��ת��ɸ���pIter��������һ�鷴ת
	}
	pList->next = pre;			// ���±�ͷ
}
//////////////////////////////////////////////////////////
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	Node *pList = (Node *)malloc(sizeof(Node *));
	pList->next = NULL; // �涨pList->nextָ���ͷ

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

