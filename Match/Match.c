/*
 * File name:Match.c
 * Description: 字符串ADT，蛮力匹配算法，KMP算法
 * Author: yzwall
 * Data: 2016/11/9
 */
#include <stdio.h>
#include <time.h>
#include <string.h>
#define TRUE 1
#define FALSE 0
typedef int BOOL;
typedef char *CharPtr; // c语言将字符串按照字符数组处理

//  获得s[lo]~s[hi]子串
CharPtr subString(CharPtr txt, int lo, int hi) {
	int len = strlen(txt);
	if(lo < 0)	lo = 0;
	if(hi > len-1) hi = len-1;
	CharPtr sub = (CharPtr)malloc(sizeof(char)*(hi-lo+2));
	int i, k;
	for(i=lo, k=0;  i<hi+1; sub[k++] = txt[i++]);
	sub[k] = '\0';
	return sub;
}

/*
 * 蛮力匹配:在字符串txt中查找串pat，返回第一次匹配位置，否则返回txt长度
 * 最坏时间复杂度：O(N*M)，最好时间复杂度为O(M)
 */
int search(CharPtr txt, CharPtr pat) {
	int i, k;
	int N = strlen(txt);
	int M = strlen(pat);
	for(i=0; i<=N-M; i++) {
		for(k=0; k<M; k++)					// txt[i+k]与pat[k]对齐
			if(txt[i+k] != pat[k]) break;	// 字符不匹配，pat向右移动一个字符，重新匹配
		if(k == M) return i;				// 发现首个匹配，返回匹配位置
	}
	return N;	// 未成功匹配，返回txt长度
}





/********************** 主程序  **********************/
int main() {
	CharPtr txt = {"abcdefgbecde"};
	CharPtr pat = subString(txt, 5, 20);
	printf("txt: %s\n", txt);
	printf("pat: %s\n", pat);
	printf("%d\n", search(txt, pat));

	return 0;
}
