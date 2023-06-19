import java.util.Scanner;

import java.util.*;

public class Rubrik {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        CustomHashMap map = new CustomHashMap();
        List<String> result = new ArrayList<>();
        int num = scanner.nextInt();
        for (int i = 0; i <= num; i++) {
            List<String> input = Arrays.asList(scanner.nextLine().split(" ", 0));
            if (input.get(0).equals("A")) {
                map.assign(input.get(1), Integer.parseInt(input.get(2)));
            }
            if (input.get(0).equals("D")) {
                map.delete(input.get(1));
            }
            if (input.get(0).equals("M")) {
                result.add(String.valueOf(map.MaxScore()));
            }
            if (input.get(0).equals("G")) {
                result.add(String.valueOf(map.getScore(input.get(1))));
            }
        }

        for (var res : result) {
            System.out.println(res);
        }
    }

    public static class CustomHashMap {
        PriorityQueue<Entry> queue
                = new PriorityQueue<Entry>(Comparator.comparing(Entry::getValue)
                .thenComparing(Entry::getKey));

        private static class Entry {
            private final String key;
            private int value;

            public String getKey() {
                return key;
            }

            public int getValue() {
                return value;
            }

            public Entry(String key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final LinkedList<Entry>[] entries = new LinkedList[5];

        public void assign(String key, int value) {
            int index = hash(key);
            if (entries[index] == null) {
                entries[index] = new LinkedList<>();
            }
            LinkedList<Entry> bucket = entries[index];
            for (var entry : bucket) {
                if (key.equals(entry.key)) {
                    entry.value = value;
                    queue.add(entry);
                    return;
                }
            }
            Entry e = new Entry(key, value);
            bucket.addLast(e);
            queue.add(e);
        }

        public int getScore(String key) {
            int index = hash(key);
            if (entries[index] != null) {
                for (Entry entry : entries[index]) {
                    if (key.equals(entry.key)) {
                        return entry.value;
                    }
                }
            }
            return 0;
        }

        public void delete(String key) {
            int index = hash(key);
            if (entries[index] == null)
                throw new IllegalStateException();
            for (var entry : entries[index]) {
                if (entry.key.equals(key)) {
                    entries[index].remove(entry);
                    queue.remove(entry);
                    return;
                }
            }
            throw new IllegalStateException();
        }

        public String MaxScore() {
            Entry e = queue.peek();
            return e.key + " " + e.value;
        }

        private int hash(String key) {
            return (key.hashCode() % entries.length);
        }
    }
}
