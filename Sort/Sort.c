/*
 * File name:Sort.c
 * Description: ѡ��/ֱ�Ӳ���/ð��/ϣ��/�鲢/��������/��������/Ͱ����/���������㷨
 * Author: yzwall
 * Data: 2016/11/3 21:53
 */
#include <stdio.h>
#include <time.h>
#define TRUE 1
#define FALSE 0
#define ARRAY_LENGTH 20
#define RADIX_BUCKET_SIZE 10
#define MIN_VALUE 1
#define MAX_VALUE 100
typedef int EleType;
typedef int BOOL;

void initial(EleType **arr) {
	*arr = (EleType *)malloc(ARRAY_LENGTH * sizeof(EleType));
}

/*
 * ������ɴ������У�������������
 * �������г���Ϊ10��ֵ����[1,100]
 */
void random(EleType *arr) {
	int i, t;
	BOOL flag[MAX_VALUE+1];
	srand((unsigned)time(NULL));
	for(i=1; i<=ARRAY_LENGTH; i++) flag[i] = FALSE;
	i = 0;
	while(TRUE)
	{
		t = rand()%MAX_VALUE+1;
		if(!flag[t]) {
			arr[i++] = t ;
			if(i == ARRAY_LENGTH) break;
			flag[t] = TRUE;
		}
	}
	printf("\nAfter shuffle:\n");
	traverse(arr);
}

void destroy(EleType *arr) {
	free(arr);
}

// ��������
void traverse(EleType *arr) {
	int i;
	for(i=0; i<ARRAY_LENGTH; i++)
		printf("%d ", arr[i]);
	printf("\n");
}

void swap(EleType *a, EleType *b) {
	EleType temp;
	temp = *b;
	*b = *a;
	*a = temp;
}

// Ĭ����������
BOOL isLess(EleType a, EleType b) {
	return a <= b;
}

/************************ ð������ ************************/
/*
 * ð������ÿ�ν�a[0]~a[n--]�����ֵ�������ֱ�����������г���Ϊ0
 * ʱ�临�Ӷ�O(n2)
 * �ռ临�Ӷ�O(1)
 */
void bubbleSort(EleType *arr) {
	int n = ARRAY_LENGTH, j;
	do {
		for(j=1; j<n; j++) {
			if(!isLess(arr[j-1], arr[j])) // a[j-1]ά��Ҫ�����������Ԫ��
				swap(&arr[j-1], &arr[j]);
		}
	} while(n--);	// ����������Ϊa[0]~a[n-1],ֱ��n=0
	printf("After bubble sort:\n");
	traverse(arr);
}

/************************ ѡ������ ************************/
/*
 * ѡ������Ĭ������)��ÿ��ѡȡ�������е���СԪ�ص��������е�β��
 * ʱ�临�Ӷ�O(n2)
 * �ռ临�Ӷ�O(1)
 */
void selectSort(EleType *arr) {
	int i, j, minIndex;
	for(i=0; i<ARRAY_LENGTH; i++) {
		minIndex = i;	// minIndex: arr[i+1]~a[N]����СԪ������
		for(j=i+1; j<ARRAY_LENGTH; j++) {
			if(isLess(arr[j], arr[minIndex]))
				minIndex = j;
		}
		swap(&arr[i], &arr[minIndex]);	// ����ѡ��ʣ��Ԫ�ص���С��
	}
	printf("After selection sort:\n");
	traverse(arr);
}

/************************ ֱ�Ӳ������� ************************/
/*
 * ֱ�Ӳ�������ʵ��һ����a[i]��a[0]~a[i-1]�б������Ԫ�أ����ν���λ�ã�a[i]����Ԫ�ض��������У�Ĭ������
 * ʱ�临�Ӷ�O(n)~O(n2)��ȡ�����������
 * �ռ临�Ӷ�O(1)
 */
void insertSort_1(EleType *arr) {
	int i, j;
	for(i=1; i<ARRAY_LENGTH; i++) {	// ��a[i]��a[0]~a[i-1]�б������Ԫ�أ����ν���λ�ã�a[i]����Ԫ�ض���������
		for(j=i; j>0; j--)
			if(isLess(arr[j], arr[j-1])) // ���arr[i]һֱ������˳�򣬽�һֱά��arr[j]����������Ԫ��arr[j-1]���ν���λ��
				swap(&arr[j], &arr[j-1]);
	}
	printf("After insertion sort:\n");
	traverse(arr);
}

