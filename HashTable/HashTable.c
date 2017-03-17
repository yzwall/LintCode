/*
 * File name:HashTable.c
 * Description: 散列表ADT,除余法散列函数，线性探测冲突
 * Author: yzwall
 * Data: 16/10/19 18:03
 */
#include <stdio.h>
#include <limits.h>
#define TRUE 1
#define FALSE 0
#define NOT_EXIST -1
#define NULL_KEY INT_MIN
#define HASH_SIZE 12
typedef int KeyType;
typedef int *KeyTypePtr;
typedef int BOOL;
/* 散列表结构 */
typedef struct HashTable {
	KeyType *table;
	int count; // 散列表中元素总数
	int M; 	   // 散列表长
} HashTable, *HashTablePtr;

// 初始化散列表
void initHashTable (HashTablePtr hashTable) {
//	hashTable = (HashTablePtr)malloc(sizeof(HashTable));
	hashTable->M = HASH_SIZE;
	hashTable->count = hashTable->M;
	hashTable->table = (KeyTypePtr)malloc((hashTable->M) * sizeof(KeyType));
	int i;
	for(i=0; i<hashTable->M; i++)	// 默认散列表中元素不存在
		hashTable->table[i] = NULL_KEY;
}

/*
 *  插入前探测table[hash(key)] == NULL_KEY表示探测到空桶，否则冲突
 *  查找时探测table[hash(key)] == key表示查找key成功，否则查找失败
 */
BOOL hasCollision(HashTablePtr hashTable, int proAddr, int keyElement) {
	// proAddr = hash(key), 表示散列地址
	return hashTable->table[proAddr] != keyElement;
}

// 散列函数：采用除余法
int Hash(HashTablePtr hashTable, KeyType key) {
	return key % hashTable->M;
}

// 向散列表插入记录, 线性试探是否冲突
void insHashTable(HashTablePtr hashTable, KeyType key) {
	int proAddr = Hash(hashTable, key);					// 计算散列地址
	while(hasCollision(hashTable, proAddr, NULL_KEY))	// 冲突
	{
		proAddr = (proAddr+1) % hashTable->M;	// 开放定址：线性探测
	}
	hashTable->table[proAddr] = key;	// 找到空桶，插入key
}

// 散列表创建完成后，查找key的散列地址
int searchHashTable(HashTablePtr hashTable, KeyType key) {
	int proAddr = Hash(hashTable, key);
	while(hasCollision(hashTable, proAddr, key))	// 不匹配
	{
		proAddr = (proAddr+1) % hashTable->M;
		/*
		 * 冲突发生时，有两种情况：
		 * key在散列表中：key在其他桶中，此时散列表中必然不存在空桶
		 * key不在散列表中：线性探测回到原点，散列表中存在空桶
		 */
		if(hashTable->table[proAddr] == NULL_KEY || proAddr == Hash(hashTable, key)) {
			return NOT_EXIST;	// 查找失败
		}
	}
	return proAddr;	// 查找成功，返回key的散列地址
}

// 销毁散列表结构
void destHashTable (HashTablePtr hashTable) {
	free(hashTable->table);
	free(hashTable);
}

/********************** 主程序 **********************/
int main() {
	setvbuf(stdout,NULL,_IONBF,0);
	setvbuf(stderr, NULL, _IONBF, 0);

	int kArray[HASH_SIZE] = {12,67,56,16,25,37,22,29,15,47,48,34};
	int i;
	HashTablePtr hashTable = (HashTablePtr)malloc(sizeof(HashTable));

	initHashTable(hashTable);
	for(i=0; i<hashTable->M; i++)
		insHashTable(hashTable, kArray[i]);

	int result, key;
	key = 39;	// 查找key39
	result = searchHashTable(hashTable, key);
	if(result != NOT_EXIST) {
		printf("key: %d, hashCode: %d.\n", key, result);
	} else {
		printf("key: %d, search failed.\n", key);
	}
	for(i=0; i<hashTable->M; i++) {
		result = searchHashTable(hashTable, kArray[i]);
		printf("key: %d, hashCode: %d.\n", kArray[i], result);
	}
	destHashTable(hashTable);

	return 0;
}
