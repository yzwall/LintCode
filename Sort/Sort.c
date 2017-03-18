/*
 * File name:Sort.c
 * Description: 选择/直接插入/冒泡/希尔/归并/快速排序/计数排序/桶排序/基数排序算法
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
 * 随机生成待排序列，消除输入依赖
 * 输入序列长度为10，值介于[1,100]
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

// 遍历序列
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

// 默认升序排序
BOOL isLess(EleType a, EleType b) {
	return a <= b;
}

/************************ 冒泡排序 ************************/
/*
 * 冒泡排序：每次将a[0]~a[n--]的最大值沉到最后，直到待排序序列长度为0
 * 时间复杂度O(n2)
 * 空间复杂度O(1)
 */
void bubbleSort(EleType *arr) {
	int n = ARRAY_LENGTH, j;
	do {
		for(j=1; j<n; j++) {
			if(!isLess(arr[j-1], arr[j])) // a[j-1]维护要沉到最后的最大元素
				swap(&arr[j-1], &arr[j]);
		}
	} while(n--);	// 待排序区间为a[0]~a[n-1],直到n=0
	printf("After bubble sort:\n");
	traverse(arr);
}

/************************ 选择排序 ************************/
/*
 * 选择排序（默认升序)：每次选取无序序列的最小元素到有序序列的尾部
 * 时间复杂度O(n2)
 * 空间复杂度O(1)
 */
void selectSort(EleType *arr) {
	int i, j, minIndex;
	for(i=0; i<ARRAY_LENGTH; i++) {
		minIndex = i;	// minIndex: arr[i+1]~a[N]中最小元素索引
		for(j=i+1; j<ARRAY_LENGTH; j++) {
			if(isLess(arr[j], arr[minIndex]))
				minIndex = j;
		}
		swap(&arr[i], &arr[minIndex]);	// 不断选择剩余元素的最小者
	}
	printf("After selection sort:\n");
	traverse(arr);
}

/************************ 直接插入排序 ************************/
/*
 * 直接插入排序实现一：将a[i]与a[0]~a[i-1]中比它大的元素，依次交换位置，a[i]左侧的元素都是有序列（默认升序）
 * 时间复杂度O(n)~O(n2)，取决于输入情况
 * 空间复杂度O(1)
 */
void insertSort_1(EleType *arr) {
	int i, j;
	for(i=1; i<ARRAY_LENGTH; i++) {	// 将a[i]与a[0]~a[i-1]中比它大的元素，依次交换位置，a[i]左侧的元素都是有序列
		for(j=i; j>0; j--)
			if(isLess(arr[j], arr[j-1])) // 如果arr[i]一直不满足顺序，将一直维护arr[j]，与比它大的元素arr[j-1]依次交换位置
				swap(&arr[j], &arr[j-1]);
	}
	printf("After insertion sort:\n");
	traverse(arr);
}

/*
 * 直接插入排序实现二：找到a[i]在a[0]~a[i-1]中的插入位置k，依次移动a[k]后边较大的数据位置
 * 时间复杂度O(n)~O(n2)，取决于输入情况
 * 空间复杂度O(1)
 */
void insertSort_2(EleType *arr) {
	int i, j;
	for(i=1; i<ARRAY_LENGTH; i++) {
		if(isLess(arr[i], arr[i-1])) {
			int temp = arr[i];
			for(j=i-1; j>=0 && isLess(temp, arr[j]); j--)
				arr[j+1] = arr[j];  // 依次移动比a[i]大的数据位置
			arr[j+1] = temp;
		}
	}
	printf("After insertion sort:\n");
	traverse(arr);
}

/************************ 希尔排序 ************************/
// 插入排序：将序列arr变成h有序
void shellInsertSort(EleType *arr, int h) {
	int i, j;
	for(i=h; i<ARRAY_LENGTH; i++) { // 从序列第h个元素开始
		for(j=i; j>=h && isLess(arr[j], arr[j-h]); j-=h) {	// 每个元素与自己组内的数据进行直接插入排序，组内元素步长为h
			swap(&arr[j], &arr[j-h]);
		}
	}
}

/*
 * 希尔排序
 */
void shellSort(EleType *arr) {
	int N = ARRAY_LENGTH;
	int h = 1;
	while(h < N/3)	h = 3*h+1;	// 步长序列H = {1, 4, 13, 40, 121, ...}
	while(h >= 1)
	{
		shellInsertSort(arr, h); // 将序列变成h-sorted
		h = h/3;
	}
	printf("After shell sort:\n");
	traverse(arr);
}

/************************ 快速排序 ************************/
/*
 * 快速排序-轴点切分
 * 完成一次切分，保证L <= pivot <= G
 */
int partition(EleType *arr, int lo, int hi) {
	EleType pivot = arr[lo];
	while(lo < hi)	// 序列U长度hi-lo逐渐缩小直至为0，此时arr[lo] = pivot
	{
		while( (lo<hi) && (pivot<=arr[hi]) )	hi--;
		// 此时arr[hi]严格小于候选轴点，将arr[hi]加入子序列L中，此时arr[hi]空闲
		// 保证轴点右侧元素严格不小于轴点
		swap(&arr[hi], &arr[lo]);

		while( (lo<hi) && (pivot>=arr[lo]) )	lo++;
		// 此时arr[lo]严格大于候选轴点，将arr[lo]加入子序列G中，此时arr[lo]空闲
		// 保证轴点左侧元素严格不大于轴点
		swap(&arr[lo], &arr[hi]);
	}
	return lo;	// 完成一次切分，保证L <= pivot <= G
}