/*
 * ֱ�Ӳ�������ʵ�ֶ����ҵ�a[i]��a[0]~a[i-1]�еĲ���λ��k�������ƶ�a[k]��߽ϴ������λ��
 * ʱ�临�Ӷ�O(n)~O(n2)��ȡ�����������
 * �ռ临�Ӷ�O(1)
 */
void insertSort_2(EleType *arr) {
	int i, j;
	for(i=1; i<ARRAY_LENGTH; i++) {
		if(isLess(arr[i], arr[i-1])) {
			int temp = arr[i];
			for(j=i-1; j>=0 && isLess(temp, arr[j]); j--)
				arr[j+1] = arr[j];  // �����ƶ���a[i]�������λ��
			arr[j+1] = temp;
		}
	}
	printf("After insertion sort:\n");
	traverse(arr);
}

/************************ ϣ������ ************************/
// �������򣺽�����arr���h����
void shellInsertSort(EleType *arr, int h) {
	int i, j;
	for(i=h; i<ARRAY_LENGTH; i++) { // �����е�h��Ԫ�ؿ�ʼ
		for(j=i; j>=h && isLess(arr[j], arr[j-h]); j-=h) {	// ÿ��Ԫ�����Լ����ڵ����ݽ���ֱ�Ӳ�����������Ԫ�ز���Ϊh
			swap(&arr[j], &arr[j-h]);
		}
	}
}

/*
 * ϣ������
 */
void shellSort(EleType *arr) {
	int N = ARRAY_LENGTH;
	int h = 1;
	while(h < N/3)	h = 3*h+1;	// ��������H = {1, 4, 13, 40, 121, ...}
	while(h >= 1)
	{
		shellInsertSort(arr, h); // �����б��h-sorted
		h = h/3;
	}
	printf("After shell sort:\n");
	traverse(arr);
}

/************************ �������� ************************/
/*
 * ��������-����з�
 * ���һ���з֣���֤L <= pivot <= G
 */
int partition(EleType *arr, int lo, int hi) {
	EleType pivot = arr[lo];
	while(lo < hi)	// ����U����hi-lo����Сֱ��Ϊ0����ʱarr[lo] = pivot
	{
		while( (lo<hi) && (pivot<=arr[hi]) )	hi--;
		// ��ʱarr[hi]�ϸ�С�ں�ѡ��㣬��arr[hi]����������L�У���ʱarr[hi]����
		// ��֤����Ҳ�Ԫ���ϸ�С�����
		swap(&arr[hi], &arr[lo]);

		while( (lo<hi) && (pivot>=arr[lo]) )	lo++;
		// ��ʱarr[lo]�ϸ���ں�ѡ��㣬��arr[lo]����������G�У���ʱarr[lo]����
		// ��֤������Ԫ���ϸ񲻴������
		swap(&arr[lo], &arr[hi]);
	}
	return lo;	// ���һ���з֣���֤L <= pivot <= G
}

/*
 * ��������-�����汾
 */
void quickSort(EleType *arr, int lo, int hi) {
	if(lo >= hi)	return;					// �ݹ������lo=hiʱ���������
	int pivotIndex = partition(arr, lo, hi);// ���һ���з֣���֤L <= pivot <= G
	quickSort(arr, lo, pivotIndex);			// ��L����arr[lo]~pivot����
	quickSort(arr, pivotIndex+1, hi);		// ��G����pivot+1~arr[hi]����
}

/************************ �鲢���� ************************/
/*
 * ��·�鲢:����������arr[lo, mi]��arr[mi+1, hi]�鲢Ϊ��������arr[lo, hi]
 * ʱ�临�Ӷȣ�O(n)
 */
