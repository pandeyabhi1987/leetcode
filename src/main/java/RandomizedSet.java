import java.util.*;

class RandomizedSet {
    List<Integer> li;
    HashMap<Integer, Integer> hm;
    Random rand;

    public RandomizedSet() {
        li = new ArrayList<>();
        hm = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (!hm.containsKey(val)) {
            li.add(val);
            hm.put(val, li.size());
            return true;
        }
        return false;

    }

    public boolean remove(int val) {
        if (!hm.containsKey(val)) {
            return false;
        }
        int idx = hm.get(val);
        int lastIdx = li.size() - 1;
        li.set(idx, li.get(lastIdx));
        li.remove(li.size() - 1);
        hm.remove(val);
        return true;
    }

    public int getRandom() {
        int randomNumber = rand.nextInt(li.size());
        return li.get(randomNumber);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */