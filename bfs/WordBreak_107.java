/**
 * http://www.lintcode.com/en/problem/word-break/
 * WA Memory Limit Exceeded
 * @author yzwall
 */
package bfs;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashMap;

class Solution14 {
    /**
     * @param s: A string s
     * @param dict: A dictionary of words dict
     */
	private int maxLen;
	private Map<String, Boolean> map;
    public boolean wordBreak(String s, Set<String> dict) {
    	if (s == null || dict == null) {
    		return false;
    	}
    	
    	if (s.length() == 0 && dict.size() == 0) {
    		return true;
    	}
    	
    	maxLen = 0;
    	map = new HashMap<>();
    	for (String word : dict) {
    		maxLen = maxLen < word.length() ? word.length() : maxLen;
    	}
    	
    	return wordBreaker(s, 0, dict);
    }
    
    
    private boolean wordBreaker(String target, int startIndex, Set<String> dict) {
    	if (dict.contains(target)) {
    		return true;
    	}
    	
    	for (int i = startIndex; i < target.length(); i++) {
    		for (int k = i; k < i + maxLen; k++) {
    			String word = target.substring(i, k + 1);
    			if (!map.containsKey(word) || !map.get(word) ) {
    				boolean f1 = wordBreaker(word, k + 1, dict);
    				if (f1) {
    					
    				}
    			}
    		}
    	}
    	return false;
    }
}



public class WordBreak_107 {
	public static void main(String[] args) {
		Set<String> dict = new HashSet<>();
		dict.add("lint");
		dict.add("b");
		String s = "lintlintlint";
//		dict.add("b");
//		dict.add("aa");
//		String s = "aaab";
		System.out.println(new Solution14().wordBreak(s, dict));
	}
}
