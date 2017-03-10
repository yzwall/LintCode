/**
 * 未完成
 */

package BinarySearch;

/**
 * @author yzwall
 */
class Solution7 {

	private int count;
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
    	
    	count = 0;
    	binarySearch(A, target, 0, A.length - 1);
    	return count;
    }
    
	private void binarySearch(int[] A, int target, int start, int end) {
		if(! (start <= end)) {
			if(A[start] == target) {
				count++;
				return;
			} 
			if(A[end] == target) {
				count++;
				return;
			}
			return;
		}
		
		int mid = start + (end - start) / 2;
		if(A[mid] == target) {
			count++;
			binarySearch(A, target, start, mid);
			binarySearch(A, target, mid, end);
		} else if(A[mid] < target) {
			binarySearch(A, target, mid, end);
		} else {
			binarySearch(A, target, start, mid);
		}
		
	}
    
}

public class TotalOccurrence {

	public static void main(String[] args) {
		
		int[] A = new int[]{3,3,3,4,5};
		System.out.println(new Solution7().totalOccurrence(A, 3));

	}

}
