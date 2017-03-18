/**
 * http://www.lintcode.com/en/problem/sort-integers-ii/
 * 归并排序一个整型数组（升序）
 * @author yzwall
 */
package sort;

class Solution {
	
    public void sortIntegers2(int[] A) {
    	if (A == null || A.length == 0) {
    		return;
    	}
    	
    	// temp数组存放归并数组, 空间复杂度O(n)
    	int[] temp = new int[A.length];
    	mergeSort(A, 0, A.length - 1, temp);
    }
    
    /**
     * 归并排序：分治递归 + 二路归并，
     * 时间复杂度O(nlogn)
     * 空间复杂度O(logn + n)
     */
    private void mergeSort(int[] A, int start, int end, int[] temp) {
    	// 递归出口 单个元素不做归并排序
    	if (start >= end) {
    		return;
    	}
    	
    	/**
    	 * divide & conquer
    	 * 递归层数为logn
    	 * 递归时间复杂度O(logn), 空间复杂度O(logn)
    	 */
    	int mid = start + (end - start) / 2;
    	// 将A[start, mid]归并排序为有序数组
    	mergeSort(A, start, mid, temp);
    	// 将A[mid + 1, end]归并排序为有序数组
    	mergeSort(A, mid + 1, end, temp);
    	
    	// merge操作, 时间复杂度O(n)
    	merge(A, start, mid, end, temp);
    }
    
    /**
     * 二路归并：归并A[start, mid]与A[mid + 1, end]为有序数组A[start, end]
     * 时间复杂度O(n)， 空间复杂度O(n)
     * @param A 待归并数组
     * @param temp 原地归并所需额外空间
     */
    private void merge(int[] A, int start, int mid, int end, int[] temp) {
    	int leftIndex = start;
    	int rightIndex = mid + 1;
    	int index = start;
    	
    	while (leftIndex <= mid && rightIndex <= end) {
    		// 升序归并，优先归并较小元素
    		if (A[leftIndex] < A[rightIndex]) {
    			temp[index++] = A[leftIndex++];
    		} else {
    			temp[index++] = A[rightIndex++];
    		}
    	}
    	
    	while (leftIndex <= mid) {
    		// 右侧A[mid + 1, end]已经归并完毕，归并左侧A[start, mid]剩余内容
    		temp[index++] = A[leftIndex++];
    	}
    	
    	while (rightIndex <= end) {
    		// 左侧A[start, mid]已经归并完毕，归并右侧A[mid + 1, end]剩余内容 
    		temp[index++] = A[rightIndex++];
    	}
    	
    	// temp已经成功归并A[start, mid]与A[mid + 1, end]，原地覆盖原数组A[start, end]
    	for (int i = start; i <= end; i++) {
    		A[i] = temp[i];
    	}	
    }
}



public class SortIntegersII_MergeSort_464 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		Solution sort = new Solution();
		sort.sortIntegers2(A);
		//sort.print(A);

	}

}
