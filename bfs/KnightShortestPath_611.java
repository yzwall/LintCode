/**
 * BFS求简单图（矩阵形式）两点间最短路径
 * 骑士每次只能走日字
 * http://www.lintcode.com/zh-cn/problem/knight-shortest-path/
 * @author yzwall
 */

package bfs;
import java.util.Queue;
import java.util.ArrayDeque;

class Point {
	public int x, y;
	public Point() { x = 0; y = 0; }
    public Point(int a, int b) { x = a; y = b; }
}


class Solution4 {
	private int ROW;
	private int COLUMN;
    /**
     * @param grid a chessboard included 0 (false) and 1 (true)
     * @param source, destination a point
     * @return the shortest path 
     */
	public int shortestPath(boolean[][] grid, Point source, Point destination) {
    	
    	if (grid == null || grid.length == 0) {
    		return 0;
    	}
    	
    	ROW = grid.length;
    	COLUMN = grid[0].length;
    	if (!checkBound(source.x, source.y) ||
			!checkBound(destination.x, destination.y)) {
    		return 0;
    	}
    	
    	int[] deltaX = new int[]{1, 1, -1, -1, 2, 2, -2, -2};
    	int[] deltaY = new int[]{2, -2, 2, -2, 1, -1, 1, -1};
    	boolean[][] isVisited = new boolean[ROW][COLUMN];
    	
    	Queue<Point> queue = new ArrayDeque<>();
    	queue.offer(source);
    	isVisited[source.x][source.y] = true;
    	int minPath = Integer.MAX_VALUE;
    	int path = 0;
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
//    		path++;
    		for (int i = 0; i < size; i++) {
    			Point head = queue.poll();
    			// 遍历8个方向
    			for (int j = 0; j < 8; j++) {
    				int xx = head.x + deltaX[j];
    				int yy = head.y + deltaY[j];
        			if (isValid(grid, isVisited, xx, yy)) {
        				//System.out.printf("%d %d\n", xx, yy);
        				if (isReached(xx, yy, source)) {
        					// 找到目标
        					minPath = Math.min(minPath, path);
        					//path = 0;
        				} else {
        					// 中间步骤
        					System.out.printf("%d %d %d\n", size, xx, yy);
        					queue.offer(new Point(xx, yy));
        					isVisited[xx][yy] = true;
        				}
        			}
        		}
    		}
    	}
    	
    	return minPath != Integer.MAX_VALUE ? minPath : -1;
    }
	
	private boolean checkBound(int x, int y) {
    	if (x < 0 || x >= ROW ||
        	y < 0 || y >= COLUMN) {
    		return false;
    	}
    	return true;
	}
    
    
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
    
    private boolean isReached(int x, int y, Point source) {
    	return x == source.x && y == source.y ? true : false;
    }
}




public class KnightShortestPath_611 {

	public static void main(String[] args) {
		boolean[][] grid = new boolean[][] {{false,true,false},
											{false,false,false},
											{false,false,false,}};
		Point source = new Point(2, 0);
		Point dest = new Point(2, 2);
		System.out.println(new Solution4().shortestPath(grid, source, dest));
	}

}
