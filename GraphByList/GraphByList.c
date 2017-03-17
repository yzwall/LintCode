/*
 * File name:GraphByList.c
 * Description: ͼ���ڽӱ�ͼ��ADTʵ�֣����&������ȱ���
 * Author: yzwall
 * Data: 2016/10/16
 */
#include "GraphByList.h"
#include <stdio.h>
#include <stdlib.h>

/************************* ������ *************************/
int main() {
	setvbuf(stdout, NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);

	LGraphPtr graph;
	BOOL *visit;
	createLGraph(&graph, &visit, UNDIRECT);
	resetVisit(graph, visit);
	BFSTrav(graph, visit);
	resetVisit(graph, visit);
	DFSTrav(graph, visit);

	return 0;
}
