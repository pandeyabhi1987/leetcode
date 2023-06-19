import java.util.*;

/**
 * You are given several logs, where each log contains a unique ID and timestamp.
 * Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second,
 * for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
 * <p>
 * Implement the LogSystem class:
 * <p>
 * LogSystem() Initializes the LogSystem object.
 * void put(int id, string timestamp) Stores the given log (id, timestamp) in your storage system.
 * int[] retrieve(string start, string end, string granularity)
 * Returns the IDs of the logs whose timestamps are within the range from start to end inclusive.
 * start and end all have the same format as timestamp, and granularity means how precise the range should be
 * (i.e. to the exact Day, Minute, etc.). For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59",
 * and granularity = "Day" means that we need to find the logs within the inclusive range from
 * Jan. 1st 2017 to Jan. 2nd 2017, and the Hour, Minute, and Second for each log entry can be ignored.
 */
class LogSystem {
    private String min, max;
    private TreeMap<String, LinkedList<Integer>> logs;
    private HashMap<String, Integer> indexMap;

    public LogSystem() {
        min = "2000:01:01:00:00:00";
        max = "2017:12:31:23:59:59";
        logs = new TreeMap<>();
        indexMap = new HashMap<>();
        indexMap.put("Year", 4);
        indexMap.put("Month", 7);
        indexMap.put("Day", 10);
        indexMap.put("Hour", 13);
        indexMap.put("Minute", 16);
        indexMap.put("Second", 19);
    }

    public void put(int id, String timestamp) {
        if (!logs.containsKey(timestamp)) logs.put(timestamp, new LinkedList<>());
        logs.get(timestamp).add(id);
    }

    public List<Integer> retrieve(String s, String e, String granularity) {
        int index = indexMap.get(granularity);
        String start = s.substring(0, index) + min.substring(index);
        String end = e.substring(0, index) + max.substring(index);
        NavigableMap<String, LinkedList<Integer>> sub = logs.subMap(start, true, end, true);
        List<Integer> results = new ArrayList<>();
        for (Map.Entry<String, LinkedList<Integer>> entry : sub.entrySet()) {
            results.addAll(entry.getValue());
        }
        return results;
    }

    public static void main(String[] args) {
        LogSystem logSystem = new LogSystem();
        logSystem.put(1, "2017:01:01:23:59:59");
        logSystem.put(2, "2017:01:01:22:59:59");
        logSystem.put(3, "2016:01:01:00:00:00");
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"));
        System.out.println(logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"));
    }
}
