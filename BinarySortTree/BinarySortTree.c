/*
 * File name:BinarySortTree.c
 * Description: ����������Ĳ���/����/ɾ���������ݹ�/�����������������
 * Author: yzwall
 * Data: 2016/10/13 20:16
 */
#include <stdio.h>
#include <stdlib.h>
#define END -1
#define TRUE 1
#define FALSE 0
#define EMPTY_STACK -1
//////////////////////////////////////////////////
typedef int ElementType;
typedef int BOOL;
// ������������
typedef struct BiTreeNode {
	ElementType element;
	struct BiTreeNode *lchild;
	struct BiTreeNode *rchild;
	struct BiTreeNode *parent; // �ǵ��Ͷ������ֶΣ�������������汾��Ҫ
} BiTreeNode, *BiTreePtr;

// ջ&������н��
typedef struct StackNode {
	BiTreePtr element;
	struct StackNode *next;
} StackNode, QueueNode, *QueueNodePtr, *StackPtr;

// ����
typedef struct Queue {
	QueueNode *front;
	QueueNode *rear;
} Queue, *QueuePtr;

/************** ����������������� ***************/
// ����ǰ�����д������������
BiTreePtr createBST() {
	BiTreePtr BST;
	ElementType element;
	scanf("%d", &element);
	if(element == END) {
		BST = NULL;
	} else {
		BST = (BiTreePtr)malloc(sizeof(BiTreeNode));
		if(BST == NULL) {
			printf("BST memory allocate failed.\n");
			exit(END);
		}
		BST->element = element;
		BST->lchild = createBST();
		BST->rchild = createBST();
	}
	return BST;
}

/*
 * �����������������
 * Tָ�����ѯ������ң��ӣ���
 * fΪ��ѯ���ĸ���㣬�Ӹ���㿪ʼ��ʼΪNULL
 * p��ѯ�ɹ�ʱָ�����ѯ��㣬��ѯʧ��ʱָ�����·�����һ�����
 */
BOOL searchBST(BiTreePtr T, ElementType key, BiTreePtr f, BiTreePtr *p) {
	if(T == NULL) {			// �ݹ���ڣ�����ʧ�ܣ�����Ҷ�ӽ�㣬fΪ����·���ϵ����һ�����
		*p = f;
		return FALSE;
	}
	if(key == T->element) {	// �ݹ���ڣ����ҳɹ���pָ����������ݽ��
		*p = T;
		return TRUE;
	} else if(key < T->element) {	// ��T���������ڵݹ��ѯ
		return searchBST(T->lchild, key, T, p);
	} else {						// ��T���������ڵݹ��ѯ
		return searchBST(T->rchild, key, T, p);
	}
}


/*
 * ����������������루�������ظ�Ԫ�أ�
 * ������Ԫ��key������ɹ�����TRUE
 * �Ѵ���Ԫ��key������ʧ�ܷ���FALSE
 */
BOOL insertBST(BiTreePtr *bst, ElementType key) {
	BiTreePtr p;
	if(searchBST(*bst, key, NULL, &p) == FALSE) {
		BiTreePtr new = (BiTreePtr)malloc(sizeof(BiTreeNode));
		new->element = key;
		new->lchild = NULL;
		new->rchild = NULL;
		if(p == NULL) {	// ��������new��Ϊ�����
			*bst = new;
			(*bst)->parent = NULL; // �������Ĭ���޸����
		} else {		// pָ���������ĸ���㣬���ݴ�С���½�����Ϊ��/�Ҷ���
			if(key < p->element)	p->lchild = new;
			else	p->rchild = new;
			new->parent = p;
		}
		return TRUE;
	} else {
		return FALSE;	// BST��Ԫ�ز����ظ��������ظ�Ԫ�أ�����ʧ��
	}
}

/*
 * ɾ����ǰ���p
 * p�Ƕ���ָ�룬ָ���ϼ��ݹ��*bst->lchild/lchild
 * �޸�p�ȼ����޸�*bst->lchild/lchild
 */
