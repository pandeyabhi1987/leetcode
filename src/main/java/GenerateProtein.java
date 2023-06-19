import java.util.*;

/**
 * sequences = {
 * 'AC': (5, 15),
 * 'BC': (3, 20),
 * 'PQ': (15, 22),
 * 'XY': (22, 35),
 * 'AB': (20, 32),
 * 'BT': (9, 13)
 * }
 * <p>
 * protein = {
 * 'P1': ('AC', 'PQ')
 * 'P2': ('AC','PQ','XY')
 * 'P3': ('BC', 'AB')
 * 'P4': ('BT', 'AC')
 * }
 * <p>
 * generate_protein => (P1, 5,22), (P2, 5, 35), (P3, 3, 32)
 */
public class GenerateProtein {
    public static void main(String[] args) {
        HashMap<String, List<String>> protein = new HashMap<>() {{
            put("P1", Arrays.asList("AC", "PQ"));
            put("P2", Arrays.asList("AC", "PQ", "XY"));
            put("P3", Arrays.asList("BC", "AB"));
            put("P4", Arrays.asList("BT", "AC"));
        }};
        HashMap<String, List<Integer>> sequences = new HashMap<>() {{
            put("AC", Arrays.asList(5, 15));
            put("BC", Arrays.asList(3, 20));
            put("PQ", Arrays.asList(15, 22));
            put("XY", Arrays.asList(22, 35));
            put("AB", Arrays.asList(20, 32));
            put("BT", Arrays.asList(9, 13));
        }};
        generate(sequences, protein);
    }

    public static void generate(HashMap<String, List<Integer>> sequences, HashMap<String, List<String>> protein) {
        HashMap<String, List<Integer>> result = new HashMap<>();
        for (String currentProtein : protein.keySet()) {
            ArrayList<Integer> ranges = new ArrayList<>();
            for (String seq : protein.get(currentProtein)) {
                ranges.addAll(sequences.get(seq));
            }
            Collections.sort(ranges);
            boolean canFormProtein = true;
            for (int i = 1; i + 2 < ranges.size(); i = i + 2) {
                if (!Objects.equals(ranges.get(i), ranges.get(i + 1))) {
                    canFormProtein = false;
                    break;
                }
            }
            if (canFormProtein) {
                result.put(currentProtein, Arrays.asList(ranges.get(0), ranges.get(ranges.size() - 1)));
            }
        }
        System.out.println(result);
    }
}
