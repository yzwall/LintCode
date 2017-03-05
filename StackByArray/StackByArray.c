/*
 * File name:StackByArray.c
 * Description: 数组实现栈
 * Author: yzwall
 * Data: 2016/9/1 14:46
 */
#include <stdio.h>
#include <stdlib.h>
#define MIN_STACK_SIZE 3
#define EMPTY_TOP -1
////////////////////////////////////////////////////
typedef int ElementType;

// 一旦确定最大容量，栈会被动态确定
typedef struct Stack {
	int capacity;		// 栈最大容量
	int top;			// 栈顶
	ElementType *Array;	// 栈数据空间
}Stack;

// 空栈栈顶默认赋值-1
int isEmpty(Stack *stack) {
	return stack->top == EMPTY_TOP;
}


int isFull(Stack *stack) {
	return stack->top == stack->capacity;
}

// 清空栈，置栈顶为-1
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
	stack->capacity = maxElements - 1; // top从-1向上增长，达到最大容量时，满栈
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

// top从-1向上增长，达到最大容量时，满栈
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

// 获取栈顶元素，空栈栈顶返回0
ElementType getTop(Stack *stack) {
	if(isEmpty(stack)) {
		printf("Stack is empty, get top of stack failed.\n");
		return 0;
	} else {
		return stack->Array[stack->top];
	}
}

// 弹出并返回栈顶元素，空栈栈顶返回0
ElementType getTopAndPop(Stack *stack) {
	if(isEmpty(stack)) {
		printf("Stack is empty, get top and pop failed.\n");
		return 0;
	} else {
		return stack->Array[stack->top--];
	}
}

// 遍历栈
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
