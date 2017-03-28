/**
 * 根据单词变换构造隐式图，BFS求最短变换序列长度
 * http://www.lintcode.com/en/problem/word-ladder/
 * @author yzwall
 */
package bfs;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution12 {
    /**
     * @param start, a string
     * @param end, a string
     * @param dict, a set of string
     * @return an integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
    	//List<List<String>> transSequeue = new ArrayList<List<String>>();
    	if (dict == null || dict.size() == 0 || 
			start == null || end == null) {
    		return 0;
    	}
    	
    	Queue<String> queue = new ArrayDeque<String>();
    	int transLength = 1;
    	queue.offer(start);
    	while (!queue.isEmpty()) {
    		// 逐层遍历隐式图
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			String head = queue.poll();
    			if (head.equals(end)) {
    				return transLength;
    			}
    			// 更改head单个字母，满足条件者入队
    			transformation(head, end, dict, queue);
    		}
    		transLength++;
    	}
    	return transLength;
    }

	private void transformation(String head, String end, Set<String> dict, Queue<String> queue) {
		// 遍历每个字符
		for (int i = 0; i < head.length(); i++) {
			String target = head.substring(i, i + 1);
			// 遍历25个字母
			for (int k = 0; k < 26; k++) {
				char[] letterChar = new char[]{(char)('a' + k)};
				String letterString = new String(letterChar);
				// String用equals()判断字面值
				if (letterString.equals(target)) {
					continue;
				}
				StringBuffer str1 = new StringBuffer(new String(head));
				String strNew = new String(str1.replace(i, i + 1, letterString));
				if (!strNew.equals(end) && !dict.contains(strNew)) {
					continue;
				}
				queue.offer(new String(strNew));
				// 已遍历的单词剔除字典
				dict.remove(strNew);
			}
		}
	}
}

public class WordLadder_120 {
	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		
		System.out.println(new Solution12().ladderLength("hit", "cog", dict));
	}
}
