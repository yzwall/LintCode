/*
 * File name:GraphByMatrix.c
 * Description: 图的邻接矩阵，图的ADT实现，广度&深度优先遍历
 * Author: yzwall
 * Data: 2016/10/17 17:54
 */
#include "GraphByMatrix.h"
#include <stdio.h>

/********************** 主程序 **********************/
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);

	/* 图的邻接矩阵实现 */
	MGraphPtr *graph;
	BOOL *visit;
	initMGraph(&graph, &visit);	// 初始化图
	resetVisit(graph, visit);	// 复位visit数组
	createMGraph(graph);		// 创建无向图&有向图
	BFSTrav(graph, visit);		// 无向图&有向图广度优先遍历
	resetVisit(graph, visit);	// 复位visit数组
	DFSTrav(graph, visit);		// 无向图&有向图深度优先遍历

	return 0;
}
