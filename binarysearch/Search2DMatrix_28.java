/**
 * http://www.lintcode.com/zh-cn/problem/search-a-2d-matrix/
 * 在有序二维数组中查找元素，排序数组中找最接近元素的变种
 */
package binarysearch;

/**
 * 列中二分找出第0列中target的maxmin行坐标，
 * 然后在指定行中对target进行二分查找
 * @author yzwall
 */
class Solution6 {

	private int column;
	private int line;
	
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
    	// 异常及边界检测
    	if(matrix == null || 0 == matrix.length) {
    		return false;
    	}
    	line = matrix.length;
    	column = matrix[0].length;
    	// 寻找minmax方法中不识别界外情况，提前进行边界检查
    	if(matrix[0][0] > target || matrix[line - 1][column - 1] < target) {
    		return false;
    	}
    	
    	// 在第0列中，找出target的maxmin行下标
    	int index = findColumnMaxMinIndex(matrix, target);
    	
    	// target = matrix[0][0]
    	if(index == 0) {
    		return true;
    	}
    	
    	// target在矩阵最后一行
    	if(index == line) {
    		return binaryLineSearch(matrix, line - 1, target);
    	}
    	
    	// target在矩阵index - 1行
    	return binaryLineSearch(matrix, index - 1, target);
    }
    
    
    /**
     * 二分法 O(log(n))
     * @param lineNum 矩阵中指定行，在该行进行二分查找
     * @return 对矩阵行进行二分查找，查找成功返回true，否则返回false
     */
	private boolean binaryLineSearch(int[][] matrix, int lineNum, int target) {
		int start = 0;
		int end = column - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if(matrix[lineNum][mid] == target) {
				return true;
			}
			if(matrix[lineNum][mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		
		if(matrix[lineNum][start] == target || matrix[lineNum][end] == target) {
			return true;
		}
		
		return false;
	}

	/**
	 * 二分法 O(log(m))
	 * @return 找出target在第0列中的最先出现的maxmin的行下标
	 */
	private int findColumnMaxMinIndex(int[][] matrix, int target) {
		int start = 0;
		int end = line - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if(matrix[mid][0] == target) {
				start = mid;
			} else if(matrix[mid][0] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		
		if(matrix[start][0] >= target) {
			return start;
		} 
		if(matrix[end][0] >= target) {
			return end;
		} 
		
		return line;
	}
}


public class Search2DMatrix_28 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dimNum = 4;
		int[][] matrix = new int [][]{	
										{1, 2, 3, 5},
										{7, 9, 13, 16},
										{19, 21, 25, 30},
										{31, 33, 34, 36}
									 };
		
		System.out.println(new Solution6().searchMatrix(matrix, 33));
		System.out.println(new Solution6().searchMatrix(matrix, 34));
		System.out.println(new Solution6().searchMatrix(matrix, 1));
		
		int[][] matrix1 = new int [][]{};		
		 
		System.out.println(new Solution6().searchMatrix(matrix1, 1));
	
		

	}

}
