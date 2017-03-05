/*
 * File name:BinarySortTree.c
 * Description: 二叉查找树的查找/插入/删除操作，递归/迭代遍历二叉查找树
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
// 二叉查找树结点
typedef struct BiTreeNode {
	ElementType element;
	struct BiTreeNode *lchild;
	struct BiTreeNode *rchild;
	struct BiTreeNode *parent; // 非典型二叉树字段，后序迭代遍历版本需要
} BiTreeNode, *BiTreePtr;

// 栈&单向队列结点
typedef struct StackNode {
	BiTreePtr element;
	struct StackNode *next;
} StackNode, QueueNode, *QueueNodePtr, *StackPtr;

// 队列
typedef struct Queue {
	QueueNode *front;
	QueueNode *rear;
} Queue, *QueuePtr;

/************** 二叉查找树基本操作 ***************/
// 根据前序序列创建二叉查找树
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
 * 二叉查找树——查找
 * T指向待查询二叉查找（子）树
 * f为查询结点的父结点，从根结点开始初始为NULL
 * p查询成功时指向待查询结点，查询失败时指向查找路径最后一个结点
 */
BOOL searchBST(BiTreePtr T, ElementType key, BiTreePtr f, BiTreePtr *p) {
	if(T == NULL) {			// 递归出口：查找失败，到达叶子结点，f为查找路径上的最后一个结点
		*p = f;
		return FALSE;
	}
	if(key == T->element) {	// 递归出口：查找成功，p指向待查找数据结点
		*p = T;
		return TRUE;
	} else if(key < T->element) {	// 在T的左子树内递归查询
		return searchBST(T->lchild, key, T, p);
	} else {						// 在T的右子树内递归查询
		return searchBST(T->rchild, key, T, p);
	}
}


/*
 * 二叉查找树——插入（不允许重复元素）
 * 不存在元素key，插入成功返回TRUE
 * 已存在元素key，插入失败返回FALSE
 */
BOOL insertBST(BiTreePtr *bst, ElementType key) {
	BiTreePtr p;
	if(searchBST(*bst, key, NULL, &p) == FALSE) {
		BiTreePtr new = (BiTreePtr)malloc(sizeof(BiTreeNode));
		new->element = key;
		new->lchild = NULL;
		new->rchild = NULL;
		if(p == NULL) {	// 空树插入new作为根结点
			*bst = new;
			(*bst)->parent = NULL; // 树根结点默认无父结点
		} else {		// p指向待插入结点的父结点，根据大小将新结点插入为左/右儿子
			if(key < p->element)	p->lchild = new;
			else	p->rchild = new;
			new->parent = p;
		}
		return TRUE;
	} else {
		return FALSE;	// BST中元素不能重复，存在重复元素，插入失败
	}
}

/*
 * 删除当前结点p
 * p是二级指针，指向上级递归的*bst->lchild/lchild
 * 修改p等价于修改*bst->lchild/lchild
 */
