import java.util.*;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        HashMap<Character, Integer> s1Map = new HashMap<>();
        for (Character c : s1.toCharArray()) {
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> s2Map = new HashMap<>();

        for (int i = 0; i < s2.length(); i++) {
            Character charToAdd = s2.charAt(i);
            s2Map.put(charToAdd, s2Map.getOrDefault(charToAdd, 0) + 1);

            if (i >= s1.length()) {
                Character charToRemove = s2.charAt(i - s1.length());
                if (s2Map.get(charToRemove) == 1) {
                    s2Map.remove(charToRemove);
                } else {
                    s2Map.put(charToRemove, s2Map.get(charToRemove) - 1);
                }
            }
            if (s1Map.equals(s2Map)) return true;

        }
        return false;
    }

    public static void main(String[] args) {

    }
}
