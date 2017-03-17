/*
 * File name:GraphByMatrix.c
 * Description: ͼ���ڽӾ���ͼ��ADTʵ�֣����&������ȱ���
 * Author: yzwall
 * Data: 2016/10/17 17:54
 */
#include "GraphByMatrix.h"
#include <stdio.h>

/********************** ������ **********************/
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);

	/* ͼ���ڽӾ���ʵ�� */
	MGraphPtr *graph;
	BOOL *visit;
	initMGraph(&graph, &visit);	// ��ʼ��ͼ
	resetVisit(graph, visit);	// ��λvisit����
	createMGraph(graph);		// ��������ͼ&����ͼ
	BFSTrav(graph, visit);		// ����ͼ&����ͼ������ȱ���
	resetVisit(graph, visit);	// ��λvisit����
	DFSTrav(graph, visit);		// ����ͼ&����ͼ������ȱ���

	return 0;
}