void deleteNode(BiTreePtr *p) {
	BiTreePtr q, s;
	if(!(*p)->rchild) {			// 右子树为空，只需连接左子树
		q = *p;
		*p = (*p)->lchild;
		free(q);
	} else if(!(*p)->lchild) {	// 左子树为空，只需连接右子树
		q = *p;
		(*p) = (*p)->rchild;
		free(q);
	/*
	 * 左右子树均不为空
	 * 算法：找到待删除结点p的直接前驱or直接后继s，用s替代p，最后删除因此重复出现的s
	 * 直接前驱和直接后继由中序遍历顺序决定
	 * 直接前驱：当前结点的左子树中的最大值/最右结点，右子树为空
	 * 直接后继：当期结点的右子树中的最小值/最左结点，左子树为空
	 * 直接前驱<当前结点值<直接后继
	 */
	} else {
		q = *p;
		s = (*p)->lchild;
		/*
		 * *s表示待删结点的直接前驱
		 * *q表示直接前驱的父结点
		 */
		while(s->rchild)
		{
			q = s;
			s = s->rchild;
		}
		(*p)->element = s->element;	// 更新待删除结点值为直接前驱值
		/*
		 * *p的左孩子非*p的直接前驱, 其右子树不空，q != p, 重接s的右子树到q上, 删除s
		 * *p的左孩子是*p的直接前驱, 其右子树为空，q == p, 重接s的左子树到q/p上, 删除s
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
 * 二叉查找树——删除key对应的结点
 * 二级指针传入，实现一级指针传址，否则递归函数操作的只是拷贝
 * &与*互为逆运算，&取址，*取值
 */
BOOL deleteBST(BiTreePtr *bst, ElementType key){
	if(*bst == NULL) {					// 递归出口：未找到key对应结点
		return FALSE;
	} else {
		if((*bst)->element == key) {	// 递归出口：找到key对应结点
			deleteNode(bst);
			return TRUE;
		} else if(key > (*bst)->element) {
			return deleteBST( &(*bst)->rchild, key );
		} else {
			return deleteBST( &(*bst)->lchild, key );
		}
	}
}

// 判断结点是否有左孩子
BOOL hasLChild(BiTreePtr x) {
	return x->lchild != NULL;
}

// 判断结点是否有右孩子
BOOL hasRChild(BiTreePtr x) {
	return x->rchild != NULL;
}

// 访问结点元素
void visit(BiTreePtr x) {
	if(x) {
		printf("%d ", x->element);
	}
}

/************** 先序遍历，递归&两种迭代方法实现 ***************/
// 判断是否空栈
BOOL isEmpty(StackPtr pStack) {
	return pStack->next == NULL;
}

// 进栈
void push(StackPtr pStack, BiTreePtr element) {
	StackPtr pNew = (StackPtr)malloc(sizeof(StackNode));
	pNew->element = element;
	if(isEmpty(pStack)) {	// 空栈push
		pStack->next = pNew;
		pNew->next = NULL;
	} else {				// pStack->next始终指向栈顶
		pNew->next = pStack->next;
		pStack->next = pNew;
	}
}

// 出栈
BiTreePtr pop(StackPtr pStack) {
	if(!isEmpty(pStack)) {
		StackPtr pTop = pStack->next;
		pStack->next = pTop->next;
		return pTop->element;
	} else {
		return NULL;	// 空栈返回NULL
	}
}

// 获取栈顶元素
BiTreePtr top(StackPtr pStack) {
	return pStack->next->element;
}





/* 先序迭代遍历二叉树-1
 * 借助栈
 * 核心思想：根结点入栈，根据栈FILO的特性，先遍历的左孩子后入先出，后遍历的右孩子先入后出
 */
void travPreOrderIter_1(BiTreePtr bst) {
	StackPtr pStack = (StackPtr)malloc(sizeof(StackNode));
	pStack->next = NULL;
	push(pStack, bst);	// 根结点入栈
	while(!isEmpty(pStack))
	{
		BiTreePtr x = pop(pStack);
		visit(x);
		if(hasRChild(x))	push(pStack, x->rchild);	// 右孩子先入后出
		if(hasLChild(x))	push(pStack, x->lchild);	// 左孩子后入先出
	}
}

// 访问子树x的左侧链，自上而下将右子树入栈
void visitLeftBranch(StackPtr pStack, BiTreePtr x) {
	while(x)	// 迭代左侧链
	{
		visit(x);
		if(x->rchild)	// 右子树自上而下入栈（利用栈FILO特性，将来自下而上出栈）
			push(pStack, x->rchild);
		x = x->lchild;	// 沿左侧链下行
	}
}

/* 先序迭代遍历二叉树-2
 * 时间复杂度O(n)
 * 核心思想：先自上而下访问左侧链上的节点，将每个左侧链节点的右孩子入栈，再自下而上访问它们的右子树
 */
void travPreOrderIter_2(BiTreePtr bst) {
	StackPtr pStack = (StackPtr)malloc(sizeof(StackNode));
	pStack->next = NULL;
	BiTreePtr x = bst;
	while(x != NULL)	// 空栈退出
	{
		visitLeftBranch(pStack, x);	// 访问子树x的左侧链，自上而下将右子树入栈
		x = pop(pStack);			// 弹出下一个右子树的根，自下而上出栈
	}
}

/*
 * 先序递归遍历二叉树，自下而上递归
 */
void travPreOrder(BiTreePtr x) {
	if(x == NULL) {	// 处理递归基
		//visit(x);
		return;
	} else {
		visit(x);
		travPreOrder(x->lchild);	// 递归遍历左子树
		travPreOrder(x->rchild);	// 递归遍历右子树
	}
}
/********************* 中序遍历 递归&迭代实现 *****************************/

// 沿左侧链下沿，逐个入栈
void goAlongLeftBranch(BiTreePtr x, StackPtr pStack) {
	while(x)
	{
		push(pStack, x);
		x = x->lchild;
	}
}

/* 中序迭代遍历二叉树
 * 时间复杂度O(n)
 * 核心思想：沿着左侧链下行，自下而上访问左侧链结点，并遍历对应的右子树
 */
void travInOrderIter(BiTreePtr bst) {
	StackPtr pStack = (StackPtr)malloc(sizeof(StackNode));
	pStack->next = NULL;
	BiTreePtr x = bst;
	while(TRUE)
	{
		goAlongLeftBranch(x, pStack);	// 从当前结点出发，沿着左侧链下沿，逐个入栈，直到子树最左结点
		if(isEmpty(pStack)) break;		// 所有结点遍历完毕
		x = pop(pStack);				// 沿着左侧链自下而上访问结点
		visit(x);						// 立即访问
		x = x->rchild;					// 转向其右子树
	}
}

/* 中序递归遍历二叉查找树
 * 时间复杂度O(n)
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

/********************* 后序遍历 递归&迭代实现 *****************************/
/* 在以pStack栈顶结点为根的子树中，获取最高左侧叶结点（Highest Left Leaf）,
 * 自顶向下查找HLL结点时，通过栈逆序保存结点，始终尽可能向左，仅在左子树为空时才向右
 * HLL可能是其父结点的左孩子或者右孩子
 */
void gotoHLL(StackPtr pStack) {
	BiTreePtr x= top(pStack);
	while(x->lchild || x->rchild)	// 定位到HLL跳出循环
	{
		if(hasLChild(x)) {
			if(hasRChild(x)) {		// 后序遍历，右结点先入栈，FILO
				push(pStack, x->rchild);
			}
			push(pStack, x->lchild);// 后序遍历，左结点后入栈，LIFO
		} else {	// 始终尽可能向左，仅在左子树为空时才向右
			push(pStack, x->rchild);
		}
		x = top(pStack);			// 栈顶元素为HLL
	}
}

/* 后序迭代遍历二叉查找树
 * 时间复杂度O(n)
 * 核心思想：获取HLL——后序遍历最先访问的结点
 */
void travPostOrderIter(BiTreePtr bst) {
	StackPtr pStack = (StackPtr)malloc(sizeof(StackNode));
	pStack->next = NULL;
	BiTreePtr x = bst;
	push(pStack, x);
	while(!isEmpty(pStack))
	{
		if(top(pStack) != x->parent) { // 若当前结点x的右兄弟存在（该兄弟为栈顶元素）
			gotoHLL(pStack); // 调用gotoHLL()转向以该兄弟为根的子树 ,定位HLL
		}
		x = pop(pStack);	// 栈顶元素为HLL
		visit(x);
	}
}


/* 后序递归遍历二叉查找树
 * 时间复杂度O(n)
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

/********************* 层次遍历  迭代实现*****************************/
// 判断队列是否为空
BOOL isQueueEmpty(QueuePtr Queue) {
	return Queue->front == Queue->rear;
}

// 入队
void enqueue(QueuePtr Queue, BiTreePtr element) {
	QueueNodePtr pNew = (QueueNodePtr)malloc(sizeof(QueueNode));
	pNew->next = NULL;					// 结点pNew为rear->next申请空间
	Queue->rear->next = pNew;
	Queue->rear->element = element;		// 元素加入队尾
	Queue->rear = Queue->rear->next;	// rear始终维护队尾
}

// 出队
BiTreePtr dequeue(QueuePtr Queue) {
	QueueNodePtr p = Queue->front;
	Queue->front = Queue->front->next;
	return p->element;
}

/* 层次遍历二叉查找树
 * 时间复杂度O(n)
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
		if(hasLChild(x)) enqueue(Queue, x->lchild); // 层次遍历，左孩子&子树根结点 FIFO
		if(hasRChild(x)) enqueue(Queue, x->rchild);
	}
}


/********************* 主程序  *****************************/
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);

	BiTreePtr bst = NULL;
	int num[] = {62, 88, 58, 47, 35, 73, 51, 99, 37, 93};
	int i;
	for(i=0; i<10; i++) {
		insertBST(&bst, num[i]);
	}
	/* 先序遍历 */
	printf("Preorder Traverse:");
	printf("\nRecursive Implement  : ");
	travPreOrder(bst);
	printf("\nIterative Implement 1: ");
	travPreOrderIter_1(bst);
	printf("\nIterative Implement 2: ");
	travPreOrderIter_2(bst);
	/* 中序遍历 */
	printf("\n\nInorder Traverse:");
	printf("\nRecursive Implement  : ");
	travInOrder(bst);
	printf("\nIterative Implement  : ");
	travInOrderIter(bst);
	/* 后序遍历 */
	printf("\n\nPostorder Traverse:");
	printf("\nRecursive Implement  : ");
	travPostOrder(bst);
	printf("\nIterative Implement  : ");
	travPostOrderIter(bst);
	/* 层序遍历 */
	printf("\n\nLevelorder Traverse:");
	printf("\nIterative Implement  : ");
	travLevelOrderIter(bst);
	/*deleteBST(&bst, 47);
	printf("\n");
	travInOrder(bst);*/

	return 0;
}
