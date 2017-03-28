/**
 * ���������̱仯����
 * bfsȷ����̱任���г��ȣ���������е��ʵ��յ㵥�ʵľ���
 * dfs������̾��������������̱仯����
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
    	
    	// Ĭ��ÿ���ڵ㵽end���ɴ�
    	for (String word : dict) {
    		distance.put(word, Integer.MAX_VALUE);
    	}
    	
    	// bfs��α���������нڵ㵽end����̾���
    	bfs(start, end, dictNew, distance);
    	
    	// dfs������̾������������̱任·��
    	ArrayList<String> sequence = new ArrayList<String>();
    	sequence.add(start);
    	dfs(sequeueList, sequence, start, end, distance, dict);
    	
    	return sequeueList;
    }
    
    /**
     * bfs��α���������ֵ�dict��ÿ�����ʵ��յ㵥�ʵ���̾���
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
     * ���ĵ���head������һ����ĸ���Ϸ�����ӣ����Ҽ�¼���ĺ�ĺϷ����ʵ��յ����̾���
     * @param level ���ĺ�ĺϷ����ʵ��յ����̾���
     */
	private void transformation(String head, 
								String start,
								String end, 
								int level, 
								Map<String, Integer> dist,
								Set<String> dict, 
								Queue<String> queue) {
		// ����ÿ���ַ�
		for (int i = 0; i < head.length(); i++) {
			String target = head.substring(i, i + 1);
			// ����25����ĸ
			for (int k = 0; k < 26; k++) {
				char[] letterChar = new char[]{(char)('a' + k)};
				String letterString = new String(letterChar);
				// �޸���ĸ������ԭ��ĸ��ͬ
				if (letterString.equals(target)) {
					continue;
				}
				StringBuffer str1 = new StringBuffer(new String(head));
				String strNew = new String(str1.replace(i, i + 1, letterString));
				// �յ㵥�ʺͷǷ�������Ч
				if (strNew.equals(end) || !dict.contains(strNew)) {
					continue;
				}
				queue.offer(new String(strNew));
				// ��¼��ʽͼ�нڵ㵥�ʵ��յ㵥�ʵ���̾���
				dist.put(new String(strNew), level);
				// �ѱ����ĵ����޳��ֵ�
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
    	// �ݹ���ڣ��ҵ���㵥��
    	if (dist == 0) {
    		sequeueList.add(new ArrayList<String>(sequence));
    		return;
    	}
    	
    	for (String word : dict) {
    		// ������̾���ǰ����ͬʱ����Ϸ��任
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
