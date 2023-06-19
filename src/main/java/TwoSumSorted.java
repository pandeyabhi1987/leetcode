
public class TwoSumSorted {
    public static void main(String[] args) {
        int[] nums = {3, 24, 50, 79, 88, 150, 345};
        int target = 200;
        int[] result = twoSumSortedTwoPointers(nums, target);
        for (int n : result) {
            System.out.print(n);
        }
    }

    private static int[] twoSumSortedTwoPointers(int[] nums, int target) {
        int[] result = new int[2];
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int diff = target - nums[start];
            if (nums[end] == diff) {
                // done
                result[0] = start + 1;
                result[1] = end + 1;
                return result;
            }
            if (diff < nums[end]) {
                end--;
            } else start++;
        }
        return result;
    }

    /*
    O(nlogn) solution, helpful in three sum problem.
     */
    private static int[] twoSumSortedBinarySearch(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            int index = binarySearchIndex(nums, diff, i + 1);
            if (index != -1) {
                result[0] = i + 1;
                result[1] = index + 1;
                return result;
            }
        }
        return result;
    }

    private static int binarySearchIndex(int[] nums, int target, int startIndex) {
        if (startIndex >= nums.length) {
            return -1;
        }
        int end = nums.length - 1;
        while (startIndex <= end) {
            int middle = (startIndex + end) / 2;
            if (target == nums[middle]) return middle;
            if (target < nums[middle]) end = middle;
            else startIndex = middle + 1;
        }
        return -1;
    }
}
