/*
 * File name:Match.c
 * Description: �ַ���ADT������ƥ���㷨��KMP�㷨
 * Author: yzwall
 * Data: 2016/11/9
 */
#include <stdio.h>
#include <time.h>
#include <string.h>
#define TRUE 1
#define FALSE 0
typedef int BOOL;
typedef char *CharPtr; // c���Խ��ַ��������ַ����鴦��

//  ���s[lo]~s[hi]�Ӵ�
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
 * ����ƥ��:���ַ���txt�в��Ҵ�pat�����ص�һ��ƥ��λ�ã����򷵻�txt����
 * �ʱ�临�Ӷȣ�O(N*M)�����ʱ�临�Ӷ�ΪO(M)
 */
int search(CharPtr txt, CharPtr pat) {
	int i, k;
	int N = strlen(txt);
	int M = strlen(pat);
	for(i=0; i<=N-M; i++) {
		for(k=0; k<M; k++)					// txt[i+k]��pat[k]����
			if(txt[i+k] != pat[k]) break;	// �ַ���ƥ�䣬pat�����ƶ�һ���ַ�������ƥ��
		if(k == M) return i;				// �����׸�ƥ�䣬����ƥ��λ��
	}
	return N;	// δ�ɹ�ƥ�䣬����txt����
}





/********************** ������  **********************/
int main() {
	CharPtr txt = {"abcdefgbecde"};
	CharPtr pat = subString(txt, 5, 20);
	printf("txt: %s\n", txt);
	printf("pat: %s\n", pat);
	printf("%d\n", search(txt, pat));

	return 0;
}
