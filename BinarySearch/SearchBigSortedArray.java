/**
 * http://www.lintcode.com/zh-cn/problem/search-in-a-big-sorted-array/
 * 题目大意为在不知道有序数组长度的情况下，找到指定元素最早出现位置
 * 采取放大end确定二分边界，然后在区间内二分查找最早出现位置，题目数据不严谨没有检查越界情况
 * 
 * @author yzwall
 */
package BinarySearch;

class Solution10 {
    /**
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
    	// 边界检查
    	if(!isExists(reader, 0)) {
    		return -1;
    	}
    	
    	// 倍增法：放大end，确定二分右边界
    	int right = 0;
    	int left = 2;
    	while(isExists(reader, left) && reader.get(left) < target) {
    		right = left;
    		// 位运算效率高
    		left = left << 1;
    	}  
    	
    	int firstIndex = Integer.MAX_VALUE;
    	int start = right;
    	int end = left;
    	while(start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		int midValue = reader.get(mid);
    		if(midValue == target) {
    			firstIndex = Math.min(firstIndex, mid);
    			end = mid;
    		} else if(midValue < target) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	
    	// 优先检查左边界，确保最早出现
    	if(reader.get(start) == target) {
    		return Math.min(firstIndex, start);
    	}
    	if(reader.get(end) == target) {
    		return Math.min(firstIndex, end);
    	}   	
    	
    	return firstIndex < Integer.MAX_VALUE ? firstIndex : -1;
    }
    
    private boolean isExists(ArrayReader reader, int index) {
    	if(reader.get(index) == -1 || reader.get(index) == Integer.MAX_VALUE) {
    		return false;
    	}    	
    	return true;
    }
    
}

/**
 * 添加测试类，模拟题目接口
 * @author yzwall
 */
class ArrayReader {
	// 添加测试变量，模拟代码版本号序列
	private int[] nums = new int[]{1, 1, 1, 1, 11, 11, 88};
	
	/**
	 * 添加测试方法
	 * 判断代码版本号序列中第k版本是否错误
	 */
	public int get(int index) {
		if (index < 0) {
			return -1;
		} 
		if( index >= nums.length) {
			return Integer.MAX_VALUE;
		}
		return nums[index];
	}
}


public class SearchBigSortedArray {

	public static void main(String[] args) {
		System.out.println(new Solution10().searchBigSortedArray(new ArrayReader(), 1));
	}

}
