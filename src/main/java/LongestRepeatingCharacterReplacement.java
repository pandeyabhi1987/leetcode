import java.util.Collections;
import java.util.HashMap;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        new LongestRepeatingCharacterReplacement().characterReplacement("AABABBA", 1);
        new LongestRepeatingCharacterReplacement().characterReplacement("ABAB", 2);
    }

    public int characterReplacement(String s, int k) {
        int left = 0;
        int maxLength = 0;
        HashMap<Character, Integer> characterCount = new HashMap<>();
        int maxFreq = 0;
        for (int right = 0; right < s.length(); right++) {
            characterCount.put(s.charAt(right), characterCount.getOrDefault(s.charAt(right), 0) + 1);
            maxFreq = Math.max(maxFreq, characterCount.get(s.charAt(right)));
            while (right - left + 1 - maxFreq > k) {
                characterCount.put(s.charAt(left), characterCount.get(s.charAt(left)) - 1);
                left++;
            }
            maxLength = Math.max(right - left + 1, maxLength);
        }
        System.out.println(maxLength);
        return maxLength;
    }

}
