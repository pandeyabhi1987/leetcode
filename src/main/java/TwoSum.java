import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        for (int n : result) {
            System.out.println(n);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> pairMap = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (pairMap.containsKey(nums[i])) {
                result[0] = i;
                result[1] = pairMap.get(nums[i]);
                return result;
            }
            pairMap.put(diff, i);
        }
        return result;
    }

}
