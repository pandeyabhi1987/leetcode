import java.util.*;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] input = {1, 3, -1, -3, 5, 3, 6, 7};
        new SlidingWindowMaximum().maxSlidingWindow(input, 3);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> resultList = new ArrayList<Integer>();
        Deque<Integer> deque = new ArrayDeque<>();
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            while (!deque.isEmpty() && deque.peekFirst() <= windowEnd-k) {
                deque.pollFirst();
            }
            deque.add(nums[windowEnd]);
            if (deque.getFirst() > nums[windowStart]) {
                deque.removeFirst();
            }
            if (windowEnd + 1 >= k) {
                resultList.add(deque.getFirst());
                windowStart++;
            }
        }
        System.out.println(resultList);
        return resultList.stream().mapToInt(i -> i).toArray();
    }
}
