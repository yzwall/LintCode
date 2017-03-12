/**
 * 未完成
 */

package BinarySearch;

/**
 * @author yzwall
 */
class Solution7 {
	
	public int[] add;
	
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int totalOccurrence(int[] A, int target) {
    	// 异常检查
    	if(A == null || 0 == A.length) {
    		return 0;
    	}
    	
    	int start = 0;
    	int end = A.length - 1;
    	
    	return binarySearchCount(A, start ,end, target);
    }

	private int binarySearchCount(int[] A, int start, int end, int target) {
		int count = 0;
		
		// 递归出口
		if(!(start + 1 < end)) {
			if (A[start] == target) {
				count++;
			} 
			if (A[end] == target) {
				count++;
			}
			return count;
		}

		// 分治
		int mid = start + (end - start) / 2;
		if (A[mid] == target) {
			count++;
			count += binarySearchCount(A, start, mid, target); 
			count += binarySearchCount(A, mid, end, target);
		} else if(A[mid] < target) {
			count +=  binarySearchCount(A, mid, end, target); 
		} else {
			count +=  binarySearchCount(A, start, mid, target);
		}
		
		// 合并
		return count;
	}
}

public class TotalOccurrence {

	public static void main(String[] args) {
		
		int[] A = new int[]{1, 2, 2, 4, 5};
		Solution7 testCase = new Solution7();
		System.out.println(testCase.totalOccurrence(A, 2));
		
		/*for(int i = 0; i < testCase.add.length; i++) {
			System.out.println(i + " " + testCase.add[i]);
		}*/
		
	}

}
