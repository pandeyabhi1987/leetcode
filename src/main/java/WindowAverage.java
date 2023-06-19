
import java.util.*;

/**
 * Created by tanvi on 5/15/22.
 */
public class WindowAverage {
    Map<String, Node> mainMap = new HashMap<>();
    TreeMap<Long, List<Node>> timeNodeMap = new TreeMap<>(); // Time - List of Node where all nodes expire this time
    final Long TTL = 500L;
    //O(log n)
    public void put(String key, int val) {
        long timeToExpire = System.currentTimeMillis() + TTL; // this is the time when this key value expire
        Node n = new Node(key, val, timeToExpire);
        // remove the node from the timeMap if already exist
        if (mainMap.containsKey(key)) {
            Node node = mainMap.get(key);
            long nodeTime = node.time;
            if (timeNodeMap.containsKey(nodeTime)) {
                List<Node> nodes = timeNodeMap.get(nodeTime);
                //remove existing old node that conflicts with same key
                nodes.remove(node);
                // extra hai load mat lo.. bad mein dekhna
                if (nodes.isEmpty()) {
                    timeNodeMap.remove(nodeTime);
                }
            }
        }
        List<Node> nodes = new ArrayList<>();
        if (timeNodeMap.containsKey(timeToExpire)) {
            nodes = timeNodeMap.get(timeToExpire);
        }
        nodes.add(n);
        mainMap.put(key, n);
        timeNodeMap.put(timeToExpire, nodes);
    }
    public int get(String key) {
        long t = System.currentTimeMillis();
        //remove all the key before t
        removeExpiredKeyValue(t);
        //check key exist or not
        if (mainMap.containsKey(key)) {
            return mainMap.get(key).val;
        }
       return -1;
    }
    //O(n)
    public long average() {
        long t = System.currentTimeMillis();
        //remove all the key before t
        removeExpiredKeyValue(t);
        //calculate avg
        long sum = 0;
        long avg = 0;
        for (Map.Entry<String, Node> entry : mainMap.entrySet()) {
            sum += entry.getValue().val;
        }
        if (mainMap.size() > 0) {
            avg = sum / mainMap.size();
        }
        return avg;
    }

    //O(n*m)
    public void removeExpiredKeyValue(long t) {
        Map<Long, List<Node>> subMap = timeNodeMap.headMap(t); // can I confirm
        for (Map.Entry<Long, List<Node>> entry : subMap.entrySet()) {
            List<Node> nodes = entry.getValue();
            for (Node n : nodes) {
                mainMap.remove(n.key);
            }
        }
        // remove all old entries from treemap
        subMap.clear();
    }
    class Node {
        String key;
        int val;
        long time;
        Node(String key, int val, long time) {
            this.key = key;
            this.val = val;
            this.time = time;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        WindowAverage wa = new WindowAverage();
        wa.put("foo", 100);
        Thread.sleep(100L);
        wa.put("foo", 200);
        Thread.sleep(100L);
        wa.put("bar", 300);
        Thread.sleep(100L);
        wa.put("bar", 400);
        Thread.sleep(100L);
        wa.put("pqr", 500);
        Thread.sleep(100L);
        wa.put("foo", 700);
        Thread.sleep(100L);
        wa.put("bar", 1300);
        Thread.sleep(100L);
        while (true) {
            System.out.println("foo value : " + wa.get("foo"));
            System.out.println("bar value : " + wa.get("bar"));
            System.out.println("pqr value : " + wa.get("pqr"));
            System.out.println("Average : " + wa.average());
            Thread.sleep(100L);
        }
    }
}