import java.util.HashMap;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC");
    }

    public String minWindow(String s, String t) {
        String result = "";
        if (s.length() == 0 || t.length() == 0) return result;

        //Two hashmaps are used to store the character count.
        HashMap<Character, Integer> tmap = new HashMap<>();
        HashMap<Character, Integer> smap = new HashMap<>();

        for (Character c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }
        //We will use two variables 'have' & 'need' to keep a track whether the characters
        //we need have been encountered.
        int need = t.length();
        int have = 0;
        int windowStart = 0;
        int minLength = Integer.MAX_VALUE;
        ;
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char ch = s.charAt(windowEnd);
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);
            //if we have encountered a useful character, we will increment have variable.
            if (tmap.containsKey(ch) && smap.get(ch) <= tmap.get(ch)) {
                have++;
            }
            //if have is equals to the need, means we got a substring in s having all the character of t
            while (have == need) {
                //check if its the mimimum substring till now
                if (minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1;
                    result = s.substring(windowStart, windowEnd + 1);
                }
                //now we will check, can we do better
                //is there a shorter substring
                Character charToRemove = s.charAt(windowStart);
                if (smap.get(charToRemove) == 1) {
                    smap.remove(charToRemove);
                } else {
                    smap.put(charToRemove, smap.get(charToRemove) - 1);
                }
                windowStart++;
                //if we remove a useful char, decrementing have varaible
                if (tmap.containsKey(charToRemove) && smap.getOrDefault(charToRemove, 0) < tmap.get(charToRemove)) {
                    have--;
                }
            }
        }
        System.out.println(result);
        return result;
    }
}
