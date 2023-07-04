import java.util.*;

public class LetterCombinationsPhoneNumber {
    private Map<Character, String> letters = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
    private List<String> combinations = new ArrayList<>();
    private String phoneDigits;

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return combinations;
        }
        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;

    }

    void backtrack(int index, StringBuilder path) {
        // If the path is the same length as digits, we have a complete combination
        if(path.length() == phoneDigits.length()){
            combinations.add(path.toString());
            return; // Backtrack
        }
        for (Character c : letters.get(phoneDigits.charAt(index)).toCharArray()) {
            path.append(c);
            backtrack(index+1, path);
            path.deleteCharAt(path.length()-1);
        }
    }
}
