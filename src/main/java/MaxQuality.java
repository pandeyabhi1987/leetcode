import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxQuality {
    public static void main(String[] args) {
        List<Integer> p1 = Arrays.asList(2, 2, 1, 3, 5, 7, 5, 8, 6);
        List<Integer> p2 = Arrays.asList(89, 48, 14);
        List<Integer> p3 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> p4 = Arrays.asList(2, 2, 1, 5, 3);
        List<Integer> p5 = Arrays.asList(2, 2, 1, 5, 3);

        long max = maxQuality(p4, 2);
        System.out.println(max);
    }

    private static long maxQuality(List<Integer> packets, int channels) {
        if(packets.isEmpty()) return 0;
        if(packets.size() < channels) return 0;
        Collections.sort(packets);
        double max = 0;
        int i = packets.size() - 1;
        while (channels != 1) {
            max = max + packets.get(i);
            channels--;
            i--;
        }
        if (i == 0) {
            max += packets.get(i);
        } else {
            max += (packets.get((i + 1) / 2) + packets.get(i) / 2.0) / 2.0;
        }
        return (long) Math.ceil(max);
    }
}
