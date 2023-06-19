import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] input = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = new DailyTemperatures().dailyTemperatures(input);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int previousIndex = stack.pop();
                result[previousIndex] = i - previousIndex;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int remainingIndex = stack.pop();
            result[remainingIndex] = 0;
        }
        return result;
    }
}
