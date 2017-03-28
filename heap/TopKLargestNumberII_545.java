/**
 * 建立最大堆，取堆顶K次
 * http://www.lintcode.com/en/problem/top-k-largest-numbers-ii/
 * @author yzwall
 */
package heap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
	private MaxHeap maxHeap;
    public Solution(int k) {
        // initialize your data structure here.
    	maxHeap = new MaxHeap(k);
    }

    public void add(int num) {
    	maxHeap.add(num);
    }

    public List<Integer> topk() {
    	List<Integer> topK = new ArrayList<>();
    	int i = 0;
    	
    	int[] liskBackup = Arrays.copyOf(maxHeap.list, maxHeap.capacity);
    	int sizeBackup = maxHeap.size;
    	
    	while (!maxHeap.isEmpty() && i < maxHeap.topK) {
    		topK.add(maxHeap.delMax());
    		i++;
    	}
    	
    	maxHeap.list = Arrays.copyOf(liskBackup, maxHeap.capacity);
    	maxHeap.size = sizeBackup;
    	
    	return topK;
    }
}

class MaxHeap {
	public int capacity;
	public int size;
	public int topK;
	public int[] list;
	private final int UNVALID = Integer.MIN_VALUE;
	private final int MAX_NUM = 1000000;
	
	public MaxHeap(int k) {
    	capacity = MAX_NUM;
    	size = 0;
    	topK = k;
    	list = new int[capacity];
	}
	
	
	public void add(int num) {
    	// 插入新元素
    	list[size] = num;
    	int index = size;
    	size++;
    	// 上滤检查
    	percolearUp(index);
	}
	
	private boolean isMax(int index) {
		return index != 0;
	}
	
	public int delMax() {
		int index = size - 1;
		int max = list[0];
		swap(0, index);
		index = 0;
		list[--size] = UNVALID;
		if (!isEmpty()) {
			percolearDown(index);
		}
		return max;
	}

	private void percolearDown(int index) {
		int biggerIndex = 0;
		int rightIndex = 0;
		int leftIndex = 0;
		boolean lc = false, rc = false;
		while (true) {
			if (hasLChild(index)) {
				leftIndex = 2 * index + 1;
				lc = true;
			}
			if (hasRChild(index)) {
				rightIndex = 2 * index + 2;
				rc = true;
			}
			
			if (lc && rc) {
				biggerIndex = (list[leftIndex] > list[rightIndex]) ? leftIndex : rightIndex;
			} else if(lc) {
				biggerIndex = leftIndex;
			} else if(rc) {
				biggerIndex = rightIndex;
			} else {
				break;
			}
			
			if (!isOrder(list[index], list[biggerIndex])) {
				swap(index, biggerIndex);
				index = biggerIndex;
				lc = false;
				rc = false;
			} else {
				break;
			}
		}
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	private int getParent(int index) {
		return (index - 1) / 2;
	}
	
	public boolean isOrder(int parent, int child) {
		return parent > child;
	}
	
	private boolean hasLChild(int index) {
		int leftIndex = 2 * index + 1;
		return leftIndex < size && list[leftIndex] != UNVALID;
	}
	
	private boolean hasRChild(int index) {
		int rightIndex = 2 * index + 2;
		return rightIndex < size && list[rightIndex] != UNVALID;
	}	
	
	public void percolearUp(int index) {
		while (isMax(index)) {
			int parent = getParent(index);
			if (isOrder(list[parent], list[index])) {
				break;
			}
			swap(parent, index);
			index = parent;
		}
	}

	private void swap(int parent, int index) {
		int temp = list[parent];
		list[parent] = list[index];
		list[index] = temp;
	}
}


public class TopKLargestNumberII_545 {

	public static void main(String[] args) {
		Solution ss = new Solution(3);
		ss.add(3);
		ss.add(10);
		System.out.println(ss.topk());
		ss.add(1000);
		ss.add(-99);
		System.out.println(ss.topk());
	}
}
