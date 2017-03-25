/**
 * Knight Shortest Path的简化版，遍历方向改为4个，默认起点和终点
 * BFS层级遍历求简单图最短路经
 * http://www.lintcode.com/en/problem/knight-shortest-path-ii/
 * @author yzwall
 */
package bfs;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution5 {
	private int ROW;
	private int COLUMN;
	
	private class Point {
		public int x, y;
		public Point() { x = 0; y = 0; }
	    public Point(int a, int b) { x = a; y = b; }
	}

    public int shortestPath2(boolean[][] grid) {
        // Write your code here
    	if (grid == null || grid.length == 0) {
    		return 0;
    	}
    	
    	ROW = grid.length;
    	COLUMN = grid[0].length;
    	
    	Point source = new Point(0, 0);
    	Point destination = new Point(ROW - 1, COLUMN - 1);

    	if (!checkBound(source.x, source.y) ||
			!checkBound(destination.x, destination.y)) {
    		return 0;
    	}
    	
    	int[] deltaX = new int[]{1, -1, 2, -2};
    	int[] deltaY = new int[]{2, 2, 1, 1};
    	int directNum = 4;
    	boolean[][] isVisited = new boolean[ROW][COLUMN];
    	
    	Queue<Point> queue = new ArrayDeque<>();
    	queue.offer(source);
    	isVisited[source.x][source.y] = true;
    	int path = 0;
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			Point head = queue.poll();
    			if (isReached(head.x, head.y, destination)) {
					// 找到目标
					return path;
				} 
    			// 遍历8个方向
    			for (int j = 0; j < directNum; j++) {
    				int xx = head.x + deltaX[j];
    				int yy = head.y + deltaY[j];
        			if (isValid(grid, isVisited, xx, yy)) {
    					queue.offer(new Point(xx, yy));
    					isVisited[xx][yy] = true;
        			}
        		}
    		}
    		// 逐层遍历，每层未遍历到，则路径长度+1
    		path++;
    	}
    	return -1;
    }
    
	private boolean checkBound(int x, int y) {
    	if (x < 0 || x >= ROW ||
        	y < 0 || y >= COLUMN) {
    		return false;
    	}
    	return true;
	}
    
    // 检查grid[x][y]是否可以走
    private boolean isValid(boolean[][] grid, boolean[][] isVisited,
    					   	  int x, int y) {
    	if (!checkBound(x, y)) {
    		return false;
    	}
    	if (!isVisited[x][y] && !grid[x][y]) {
    		return true;
    	}
    	return false;
    }
    
    // 检查是否遇到目标
    private boolean isReached(int x, int y, Point dest) {
    	return x == dest.x && y == dest.y ? true : false;
    }
}


public class KnightShortestPathII_630 {

	public static void main(String[] args) {
		boolean[][] grid = new boolean[][] {{false,false,false,false},
											{false,false,false,false},
											{false,true,false,false}};
		System.out.println(new Solution5().shortestPath2(grid));
	}

}