void twoWayMerge(EleType *arr, int lo, int mi, int hi) {
	int i, j, k;
	int len1 = mi-lo+1;
	int len2 = hi-mi;
	EleType s1[len1];
	EleType s2[len2];
	for(i=0; i<len1; i++)
		s1[i] = arr[lo+i];
	for(j=0; j<=len2; j++)
		s2[j] = arr[mi+1+j];
//	i = 0;
//	j = 0;
//	k = lo;
//	while(i<len1 && j<len2)
//	{
//		if(isLess(s1[i], s2[j]))	arr[k++] = s1[i++];
//		else	arr[k++] = s2[j++];
//	}
//	while(i<len1)
//	{
//		arr[k++] = s1[i++];
//	}
//	while(j<len2)
//	{
//		arr[k++] = s2[j++];
//	}
	/*
	 * �±�forѭ������Ϊ�鲢���뾫���
	 * !(i<len1)��ʾarr[lo, mid]��Ԫ����ȫ���鲢
	 * !(j<len2)��ʾarr[mid+1, hi]��Ԫ����ȫ���鲢
	 * ����һ��������ȫ���鲢������һ����δ�鲢Ԫ�����μ���鲢����β��
	 */
	for(i=0, j=0, k=lo; i<len1 || j<len2;) {
		if(i<len1 && (!(j<len2) || (isLess(s1[i], s2[j]))))	arr[k++] = s1[i++];
		if(j<len2 && (!(i<len1) || (isLess(s2[j], s1[i]))))	arr[k++] = s2[j++];
	}
}

/*
 * �鲢����
 * ʱ�临�Ӷ�:O(nlogn)
 * �ռ临�Ӷ�:O(n+logn)
 */
void mergeSort(EleType *arr, int lo, int hi) {
	if(lo >= hi)	return;			// ��Ԫ�ر�Ȼ���򣬵ݹ��
	int mi = (lo+hi) / 2;			// ��arr[lo, mi]�ֽ�Ϊarr[lo, mi]��arr[mi+1, hi]
	mergeSort(arr, lo, mi);			// arr[lo, mi]�鲢����Ϊ��������
	mergeSort(arr, mi+1, hi);		// arr[mi+1, hi]�鲢����תΪ��������
	twoWayMerge(arr, lo, mi, hi);	// ��·�鲢
}

/************************ �������� ************************/
/*
 * �����������������ֵ��Χ��[MIN, MAX]�ڣ����ǱȽ�����
 * ʱ�临�Ӷȣ�O(MAX-MIN+N)
 * �ռ临�Ӷȣ��ϸ�
 */
EleType *countSort(EleType *arr) {
	EleType count[MAX_VALUE+1], sort[ARRAY_LENGTH];
	int i, k;
	for(i=MIN_VALUE; i<=MAX_VALUE; i++) { // �������г�ʼ��
		count[i] = 0;
	}
	for(i=0; i<ARRAY_LENGTH; i++)	count[arr[i]]++; // ����ͳ��
	for(i=MIN_VALUE; i<=MAX_VALUE; i++) // �����ۼƼ���������
		count[i+1] += count[i];
//	for(i=MAX_VALUE; i>MIN_VALUE; i--) // �����ۼƼ���������
//		count[i-1] += count[i];
	for(k=0; k<ARRAY_LENGTH; k++)	// �����ۼƼ���������
		sort[count[arr[k]]-1] = arr[k];
	destroy(arr);
	printf("After count sort:\n");
	return sort;
}

/************************ Ͱ���� ************************/
// Ͱ-����
typedef struct Bucket {
	EleType element;
	BOOL isEmpty;
	struct Bucket *next;
} Bucket, *BucketPtr;

/*
 * ��Ԫ�ز���Ͱ��Ԫ
 * Ͱ��Ԫ���ΪM�����븴�Ӷ�ΪO(M)
 */
void insBucket(BucketPtr bc, EleType element) {
	if(!bc->isEmpty) {
		BucketPtr pIter = bc;
		while(pIter->next)	pIter = pIter->next;
		BucketPtr pNew = (BucketPtr)malloc(sizeof(Bucket));
		pNew->element = element;
		pNew->next = NULL;
		pIter->next = pNew;
	} else {
		bc->element = element;
		bc->isEmpty = FALSE;
	}
}

// ��ϣ����(���������ص��Զ���)
int hash(EleType key, int size) {
	return key/size;
}

// Ͱ����
void bucketSort(EleType *arr) {
	Bucket bucket[ARRAY_LENGTH]; // �����СΪARRAY_LENGTH��Ͱ
	int i;
	for(i=0; i<ARRAY_LENGTH; i++) {
		bucket[i].isEmpty = TRUE;
		bucket[i].next = NULL;
	}
	for(i=0; i<ARRAY_LENGTH; i++) {
		int index = hash(arr[i], ARRAY_LENGTH);
		insBucket(&bucket[index], arr[i]);
	}
	for(i=0; i<ARRAY_LENGTH; i++) {
		if(!bucket[i].isEmpty) { // �Էǿ�Ͱ��Ԫ��������
			// ��bucket[i]��������  sort(bucket[i])
			if(bucket[i].next) {
				BucketPtr pIter = &bucket[i];
				while(pIter->)

			}




			// ����bucket[i]��arr
			BucketPtr pIter = &bucket[i];
			int k=0;
			while(pIter)
			{
				arr[i++] = pIter->element;
				pIter = pIter->next;
			}

		}
	}
}

/************************ �������� ************************/

// �����������ֵ��λ��Ϊ����ѭ������
int getLoopTime(EleType *arr) {
	EleType max = MIN_VALUE;
	int i;
	for(i=0; i<ARRAY_LENGTH; i++) {
		if(isLess(max, arr[i]))
			max = arr[i];
	}
	i=0;
	while(max)
	{
		max /= 10;
		i++;
	}
	return i;
}

// ��arr[i]�������λlsd�����䵽Ͱbucket[key]�У����һ������
void distribute(EleType *arr, BucketPtr bucket, EleType element, int lsd) {
	int i, key, temp = element;
	for(i=0; i<lsd; i++) {
		temp /= 10;
	}
	key = temp%10;	// keyΪelement��lsdλ
	insBucket(&bucket[key], element);
}

// ��Ͱ��Ԫ�غϲ�������������
void cpBucket(EleType *arr, BucketPtr bucket) {
	int i, k=0;
	for(i=0; i<RADIX_BUCKET_SIZE; i++) {
		if(!bucket[i].isEmpty) {
			BucketPtr pIter = &bucket[i];
			while(pIter)
			{
				arr[k++] = pIter->element;
				pIter = pIter->next;
			}
			bucket[i].isEmpty = TRUE;
			bucket[i].next = NULL;
		}
 	}
}

/*
 * ���������ȴӹؼ���kd��ʼ�����ٶ�kd-1����ֱ��k1�������
 * LSD�����ؼ���˳��ӵ�λ���λ����λ/��λ/ǧλ/...��
 */
void radixSortLSD(EleType *arr) {
	Bucket bucket[RADIX_BUCKET_SIZE]; // 0~9
	int i, k;
	for(i=0; i<RADIX_BUCKET_SIZE; i++) {
		bucket[i].isEmpty = TRUE;
		bucket[i].next = NULL;
	}
	int loop = getLoopTime(arr); // �������ֵ��λ��Ϊ�������
	for(i=0; i<loop; i++) {
		for(k=0; k<ARRAY_LENGTH; k++)	// ���յ�λi��������
			distribute(arr, bucket, arr[k], i);
		cpBucket(arr, bucket);	// ÿ�������Ͱ�����ݿ�����ԭ����
	}
	printf("After radix sort:\n");
}


/************************ ������ ************************/
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);
	EleType *arr;
	initial(&arr);

//	random(arr);
//	selectSort(arr);
//
//	random(arr);
//	insertSort_1(arr);
//
//	random(arr);
//	insertSort_2(arr);
//
//	random(arr);
//	bubbleSort(arr);
//
//	random(arr);
//	shellSort(arr);
//
//	random(arr);
//	quickSort(arr, 0, ARRAY_LENGTH-1);
//	printf("After quick sort:\n");
//	traverse(arr);
//
//	random(arr);
//	mergeSort(arr, 0, ARRAY_LENGTH-1);
//	printf("After merge sort:\n");
//	traverse(arr);
//
//	random(arr);
//	arr = countSort(arr);
//	traverse(arr);

//	random(arr);
//	bucketSort(arr);
//	traverse(arr);
//
	random(arr);
	radixSortLSD(arr);
	traverse(arr);

	destroy(arr);
	return 0;
}
