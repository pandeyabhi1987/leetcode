import java.util.*;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    /*
    1- The idea is to add ')' only after valid '('
    2- We use two integer variables left & right to see how many '(' & ')' are in the current string
    3- If open < n then we can add '(' to the current string
    4- If close < open then we can add ')' to the current string
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList();
        backtrack(n, 0, 0, "", result);
        return result;
    }

    private static void backtrack(int n, int open, int close, String sb, List<String> result) {
        if (close == n) {
            result.add(sb);
        }
        if (open < n) {
            backtrack(n, open + 1, close, sb + "(", result);
        }
        if (close < open) {
            backtrack(n, open, close + 1, sb + ")", result);
        }
    }
}