/*
 * 快速排序-基本版本
 */
void quickSort(EleType *arr, int lo, int hi) {
	if(lo >= hi)	return;					// 递归基：当lo=hi时，完成排序
	int pivotIndex = partition(arr, lo, hi);// 完成一次切分，保证L <= pivot <= G
	quickSort(arr, lo, pivotIndex);			// 将L序列arr[lo]~pivot排序
	quickSort(arr, pivotIndex+1, hi);		// 将G序列pivot+1~arr[hi]排序
}

/************************ 归并排序 ************************/
/*
 * 二路归并:将有序序列arr[lo, mi]和arr[mi+1, hi]归并为有序序列arr[lo, hi]
 * 时间复杂度：O(n)
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
	 * 下边for循环代码为归并代码精简版
	 * !(i<len1)表示arr[lo, mid]中元素已全部归并
	 * !(j<len2)表示arr[mid+1, hi]中元素已全部归并
	 * 若有一方序列已全部归并，将另一序列未归并元素依次加入归并序列尾部
	 */
	for(i=0, j=0, k=lo; i<len1 || j<len2;) {
		if(i<len1 && (!(j<len2) || (isLess(s1[i], s2[j]))))	arr[k++] = s1[i++];
		if(j<len2 && (!(i<len1) || (isLess(s2[j], s1[i]))))	arr[k++] = s2[j++];
	}
}

/*
 * 归并排序
 * 时间复杂度:O(nlogn)
 * 空间复杂度:O(n+logn)
 */
void mergeSort(EleType *arr, int lo, int hi) {
	if(lo >= hi)	return;			// 单元素必然有序，递归基
	int mi = (lo+hi) / 2;			// 将arr[lo, mi]分解为arr[lo, mi]和arr[mi+1, hi]
	mergeSort(arr, lo, mi);			// arr[lo, mi]归并排序为有序序列
	mergeSort(arr, mi+1, hi);		// arr[mi+1, hi]归并排序转为有序序列
	twoWayMerge(arr, lo, mi, hi);	// 二路归并
}

/************************ 计数排序 ************************/
/*
 * 计数排序：排序对象数值范围在[MIN, MAX]内，不是比较排序
 * 时间复杂度：O(MAX-MIN+N)
 * 空间复杂度：较高
 */
EleType *countSort(EleType *arr) {
	EleType count[MAX_VALUE+1], sort[ARRAY_LENGTH];
	int i, k;
	for(i=MIN_VALUE; i<=MAX_VALUE; i++) { // 计数序列初始化
		count[i] = 0;
	}
	for(i=0; i<ARRAY_LENGTH; i++)	count[arr[i]]++; // 计数统计
	for(i=MIN_VALUE; i<=MAX_VALUE; i++) // 根据累计计数，升序
		count[i+1] += count[i];
//	for(i=MAX_VALUE; i>MIN_VALUE; i--) // 根据累计计数，降序
//		count[i-1] += count[i];
	for(k=0; k<ARRAY_LENGTH; k++)	// 根据累计计数，排序
		sort[count[arr[k]]-1] = arr[k];
	destroy(arr);
	printf("After count sort:\n");
	return sort;
}

/************************ 桶排序 ************************/
// 桶-链表
typedef struct Bucket {
	EleType element;
	BOOL isEmpty;
	struct Bucket *next;
} Bucket, *BucketPtr;

/*
 * 将元素插入桶单元
 * 桶单元深度为M，插入复杂度为O(M)
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

// 哈希函数(根据数据特点自定义)
int hash(EleType key, int size) {
	return key/size;
}

// 桶排序
void bucketSort(EleType *arr) {
	Bucket bucket[ARRAY_LENGTH]; // 定义大小为ARRAY_LENGTH的桶
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
		if(!bucket[i].isEmpty) { // 对非空桶单元进行排序
			// 对bucket[i]进行排序  sort(bucket[i])
			if(bucket[i].next) {
				BucketPtr pIter = &bucket[i];
				while(pIter->)

			}




			// 拷贝bucket[i]到arr
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

/************************ 基数排序 ************************/

// 待排序列最大值的位数为排序循环次数
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

// 将arr[i]按照最低位lsd，分配到桶bucket[key]中，完成一次排序
void distribute(EleType *arr, BucketPtr bucket, EleType element, int lsd) {
	int i, key, temp = element;
	for(i=0; i<lsd; i++) {
		temp /= 10;
	}
	key = temp%10;	// key为element的lsd位
	insBucket(&bucket[key], element);
}

// 将桶中元素合并到待排序列中
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
 * 基数排序：先从关键码kd开始排序，再对kd-1排序，直到k1排序完成
 * LSD法：关键码顺序从低位向高位（个位/百位/千位/...）
 */
void radixSortLSD(EleType *arr) {
	Bucket bucket[RADIX_BUCKET_SIZE]; // 0~9
	int i, k;
	for(i=0; i<RADIX_BUCKET_SIZE; i++) {
		bucket[i].isEmpty = TRUE;
		bucket[i].next = NULL;
	}
	int loop = getLoopTime(arr); // 序列最大值的位数为排序次数
	for(i=0; i<loop; i++) {
		for(k=0; k<ARRAY_LENGTH; k++)	// 按照低位i进行排序
			distribute(arr, bucket, arr[k], i);
		cpBucket(arr, bucket);	// 每次排序后将桶中数据拷贝到原序列
	}
	printf("After radix sort:\n");
}


/************************ 主程序 ************************/
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
