import java.util.*;

public class PrefixPairs {
    public int prefixPairs( String[] strings) {
        TriNode root = new TriNode();
        Arrays.sort(strings);
        int res = 0;
        for (String string : strings) {
            int count = helper(root, string);

            res += count;

        }
        return res;
    }

    public int helper(TriNode root, String input) {
        TriNode current = root;
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            int index = input.charAt(i) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TriNode();
            }
            current = current.children[index];
            if (current.end) {
                count += current.count;
            }
        }
        current.end = true;
        current.count++;
        return count;

    }

    class TriNode {
        int count;
        TriNode[] children = new TriNode[26];
        boolean end;
    }

    public static void main(String[] args) {
        String[] arr = {"back", "backdoor", "gammon", "backgammon"};
        List<String> input = Arrays.asList("back", "backdoor", "gammon", "backgammon" );
        PrefixPairs p = new PrefixPairs();
        System.out.println(p.prefixPairs(arr));

    }
}
