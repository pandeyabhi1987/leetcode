import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatedCharacters {
    public static void main(String[] args) {
        lengthOfLongestSubstring("au");
        lengthOfLongestSubstring("pwwkew");
    }

    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int start = 0, maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            while (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            }
            set.add(s.charAt(end));
            maxLength = Math.max(maxLength, end - start + 1);
        }
        System.out.println(maxLength);
        return maxLength;
    }
}
