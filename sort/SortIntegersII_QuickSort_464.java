/**
 * http://www.lintcode.com/en/problem/sort-integers-ii/
 * 快速排序一个数组（升序）
 * @author yzwall
 */
package sort;

class Solution1 {
	
    public void sortIntegers2(int[] A) {
    	if (A == null || A.length == 0) {
    		return;
    	}
    	
    	quickSort(A, 0, A.length - 1);
    }
    
    private void quickSort(int[]A, int start, int end) {
    	//　递归出口　单元素不做排序
    	if (start >= end) {
    		return;
    	}
    	
    	/**
    	 * 切分操作时间复杂度O(n)，空间复杂度O(1)
    	 * 切分点pivot取数组值，而不是下标，保证 pivot左侧“小”，右侧“大”
    	 */
    	int leftIndex = start;
    	int rightIndex = end;
    	int pivot = A[start + (end - start) / 2];
    	
    	// leftIndex <= rightIndex, < s导致栈溢出
    	while (leftIndex <= rightIndex) {
    		// 在左侧寻找不合法数， A[leftIndex] < pivot，保证切分均匀
    		while (leftIndex <= rightIndex && A[leftIndex] < pivot) {
    			leftIndex++;
    		}
    		
    		// 在右侧寻找不合法数， A[rightIndex] > pivot，保证切分均匀
    		while (leftIndex <= rightIndex && A[rightIndex] > pivot) {
    			rightIndex--;
    		}
    		
    		// 找到不合法数，将不合法数放入对应区间内
    		if (leftIndex <= rightIndex) {
    			int temp = A[leftIndex];
    			A[leftIndex] = A[rightIndex];
    			A[rightIndex] = temp;
    			// 继续查找不合法数
    			leftIndex++;
    			rightIndex--;
    		}
    	}
    	
    	/**
    	 * 跳出循环，leftIndex与rightIndex已互换位置
    	 * 分治时间复杂度O(logn), 空间复杂度O(1)
    	 */
    	quickSort(A, start, rightIndex);
    	quickSort(A, leftIndex, end);
    }
}


public class SortIntegersII_QuickSort_464 {

	public static void main(String[] args) {
		
		int[] A = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		Solution1 sort = new Solution1();
		sort.sortIntegers2(A);
		//sort.print(A, 0, A.length - 1);
	}

}
