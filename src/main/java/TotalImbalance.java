import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TotalImbalance {
    public static void main(String[] args) {
        List<Integer> sample = Arrays.asList(4, 3, 2, 5, 6);
        System.out.println(getTotalImbalance(sample));
    }

    public static long getTotalImbalance(List<Integer> weights) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        int total = 0;
        for (int i = 0; i < weights.size(); i++) {
            int k = 0;
            for (int j = 0; j < weights.size(); j++) {
                max.add(weights.get(j));
                min.add(weights.get(j));
                if (j - k + 1 >= i + 1) {
                    total += max.peek() - min.peek();
                    max.remove(weights.get(k));
                    min.remove(weights.get(k));
                    k++;
                }
            }
        }
        return total;
    }
}
