/*
 * File name:BinaryTreeByList.c
 * Description: 前序序列创建二叉树，前序/中序/后序递归遍历二叉树
 * Author: yzwall
 * Data: 2016/9/19 20:12
 */
#include <stdio.h>
#include <stdlib.h>
//////////////////////////////////////////////////////////
typedef int ElementType;
// 二叉树结点结构
typedef struct BiTreeNode {
	ElementType element;
	struct BiTreeNode *lchild; // 左孩子结点指针
	struct BiTreeNode *rchild; // 右孩子结点指针
} BiTreeNode;
//////////////////////////////////////////////////////////
// 前序递归遍历preorder recursive traversal
void traversalPreOrder(BiTreeNode *bt) {
	if(bt == NULL) {
		printf("#\n");
		return;	// 递归无返回值，可通过return;返回上次递归位置
	} else {
		printf("%d\n", bt->element);	// 遍历根结点
		traversalPreOrder(bt->lchild);	// 遍历左子树
		traversalPreOrder(bt->rchild);	// 遍历右子树
	}
}

// 中序递归遍历inorder recursive traversal
void traversalInOrder(BiTreeNode *bt) {
	if(bt == NULL) {
		printf("#\n");
		return;
	} else {
		traversalInOrder(bt->lchild);	// 遍历左子树
		printf("%d\n", bt->element);	// 遍历根结点
		traversalInOrder(bt->rchild);	// 遍历右子树
	}
}

// 后序递归遍历postorder recursive traversal
void traversalPostOrder(BiTreeNode *bt) {
	if(bt == NULL) {
		printf("#\n");
		return;
	} else {
		traversalPostOrder(bt->lchild);	// 遍历左子树
		traversalPostOrder(bt->rchild);	// 遍历右子树
		printf("%d\n", bt->element);	// 遍历根结点
	}
}

/*
 * 前序输入二叉树结点值（单字符输入，#表示空结点）根据前序输入构造二叉链表表示二叉树
 * note: 将结点指针作为返回值，直接将结点指针作为参数传入，传值传递了bt的一个备份，开辟新结点时malloc会赋值给备份
 * note: 二级指针可解决该问题，代码可读性差
 */
BiTreeNode *createBiTreepPreOrder() {
	BiTreeNode *bt;
	ElementType element;

	scanf("%d", &element);
	if(element == -1) {
		bt = NULL;	// 空结点
		//return;	// 递归已有出口return bt, return;会使得空结点值赋值null值失效；
	} else {
		bt = (BiTreeNode *)malloc(sizeof(BiTreeNode));
		if(bt == NULL) {
			exit(-1);
			printf("BiTreeNode memory allocate failed.\n");
		} else {
			bt->element = element;					// 构造根结点
			bt->lchild = createBiTreepPreOrder();	// 构造左子树
			bt->rchild = createBiTreepPreOrder();	// 构造右子树
		}
	}
	return bt;
}


//////////////////////////////////////////////////////////
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);

	BiTreeNode *bt;
	bt = createBiTreepPreOrder();			// 根据前序输入构造二叉树
	printf("\n前序遍历：\n");
	traversalPreOrder(bt);					// 前序递归遍历二叉树
	printf("\n中序遍历：\n");
	traversalInOrder(bt);					// 前序递归遍历二叉树
	printf("\n后序遍历：\n");
	traversalPostOrder(bt);					// 前序递归遍历二叉树

	return 0;
}
