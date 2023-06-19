import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> result = threeSum(nums);
        System.out.println(result);
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int target = -nums[i];
            searchPairs(nums, target, i, results);
        }
        return results;
    }

    private static void searchPairs(int[] nums, int target, int i, List<List<Integer>> results) {
        int start = i + 1;
        int end = nums.length - 1;
        HashSet<Integer> seen = new HashSet<Integer>();

        while (start < end) {
            int diff = target - nums[start];
            if(seen.contains(diff)){
                continue;
            }
            if (diff == nums[end]) {
                results.add(Arrays.asList(nums[i], nums[start], nums[end]));
                seen.add(diff);
            }
            if (diff < nums[end]) end--;
            else start++;
        }

    }
}
