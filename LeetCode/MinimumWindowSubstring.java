import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    // driver code
    public static void main(String[] args) {
        System.out.println(optiMinWindow("ADOBECODEBANC", "ABC")); // expected output: BANC
    }

    // solution code
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        // Create frequency map for string t
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int required = targetMap.size(); // Number of unique chars needed
        int matched = 0;  // Number of unique chars matched with required frequency
        
        // Create frequency map for current window
        Map<Character, Integer> windowMap = new HashMap<>();
        
        // Variables for window boundaries and result
        int start = 0, end = 0;
        int[] result = {-1, 0, 0}; // {window length, left, right}
        
        while (end < s.length()) {
            // Add character to window
            char c = s.charAt(end);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            
            // Check if this character completes a required frequency
            if (targetMap.containsKey(c) && windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                matched++;
            }
            
            // Try to minimize window by removing characters from start
            while (start <= end && matched == required) {
                c = s.charAt(start);
                
                // Update result if current window is smaller
                if (result[0] == -1 || end - start + 1 < result[0]) {
                    result[0] = end - start + 1;
                    result[1] = start;
                    result[2] = end;
                }
                
                // Remove character from window
                windowMap.put(c, windowMap.get(c) - 1);
                if (targetMap.containsKey(c) && windowMap.get(c).intValue() < targetMap.get(c).intValue()) {
                    matched--;
                }
                start++;
            }
            end++;
        }
        
        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }

    // optmized solution using Array instead of HashMap
    public static String optiMinWindow(String s, String t) {
        int tElements[] = new int[123]; //to track a-z & A-Z in unicode (65-90 and 97-122)
        for(char c:t.toCharArray()) {
            tElements[c]++;
        }

        char[] S = s.toCharArray();
        int minLength = Integer.MAX_VALUE, start = 0;
        int find = t.length(); // no. of letters to be found

        int left = 0, right  = 0; // sliding window
        while(right < S.length) {
            if(tElements[S[right]] > 0) find--;

            tElements[S[right]]--;
            right++;

            // sliding left pointer when all required characters are found
            while(find == 0) {
                if(right - left < minLength){
                    start = left;
                    minLength = right-left;
                }
                if(tElements[S[left]] == 0) find++;
                tElements[S[left]]++;
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start+minLength);
    }
}