void deleteNode(BiTreePtr *p) {
	BiTreePtr q, s;
	if(!(*p)->rchild) {			// ������Ϊ�գ�ֻ������������
		q = *p;
		*p = (*p)->lchild;
		free(q);
	} else if(!(*p)->lchild) {	// ������Ϊ�գ�ֻ������������
		q = *p;
		(*p) = (*p)->rchild;
		free(q);
	/*
	 * ������������Ϊ��
	 * �㷨���ҵ���ɾ�����p��ֱ��ǰ��orֱ�Ӻ��s����s���p�����ɾ������ظ����ֵ�s
	 * ֱ��ǰ����ֱ�Ӻ�����������˳�����
	 * ֱ��ǰ������ǰ�����������е����ֵ/���ҽ�㣬������Ϊ��
	 * ֱ�Ӻ�̣����ڽ����������е���Сֵ/�����㣬������Ϊ��
	 * ֱ��ǰ��<��ǰ���ֵ<ֱ�Ӻ��
	 */
	} else {
		q = *p;
		s = (*p)->lchild;
		/*
		 * *s��ʾ��ɾ����ֱ��ǰ��
		 * *q��ʾֱ��ǰ���ĸ����
		 */
		while(s->rchild)
		{
			q = s;
			s = s->rchild;
		}
		(*p)->element = s->element;	// ���´�ɾ�����ֵΪֱ��ǰ��ֵ
		/*
		 * *p�����ӷ�*p��ֱ��ǰ��, �����������գ�q != p, �ؽ�s����������q��, ɾ��s
		 * *p��������*p��ֱ��ǰ��, ��������Ϊ�գ�q == p, �ؽ�s����������q/p��, ɾ��s
		 */
		if(q != p) {
			q->rchild = s->lchild;
		} else {
			q->lchild = s->lchild;
		}
		free(s);
	}
}

/*
 * �������������ɾ��key��Ӧ�Ľ��
 * ����ָ�봫�룬ʵ��һ��ָ�봫ַ������ݹ麯��������ֻ�ǿ���
 * &��*��Ϊ�����㣬&ȡַ��*ȡֵ
 */
BOOL deleteBST(BiTreePtr *bst, ElementType key){
	if(*bst == NULL) {					// �ݹ���ڣ�δ�ҵ�key��Ӧ���
		return FALSE;
	} else {
		if((*bst)->element == key) {	// �ݹ���ڣ��ҵ�key��Ӧ���
			deleteNode(bst);
			return TRUE;
		} else if(key > (*bst)->element) {
			return deleteBST( &(*bst)->rchild, key );
		} else {
			return deleteBST( &(*bst)->lchild, key );
		}
	}
}

// �жϽ���Ƿ�������
BOOL hasLChild(BiTreePtr x) {
	return x->lchild != NULL;
}

// �жϽ���Ƿ����Һ���
BOOL hasRChild(BiTreePtr x) {
	return x->rchild != NULL;
}

// ���ʽ��Ԫ��
void visit(BiTreePtr x) {
	if(x) {
		printf("%d ", x->element);
	}
}

/************** ����������ݹ�&���ֵ�������ʵ�� ***************/
// �ж��Ƿ��ջ
BOOL isEmpty(StackPtr pStack) {
	return pStack->next == NULL;
}

// ��ջ
void push(StackPtr pStack, BiTreePtr element) {
	StackPtr pNew = (StackPtr)malloc(sizeof(StackNode));
	pNew->element = element;
	if(isEmpty(pStack)) {	// ��ջpush
		pStack->next = pNew;
		pNew->next = NULL;
	} else {				// pStack->nextʼ��ָ��ջ��
		pNew->next = pStack->next;
		pStack->next = pNew;
	}
}

// ��ջ
BiTreePtr pop(StackPtr pStack) {
	if(!isEmpty(pStack)) {
		StackPtr pTop = pStack->next;
		pStack->next = pTop->next;
		return pTop->element;
	} else {
		return NULL;	// ��ջ����NULL
	}
}

// ��ȡջ��Ԫ��
BiTreePtr top(StackPtr pStack) {
	return pStack->next->element;
}





/* �����������������-1
 * ����ջ
 * ����˼�룺�������ջ������ջFILO�����ԣ��ȱ��������Ӻ����ȳ�����������Һ���������
 */
