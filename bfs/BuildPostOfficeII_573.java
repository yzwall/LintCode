/**
 * 由于房子数量远小于空地数量，因此枚举房子到每个空地的距离，每个空格累加到该房子的距离，最后取距离所有房子距离最小的空格
 * 由于房子数量远小于空地数量，枚举空格到房子的距离空间复杂度高，会MLE
 * http://www.lintcode.com/en/problem/build-post-office-ii/
 * @author yzwall
 */
package bfs;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution9 {
	private final int EMPTY = 0;
	private final int HOUSE = 1;
	private final int DIRECT = 4;
	private int ROW;
	private int COLUMN;
	private class Point {
		int x, y;
		public Point(int x, int y) { 
			this.x = x;
			this.y = y;
		}
	}
    /**
     * @param grid a 2D grid
     * @return an integer
     */
    public int shortestDistance(int[][] grid) {
    	if (grid == null || grid.length == 0) {
    		return -1;
    	}
    	
    	ROW = grid.length;
    	COLUMN = grid[0].length;
    	Queue<Point> queue = new ArrayDeque<>();
    	ArrayList<Point> houses = new ArrayList<>();
    	int [][] visitTimes = new int[ROW][COLUMN];
    	int [][] sum = new int[ROW][COLUMN];
    	int[] deltaX = new int[]{0, 0, -1, 1};
    	int[] deltaY = new int[]{-1, 1, 0, 0};
    	for (int i = 0; i < ROW; i++) {
    		for (int k = 0; k < COLUMN; k++) {
    			if (grid[i][k] == HOUSE) {
    				houses.add(new Point(i, k));
    			}
    		}
    	}
    	
    	int minSum = Integer.MAX_VALUE;
    	for (Point house : houses) {
    		queue.offer(house);
    		int level = 0;
    		boolean [][] isVisited = new boolean[ROW][COLUMN];
    		while (!queue.isEmpty()) {
    			int size = queue.size();
    			level++;
    			for (int i = 0; i < size; i++) {
    				Point head = queue.poll();
    				for (int k = 0; k < DIRECT; k++) {
	    				int xx = head.x + deltaX[k];
	    				int yy = head.y + deltaY[k];
	    				if (!checkBound(xx, yy)) {
	    					continue;
	    				}
	    				if (isVisited[xx][yy]) {
	    					continue;
	    				}
	    				
	    				if (grid[xx][yy] == EMPTY) {
	    					sum[xx][yy] += level;
	    					visitTimes[xx][yy]++;
	    					isVisited[xx][yy] = true;
	    					if (visitTimes[xx][yy] == houses.size() && sum[xx][yy] < minSum) {
	    						minSum = sum[xx][yy];
	    					}
	    					queue.offer(new Point(xx, yy));
	    				}
    				}
    			}
    		}
    	}
    	
    	return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
    
    // 检查越界
    private boolean checkBound(int x, int y) {
    	return x >= 0 && x < ROW && y >= 0 && y < COLUMN;
    }
}

public class BuildPostOfficeII_573 {
	public static void main(String[] args) {
		int[][] grid = new int[][] {{0,1,0},
									{1,0,1},
									{0,1,0}};
		System.out.println(new Solution9().shortestDistance(grid));
	}
}
