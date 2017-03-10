/**
 * http://www.lintcode.com/en/problem/search-a-2d-matrix-ii/
 * @author yzwall
 */
package BinarySearch;

/**
 * @author yzwall
 */
class Solution8 {
	private int count;
	private int row;
	private int column;
	/**
	 * 返回target在矩阵中出现的次数总和
	 * @param matrix
	 * @param target
	 * @return 返回target在矩阵中出现的次数总和
	 */
	public int searchMatrix(int[][] matrix, int target) {
		
		if (matrix == null || 0 == matrix.length) {
			return 0;
		}
		for (int i=0; i<matrix.length; i++) {
			if(matrix[i].length == 0) {
				return 0;
			}
		}
		
		count = 0;
		row = matrix.length - 1;
		column = matrix[0].length - 1;
		findTarget(matrix, row, 0, target);
		
		return count;
		
	}
	
	/**
	 * 每次取子矩阵左下元素和target比较，递归查找
	 * @param i 当前左下元素的行下标
	 * @param j 当前左下元素的列下标
	 */
	private void findTarget(int[][] matrix, int i, int j, int target) {
		// 递归基准条件
		if (i < 0 || i > row || j < 0 || j > column) {
			return;
		}
		
		// 递归分解
		if (matrix[i][j] == target) {
			count++;
			// 砍掉第i行，第j列
			findTarget(matrix, i - 1, j + 1, target);
		} else if (matrix[i][j] < target) {
			// 砍掉第j列
			findTarget(matrix, i, j + 1, target);
		} else {
			// 砍掉第i行
			findTarget(matrix, i - 1, j, target);
		}
	}
}

public class Search2DMatrixII {

	public static void main(String[] args) {
		int[][] matrix = {{1, 3, 5, 7},
						  {2, 4, 7, 8},
						  {3, 8, 9, 10}};
		//int[][] matrix = {{},{}};
		System.out.println(new Solution8().searchMatrix(matrix, 11));
	}

}
