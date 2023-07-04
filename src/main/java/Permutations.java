import java.util.*;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        permute(nums, 0, result, new ArrayList());
        System.out.println(result);
        return result;
    }

    private void permute(int[] nums, int index, List<List<Integer>> result, ArrayList list) {
        if(index == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < nums.length ; i++) {
            swap(nums, index, i);
            list.add(nums[index]);
            permute(nums, index+1, result, list);
            list.remove(list.size()-1);
            swap(nums, index, i);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Permutations p = new Permutations();
        int[] input = {1, 2, 3};
        p.permute(input);
    }

}
