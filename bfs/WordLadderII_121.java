/**
 * 求出所有最短变化序列
 * bfs确定最短变换序列长度，计算出所有单词到终点单词的距离
 * dfs沿着最短距离遍历出所有最短变化序列
 * http://www.lintcode.com/en/problem/word-ladder-ii/
 * @author yzwall
 */
package bfs;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution13 {
    /**
      * @param start, a string
      * @param end, a string
      * @param dict, a set of string
      * @return a list of lists of string
      */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
    	List<List<String>> sequeueList= new ArrayList<List<String>>();
    	if (dict == null || dict.size() == 0 || 
			start == null || end == null) {
    		return sequeueList;
    	}
    	dict.add(start);
    	dict.add(end);
    	
    	Set<String> dictNew = new HashSet(dict);
    	Map<String, Integer> distance = new HashMap<>();
    	
    	// 默认每个节点到end不可达
    	for (String word : dict) {
    		distance.put(word, Integer.MAX_VALUE);
    	}
    	
    	// bfs层次遍历求出所有节点到end的最短距离
    	bfs(start, end, dictNew, distance);
    	
    	// dfs沿着最短距离遍历所有最短变换路径
    	ArrayList<String> sequence = new ArrayList<String>();
    	sequence.add(start);
    	dfs(sequeueList, sequence, start, end, distance, dict);
    	
    	return sequeueList;
    }
    
    /**
     * bfs层次遍历，求出字典dict中每个单词到终点单词的最短距离
     */
	private void bfs(String start, 
					 String end, 
					 Set<String> dict,
    				 Map<String, Integer> dist) {
    	Queue<String> queue = new ArrayDeque<>();
    	queue.offer(end);
    	dist.put(end, 0);
    	int level = 1;
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			String head = queue.poll();
    			if (head.equals(start)) {
    				return;
    			}
    			transformation(head, start, end, level, dist, dict, queue);
    		}
    		level++;
    	}
    }
	
    /**
     * 更改单词head的任意一个字母，合法则入队，并且记录更改后的合法单词到终点的最短距离
     * @param level 更改后的合法单词到终点的最短距离
     */
	private void transformation(String head, 
								String start,
								String end, 
								int level, 
								Map<String, Integer> dist,
								Set<String> dict, 
								Queue<String> queue) {
		// 遍历每个字符
		for (int i = 0; i < head.length(); i++) {
			String target = head.substring(i, i + 1);
			// 遍历25个字母
			for (int k = 0; k < 26; k++) {
				char[] letterChar = new char[]{(char)('a' + k)};
				String letterString = new String(letterChar);
				// 修改字母不得与原字母相同
				if (letterString.equals(target)) {
					continue;
				}
				StringBuffer str1 = new StringBuffer(new String(head));
				String strNew = new String(str1.replace(i, i + 1, letterString));
				// 终点单词和非法单词无效
				if (strNew.equals(end) || !dict.contains(strNew)) {
					continue;
				}
				queue.offer(new String(strNew));
				// 记录隐式图中节点单词到终点单词的最短距离
				dist.put(new String(strNew), level);
				// 已遍历的单词剔除字典
				dict.remove(strNew);
			}
		}
	}
    
    private void dfs(List<List<String>> sequeueList, 
					 ArrayList<String> sequence, 
					 String next, String end,
					 Map<String, Integer> distance, 
					 Set<String> dict) {
    	int dist = distance.get(next);
    	// 递归出口：找到起点单词
    	if (dist == 0) {
    		sequeueList.add(new ArrayList<String>(sequence));
    		return;
    	}
    	
    	for (String word : dict) {
    		// 沿着最短距离前进，同时满足合法变换
    		if (distance.get(word) == dist - 1 && isValidTrans(next, word)) {
    			sequence.add(word);
    			dfs(sequeueList, sequence, word, end, distance, dict);
    			sequence.remove(sequence.size() - 1);
    		}
    	}
	}
	
	boolean isValidTrans(String from, String to) {
		int count = 0;
		for (int i = 0; i < from.length(); i++) {
			if (from.charAt(i) != to.charAt(i)) {
				count++;
			}
		}
		return count == 1 ? true : false;
	}
    
}

public class WordLadderII_121 {
	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		
		System.out.println(new Solution13().findLadders("hit", "cog", dict));
	}
}
