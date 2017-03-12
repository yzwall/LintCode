/**
 * http://www.lintcode.com/en/problem/smallest-rectangle-enclosing-black-pixels/
 * 最小覆盖矩形，通过指定像素点(x,y)分别做四次二分逼近
 * @author yzwall
 */
package BinarySearch;

class Solution15 {
	private boolean[] row;	
	private boolean[] column;
	private int ROW;	
	private int COLUMN;
	private final int COLUMN_FLAG = 1;
	private final int ROW_FLAG = 0;

    /**
     * @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer
     */
    public int minArea(char[][] image, int x, int y) {
    	if (image == null || image.length == 0 || image[0].length == 0) {
    		return 0;
    	}
    	
    	setPara(image);
    	
    	// 二分查找左边界:第一个出现像素点的列标
    	int leftBound = findLeft(image, 0, y);
    	
    	// 二分查找右边界:最后一个出现像素点的列标
    	int rightBound = findRight(image, y, COLUMN - 1);
    	
    	// 二分查找上边界：第一个出现像素点的行标
    	int upBound = findUp(image, 0, x);
    	
		// 二分查找下边界：最后一个出现像素点的行标
    	int bottomBound = findBottom(image, x, ROW - 1);
    	
    	return (rightBound - leftBound + 1) * (bottomBound - upBound + 1);
    }

	/**
     * 二分找左边界，第一个出现像素点的列
     * 相当于在OOOOOXXXXX中找第1个X出现的位置
     * @param start 第0列
     * @param end 第x列
     * @return 第一个出现像素点的列标
     */
    private int findLeft(char[][] image, int start, int end) {
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (isContainsPixels(image, mid, COLUMN_FLAG)) {
				// 抛弃右侧，end逼近firstIndex
				end = mid;
			} else {
				// 抛弃左侧O区域
				start = mid;
	    	}
		}
		
		// 优先检测二分区间左侧，确保firstIndex
		if(isContainsPixels(image, start, COLUMN_FLAG)) {
			return start;
		}
		return end;
    }
    
    /**
     * 二分找右边界
     * 相当于XXXXOOOOO，找最后一个X出现的位置
     * @param start 第x列
     * @param end 最后一列
     * @return 最后一个出现像素点的列标
     */
    private int findRight(char[][] image, int start, int end) {
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (isContainsPixels(image, mid, COLUMN_FLAG)) {
    			// 抛弃左侧X区域，逼近lastIndex
    			start = mid;
    		} else {
    			// 抛弃右侧O区域
    			end = mid;
    		}
    	}
    	
    	// 优先检测二分区间右侧，确保lastIndex
    	if (isContainsPixels(image, end, COLUMN_FLAG)) {
    		return end;
    	}
		return start;
	}
    
    /**
     * 二分找出上边界
     * 相当于在OOOOOXXXX中找出第一个X出现位置
     * @param start 第0行
     * @param end 第x行
     * @return 第一个出现像素点的行标
     */
	private int findUp(char[][] image, int start, int end) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (isContainsPixels(image, mid, ROW_FLAG)) {
				// 抛弃下侧X区域，逼近firstIndex
				end = mid;
			} else {
				// 抛弃上侧O区域
				start = mid;
			}
		}
		
		// 优先检查二分区间左(上)侧，确保firstIndex
		if (isContainsPixels(image, start, ROW_FLAG)) {
			return start;
		}
		return end;
	}
	
    /**
     * 二分找出下边界
     * 相当于在XXXXOOOO中找到第一个X出现位置
     * @param start 第x行
     * @param end 最后一行
     * @return 最后一个出现像素点的行标
     */
	private int findBottom(char[][] image, int start, int end) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (isContainsPixels(image, mid, ROW_FLAG)) {
				// 抛弃上侧X区域，逼近lastIndex
				start = mid;
			} else {
				// 抛弃下侧O区域
				end = mid;
			}
		}
		
		// 优先检查二分区间右(下)侧，确保lastIndex;
		if(isContainsPixels(image, end, ROW_FLAG)) {
			return end;
		}
		
		return start;
	}
	
    // 参数初始化
    private void setPara(char[][] image) {
    	ROW = image.length;
    	COLUMN = image[0].length;
    	row = new boolean[ROW];
    	column = new boolean[COLUMN];
    }
    
    /**
     * 检查第n行/列是否存在像素点
     * @param flag	0	检查第n行
     * 			  	1	 检查第n列
     * @return true 该行/列存在像素点
     */
    private boolean isContainsPixels(char[][]image, int n, int flag) {
    	// 检查第n行
    	if (flag == ROW_FLAG) {
    		if (row[n]) {
    			return true;
    		}
    		for (int i = 0; i < COLUMN; i++) {
    			if(image[n][i] == '1') {
    				row[n] = true;
    				column[i] = true;
    				return true;
    			}
    		}
    		return false;
    	} else {	// 检查第n列
    		if (column[n]) {
    			return true;
    		}
    		for (int i = 0; i < ROW; i++) {
    			if(image[i][n] == '1') {
    				row[i] = true;
    				column[n] = true;
    				return true;
    			}
    		}
    		return false;    		
    	}
    }
}


public class SmallEclosingRectangle {

	public static void main(String[] args) {
		char[][] image = {
							{'0', '0', '1', '0'},
							{'0', '1', '1', '0'},
							{'0','1', '0', '0'}
						 };
		System.out.println(new Solution15().minArea(image, 0, 2));
	}

}
