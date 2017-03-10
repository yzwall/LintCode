/**
 * www.lintcode.com/zh-cn/problem/classical-binary-search/
 * O(n)±©Á¦·¨£¬2.4s
 */
package BinarySearch;

class Solution2 {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int findPosition(int[] nums, int target) {
    	
    	if(nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	int pos;
    	for(int i = 0; i < nums.length; i++) {
    		if(nums[i] == target) {
    			return i;
    		}
    	}
    	return -1;
    }
}


public class FindPositionBruteForce {

	public static void main(String[] args) {
		
		int[] nums = new int[]{1, 2, 2, 4, 5, 5};
//		List<Integer> posList = new ArrayList<Integer>();
		System.out.println(new Solution2().findPosition(nums, 6));

	}

}
