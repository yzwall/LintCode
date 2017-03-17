/*
 * File name:HashTable.c
 * Description: ɢ�б�ADT,���෨ɢ�к���������̽���ͻ
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
/* ɢ�б�ṹ */
typedef struct HashTable {
	KeyType *table;
	int count; // ɢ�б���Ԫ������
	int M; 	   // ɢ�б�
} HashTable, *HashTablePtr;

// ��ʼ��ɢ�б�
void initHashTable (HashTablePtr hashTable) {
//	hashTable = (HashTablePtr)malloc(sizeof(HashTable));
	hashTable->M = HASH_SIZE;
	hashTable->count = hashTable->M;
	hashTable->table = (KeyTypePtr)malloc((hashTable->M) * sizeof(KeyType));
	int i;
	for(i=0; i<hashTable->M; i++)	// Ĭ��ɢ�б���Ԫ�ز�����
		hashTable->table[i] = NULL_KEY;
}

/*
 *  ����ǰ̽��table[hash(key)] == NULL_KEY��ʾ̽�⵽��Ͱ�������ͻ
 *  ����ʱ̽��table[hash(key)] == key��ʾ����key�ɹ����������ʧ��
 */
BOOL hasCollision(HashTablePtr hashTable, int proAddr, int keyElement) {
	// proAddr = hash(key), ��ʾɢ�е�ַ
	return hashTable->table[proAddr] != keyElement;
}

// ɢ�к��������ó��෨
int Hash(HashTablePtr hashTable, KeyType key) {
	return key % hashTable->M;
}

// ��ɢ�б�����¼, ������̽�Ƿ��ͻ
void insHashTable(HashTablePtr hashTable, KeyType key) {
	int proAddr = Hash(hashTable, key);					// ����ɢ�е�ַ
	while(hasCollision(hashTable, proAddr, NULL_KEY))	// ��ͻ
	{
		proAddr = (proAddr+1) % hashTable->M;	// ���Ŷ�ַ������̽��
	}
	hashTable->table[proAddr] = key;	// �ҵ���Ͱ������key
}

// ɢ�б�����ɺ󣬲���key��ɢ�е�ַ
int searchHashTable(HashTablePtr hashTable, KeyType key) {
	int proAddr = Hash(hashTable, key);
	while(hasCollision(hashTable, proAddr, key))	// ��ƥ��
	{
		proAddr = (proAddr+1) % hashTable->M;
		/*
		 * ��ͻ����ʱ�������������
		 * key��ɢ�б��У�key������Ͱ�У���ʱɢ�б��б�Ȼ�����ڿ�Ͱ
		 * key����ɢ�б��У�����̽��ص�ԭ�㣬ɢ�б��д��ڿ�Ͱ
		 */
		if(hashTable->table[proAddr] == NULL_KEY || proAddr == Hash(hashTable, key)) {
			return NOT_EXIST;	// ����ʧ��
		}
	}
	return proAddr;	// ���ҳɹ�������key��ɢ�е�ַ
}

// ����ɢ�б�ṹ
void destHashTable (HashTablePtr hashTable) {
	free(hashTable->table);
	free(hashTable);
}

/********************** ������ **********************/
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
	key = 39;	// ����key39
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