void travPreOrderIter_1(BiTreePtr bst) {
	StackPtr pStack = (StackPtr)malloc(sizeof(StackNode));
	pStack->next = NULL;
	push(pStack, bst);	// �������ջ
	while(!isEmpty(pStack))
	{
		BiTreePtr x = pop(pStack);
		visit(x);
		if(hasRChild(x))	push(pStack, x->rchild);	// �Һ���������
		if(hasLChild(x))	push(pStack, x->lchild);	// ���Ӻ����ȳ�
	}
}

// ��������x������������϶��½���������ջ
void visitLeftBranch(StackPtr pStack, BiTreePtr x) {
	while(x)	// ���������
	{
		visit(x);
		if(x->rchild)	// ���������϶�����ջ������ջFILO���ԣ��������¶��ϳ�ջ��
			push(pStack, x->rchild);
		x = x->lchild;	// �����������
	}
}

/* �����������������-2
 * ʱ�临�Ӷ�O(n)
 * ����˼�룺�����϶��·���������ϵĽڵ㣬��ÿ��������ڵ���Һ�����ջ�������¶��Ϸ������ǵ�������
 */
void travPreOrderIter_2(BiTreePtr bst) {
	StackPtr pStack = (StackPtr)malloc(sizeof(StackNode));
	pStack->next = NULL;
	BiTreePtr x = bst;
	while(x != NULL)	// ��ջ�˳�
	{
		visitLeftBranch(pStack, x);	// ��������x������������϶��½���������ջ
		x = pop(pStack);			// ������һ���������ĸ������¶��ϳ�ջ
	}
}

/*
 * ����ݹ���������������¶��ϵݹ�
 */
void travPreOrder(BiTreePtr x) {
	if(x == NULL) {	// ����ݹ��
		//visit(x);
		return;
	} else {
		visit(x);
		travPreOrder(x->lchild);	// �ݹ����������
		travPreOrder(x->rchild);	// �ݹ����������
	}
}
/********************* ������� �ݹ�&����ʵ�� *****************************/

// ����������أ������ջ
void goAlongLeftBranch(BiTreePtr x, StackPtr pStack) {
	while(x)
	{
		push(pStack, x);
		x = x->lchild;
	}
}

/* �����������������
 * ʱ�临�Ӷ�O(n)
 * ����˼�룺������������У����¶��Ϸ����������㣬��������Ӧ��������
 */
void travInOrderIter(BiTreePtr bst) {
	StackPtr pStack = (StackPtr)malloc(sizeof(StackNode));
	pStack->next = NULL;
	BiTreePtr x = bst;
	while(TRUE)
	{
		goAlongLeftBranch(x, pStack);	// �ӵ�ǰ��������������������أ������ջ��ֱ������������
		if(isEmpty(pStack)) break;		// ���н��������
		x = pop(pStack);				// ������������¶��Ϸ��ʽ��
		visit(x);						// ��������
		x = x->rchild;					// ת����������
	}
}

/* ����ݹ�������������
 * ʱ�临�Ӷ�O(n)
 */
void travInOrder(BiTreePtr x) {
	if(x == NULL) {
//		visit(x);
		return;
	} else {
		travInOrder(x->lchild);
		visit(x);
		travInOrder(x->rchild);
	}
}

/********************* ������� �ݹ�&����ʵ�� *****************************/
/* ����pStackջ�����Ϊ���������У���ȡ������Ҷ��㣨Highest Left Leaf��,
 * �Զ����²���HLL���ʱ��ͨ��ջ���򱣴��㣬ʼ�վ��������󣬽���������Ϊ��ʱ������
 * HLL�������丸�������ӻ����Һ���
 */
void gotoHLL(StackPtr pStack) {
	BiTreePtr x= top(pStack);
	while(x->lchild || x->rchild)	// ��λ��HLL����ѭ��
	{
		if(hasLChild(x)) {
			if(hasRChild(x)) {		// ����������ҽ������ջ��FILO
				push(pStack, x->rchild);
			}
			push(pStack, x->lchild);// ����������������ջ��LIFO
		} else {	// ʼ�վ��������󣬽���������Ϊ��ʱ������
			push(pStack, x->rchild);
		}
		x = top(pStack);			// ջ��Ԫ��ΪHLL
	}
}

