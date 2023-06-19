import java.util.HashMap;
import java.util.Map;

public class MaxPoints {
    public int maxPoints(int[][] points) {
        int ans = 1;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> hm = new HashMap();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    double val = Math.atan2(points[i][0] - points[j][0], points[i][1] - points[j][1]);
                    hm.put(val, hm.getOrDefault(val, 1) + 1);
                    ans = Math.max(ans, hm.get(val));
                }
            }
        }
        return ans;
    }
}
