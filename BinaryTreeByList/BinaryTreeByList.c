/*
 * File name:BinaryTreeByList.c
 * Description: ǰ�����д�����������ǰ��/����/����ݹ����������
 * Author: yzwall
 * Data: 2016/9/19 20:12
 */
#include <stdio.h>
#include <stdlib.h>
//////////////////////////////////////////////////////////
typedef int ElementType;
// ���������ṹ
typedef struct BiTreeNode {
	ElementType element;
	struct BiTreeNode *lchild; // ���ӽ��ָ��
	struct BiTreeNode *rchild; // �Һ��ӽ��ָ��
} BiTreeNode;
//////////////////////////////////////////////////////////
// ǰ��ݹ����preorder recursive traversal
void traversalPreOrder(BiTreeNode *bt) {
	if(bt == NULL) {
		printf("#\n");
		return;	// �ݹ��޷���ֵ����ͨ��return;�����ϴεݹ�λ��
	} else {
		printf("%d\n", bt->element);	// ���������
		traversalPreOrder(bt->lchild);	// ����������
		traversalPreOrder(bt->rchild);	// ����������
	}
}

// ����ݹ����inorder recursive traversal
void traversalInOrder(BiTreeNode *bt) {
	if(bt == NULL) {
		printf("#\n");
		return;
	} else {
		traversalInOrder(bt->lchild);	// ����������
		printf("%d\n", bt->element);	// ���������
		traversalInOrder(bt->rchild);	// ����������
	}
}

// ����ݹ����postorder recursive traversal
void traversalPostOrder(BiTreeNode *bt) {
	if(bt == NULL) {
		printf("#\n");
		return;
	} else {
		traversalPostOrder(bt->lchild);	// ����������
		traversalPostOrder(bt->rchild);	// ����������
		printf("%d\n", bt->element);	// ���������
	}
}

/*
 * ǰ��������������ֵ�����ַ����룬#��ʾ�ս�㣩����ǰ�����빹����������ʾ������
 * note: �����ָ����Ϊ����ֵ��ֱ�ӽ����ָ����Ϊ�������룬��ֵ������bt��һ�����ݣ������½��ʱmalloc�ḳֵ������
 * note: ����ָ��ɽ�������⣬����ɶ��Բ�
 */
BiTreeNode *createBiTreepPreOrder() {
	BiTreeNode *bt;
	ElementType element;

	scanf("%d", &element);
	if(element == -1) {
		bt = NULL;	// �ս��
		//return;	// �ݹ����г���return bt, return;��ʹ�ÿս��ֵ��ֵnullֵʧЧ��
	} else {
		bt = (BiTreeNode *)malloc(sizeof(BiTreeNode));
		if(bt == NULL) {
			exit(-1);
			printf("BiTreeNode memory allocate failed.\n");
		} else {
			bt->element = element;					// ��������
			bt->lchild = createBiTreepPreOrder();	// ����������
			bt->rchild = createBiTreepPreOrder();	// ����������
		}
	}
	return bt;
}


//////////////////////////////////////////////////////////
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);

	BiTreeNode *bt;
	bt = createBiTreepPreOrder();			// ����ǰ�����빹�������
	printf("\nǰ�������\n");
	traversalPreOrder(bt);					// ǰ��ݹ����������
	printf("\n���������\n");
	traversalInOrder(bt);					// ǰ��ݹ����������
	printf("\n���������\n");
	traversalPostOrder(bt);					// ǰ��ݹ����������

	return 0;
}
