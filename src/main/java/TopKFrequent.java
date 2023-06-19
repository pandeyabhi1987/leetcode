import java.util.*;

public class TopKFrequent {
    public static void main(String[] args) {
        int[] input = {};
    }
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        for(int n : nums){
            map.put(n, map.getOrDefault(n , 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length+1];
        for(int key : map.keySet()){
            int freq = map.get(key);
            if(bucket[freq] == null) bucket[freq] = new LinkedList();
            bucket[freq].add(key);
        }
        List<Integer> result = new ArrayList();
        for(int i = bucket.length -1 ; i > 0 ; i--){
            if(result.size() == k){
                break;
            }
            if(bucket[i] != null)
                result.addAll(bucket[i]);

        }

        int [] res = new int[result.size()];
        for(int j = 0 ; j < result.size(); j++){
            res[j] = result.get(j);
        }
        return res;

    }
}
