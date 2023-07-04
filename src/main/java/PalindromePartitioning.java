import java.util.*;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        partitionString(0, s, res, new ArrayList<>());
        return res;
    }

    private void partitionString(int index, String s, List<List<String>> partitions, ArrayList<String> partition) {
        //If the current index is equal to the length of the string, add the current partition to the list of partitions.
        if(index >= s.length()){
            partitions.add(new ArrayList<>(partition));
            return;
        }
        for (int i = index; i < s.length() ; i++) {
            //If the substring from the current index to the current iteration index is a palindrome, add it to the current partition.
            if(isPalindrome(s, index, i)){
                String palindrome = s.substring(index, i + 1); // Get the current palindrome substring
                partition.add(palindrome); // Add the current palindrome to the current partition
                partitionString(i+1, s, partitions, partition); // Recursively partition the remaining string
                partition.remove(partition.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l<r){
            if(s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

}