/* ��������������������
 * ʱ�临�Ӷ�O(n)
 * ����˼�룺��ȡHLL��������������ȷ��ʵĽ��
 */
void travPostOrderIter(BiTreePtr bst) {
	StackPtr pStack = (StackPtr)malloc(sizeof(StackNode));
	pStack->next = NULL;
	BiTreePtr x = bst;
	push(pStack, x);
	while(!isEmpty(pStack))
	{
		if(top(pStack) != x->parent) { // ����ǰ���x�����ֵܴ��ڣ����ֵ�Ϊջ��Ԫ�أ�
			gotoHLL(pStack); // ����gotoHLL()ת���Ը��ֵ�Ϊ�������� ,��λHLL
		}
		x = pop(pStack);	// ջ��Ԫ��ΪHLL
		visit(x);
	}
}


/* ����ݹ�������������
 * ʱ�临�Ӷ�O(n)
 */
void travPostOrder(BiTreePtr x) {
	if(x == NULL) {
//		visit(x);
		return;
	} else {
		travPostOrder(x->lchild);
		travPostOrder(x->rchild);
		visit(x);
	}
}

/********************* ��α���  ����ʵ��*****************************/
// �ж϶����Ƿ�Ϊ��
BOOL isQueueEmpty(QueuePtr Queue) {
	return Queue->front == Queue->rear;
}

// ���
void enqueue(QueuePtr Queue, BiTreePtr element) {
	QueueNodePtr pNew = (QueueNodePtr)malloc(sizeof(QueueNode));
	pNew->next = NULL;					// ���pNewΪrear->next����ռ�
	Queue->rear->next = pNew;
	Queue->rear->element = element;		// Ԫ�ؼ����β
	Queue->rear = Queue->rear->next;	// rearʼ��ά����β
}

// ����
BiTreePtr dequeue(QueuePtr Queue) {
	QueueNodePtr p = Queue->front;
	Queue->front = Queue->front->next;
	return p->element;
}

/* ��α������������
 * ʱ�临�Ӷ�O(n)
 */
void travLevelOrderIter(BiTreePtr bst) {
	QueuePtr Queue = (QueuePtr)malloc(sizeof(Queue));
	Queue->front = (QueueNodePtr)malloc(sizeof(QueueNode));
	Queue->rear = (QueueNodePtr)malloc(sizeof(QueueNode));
	Queue->rear->next = NULL;
	Queue->front = Queue->rear;
	enqueue(Queue, bst);
	while(!isQueueEmpty(Queue))
	{
		BiTreePtr x = dequeue(Queue);
		visit(x);
		if(hasLChild(x)) enqueue(Queue, x->lchild); // ��α���������&��������� FIFO
		if(hasRChild(x)) enqueue(Queue, x->rchild);
	}
}


/********************* ������  *****************************/
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);

	BiTreePtr bst = NULL;
	int num[] = {62, 88, 58, 47, 35, 73, 51, 99, 37, 93};
	int i;
	for(i=0; i<10; i++) {
		insertBST(&bst, num[i]);
	}
	/* ������� */
	printf("Preorder Traverse:");
	printf("\nRecursive Implement  : ");
	travPreOrder(bst);
	printf("\nIterative Implement 1: ");
	travPreOrderIter_1(bst);
	printf("\nIterative Implement 2: ");
	travPreOrderIter_2(bst);
	/* ������� */
	printf("\n\nInorder Traverse:");
	printf("\nRecursive Implement  : ");
	travInOrder(bst);
	printf("\nIterative Implement  : ");
	travInOrderIter(bst);
	/* ������� */
	printf("\n\nPostorder Traverse:");
	printf("\nRecursive Implement  : ");
	travPostOrder(bst);
	printf("\nIterative Implement  : ");
	travPostOrderIter(bst);
	/* ������� */
	printf("\n\nLevelorder Traverse:");
	printf("\nIterative Implement  : ");
	travLevelOrderIter(bst);
	/*deleteBST(&bst, 47);
	printf("\n");
	travInOrder(bst);*/

	return 0;
}
