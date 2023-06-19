import java.util.Arrays;

public class mahjong {
    public static void main(String[] args) {
        System.out.println(check("55555555"));
    }

    public static boolean check(String pattern) {
        int dups = 1;
        int triples = 0;
        int doubles = 0;
        int i = 0;
        char tempArray[] = pattern.toCharArray();
        Arrays.sort(tempArray);
        String sortedPattern = new String(tempArray);
        while (i + 1 < sortedPattern.length()) {
            if (sortedPattern.charAt(i) == sortedPattern.charAt(i + 1)) {
                dups++;
            } else {
                dups = 1;
                i++;
                if (triples > 0) doubles = 0;
                continue;
            }
            if (dups % 3 == 0) {
                triples++;
                if (dups / 3 == 1) {

                }
                if (dups / 3 == 2) {
                    doubles++;
                }
            }
            if (dups % 2 == 0) {
                doubles++;
            }
            i++;
        }
        if (doubles == 0 || doubles > 1) {
            return false;
        }
        return true;

    }
}
