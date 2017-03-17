/*
 * File name:StackByArray.c
 * Description: ����ʵ��ջ
 * Author: yzwall
 * Data: 2016/9/1 14:46
 */
#include <stdio.h>
#include <stdlib.h>
#define MIN_STACK_SIZE 3
#define EMPTY_TOP -1
////////////////////////////////////////////////////
typedef int ElementType;

// һ��ȷ�����������ջ�ᱻ��̬ȷ��
typedef struct Stack {
	int capacity;		// ջ�������
	int top;			// ջ��
	ElementType *Array;	// ջ���ݿռ�
}Stack;

// ��ջջ��Ĭ�ϸ�ֵ-1
int isEmpty(Stack *stack) {
	return stack->top == EMPTY_TOP;
}


int isFull(Stack *stack) {
	return stack->top == stack->capacity;
}

// ���ջ����ջ��Ϊ-1
void clearStack(Stack *stack) {
	stack->top = EMPTY_TOP;
}

Stack *createStack(int maxElements) {
	if(maxElements < MIN_STACK_SIZE) {
		printf("Stack size is too small\n");
		exit(-1);
	}
	Stack *stack = (Stack *)malloc(sizeof(Stack));
	if(stack == NULL) {
		printf("Stack memory is fault");
		exit(-1);
	}
	stack->Array = (ElementType *)malloc(sizeof(int) * maxElements);
	if(stack->Array == NULL) {
		printf("Stack memory is fault");
		exit(-1);
	}
	stack->capacity = maxElements - 1; // top��-1�����������ﵽ�������ʱ����ջ
	clearStack(stack);
	return stack;
}

void disposeStack(Stack *stack) {
	if(stack != NULL) {
		free(stack->Array);
		free(stack);
	}
	printf("Dispose Success.\n");
}

// top��-1�����������ﵽ�������ʱ����ջ
void push(Stack *stack, ElementType element) {
	if(isFull(stack)) {
		printf("Stack is full, push failed.\n");
		return;
	} else {
		stack->Array[++stack->top] = element;
	}
	printStack(stack);
}

//
void pop(Stack *stack) {
	if(isEmpty(stack)) {
		printf("Stack is empty, pop failed.\n");
		return;
	} else {
		stack->top--;
	}
	printStack(stack);
}

// ��ȡջ��Ԫ�أ���ջջ������0
ElementType getTop(Stack *stack) {
	if(isEmpty(stack)) {
		printf("Stack is empty, get top of stack failed.\n");
		return 0;
	} else {
		return stack->Array[stack->top];
	}
}

// ����������ջ��Ԫ�أ���ջջ������0
ElementType getTopAndPop(Stack *stack) {
	if(isEmpty(stack)) {
		printf("Stack is empty, get top and pop failed.\n");
		return 0;
	} else {
		return stack->Array[stack->top--];
	}
}

// ����ջ
void printStack(Stack *stack) {
	if(isEmpty(stack)) {
		printf("Stack is empty.\n");
	} else {
		printf("Stack:\n");
		int i;
		for(i=stack->top; i>EMPTY_TOP; i-- )
		{
			printf("%d\n", stack->Array[i]);
		}
	}
	printf("\n");
}

void printTOS(Stack *stack) {
	printf("TOS:\n");
	printf("%d\n", getTopAndPop(stack));
}

////////////////////////////////////////
int main(){
	setvbuf(stdout,NULL,_IONBF,0);
	Stack *stack = createStack(MIN_STACK_SIZE);
	push(stack, 11);
	push(stack, 22);
	push(stack, 33);
	pop(stack);
	pop(stack);
	pop(stack);
	printTOS(stack);
	printTOS(stack);
	printTOS(stack);
	disposeStack(stack);

	return 0;
}
