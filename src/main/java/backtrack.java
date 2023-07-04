import java.util.*;

public class backtrack {
    /*
    Given an integer array nums of unique elements, return all possible
    subsets (the power set).
    The solution set must not contain duplicate subsets. Return the solution in any order.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets(0, nums, new ArrayList<>(), subsets);
        return subsets;
    }

    private void subsets(int index, int[] nums, List<Integer> subset, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(subset));
        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            subsets(i + 1, nums, subset, subsets);
            subset.remove(subset.size() - 1);
        }
    }

    /*
    Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
    The solution set must not contain duplicate subsets. Return the solution in any order.
    Input: nums = [1,2,2]
    Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
    */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsetsWithDup = new ArrayList<>();
        subsetsWithDup(0, nums, new ArrayList<>(), subsetsWithDup);
        return subsetsWithDup;

    }

    private void subsetsWithDup(int index, int[] nums, ArrayList<Integer> subsetWithDup, List<List<Integer>> subsetsWithDup) {
        subsetsWithDup.add(new ArrayList<>(subsetWithDup));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i-1]) continue;
            subsetWithDup.add(nums[i]);
            subsetsWithDup(i + 1, nums, subsetWithDup, subsetsWithDup);
            subsetWithDup.remove(subsetWithDup.size() - 1);
        }
    }

    /*
    Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
    You may return the answer in any order.
    Input: n = 4, k = 2
    Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    Explanation: There are 4 choose 2 = 6 total combinations.
    Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
     */
    public List<List<Integer>> combination(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        combination(1, n, k, new ArrayList<>(), combinations);
        return combinations;
    }

    private <E> void combination(int firstNum, int n, int k, ArrayList<Integer> combination, List<List<Integer>> combinations) {
        if (combination.size() == k) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        for (int i = firstNum; i <= n; i++) {
            combination.add(i);
            combination(i + 1, n, k, combination, combinations);
            combination.remove(combination.size() - 1);
        }
    }

    /*
    Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
    The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
    frequency of at least one of the chosen numbers is different.
    The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
    Input: candidates = [2,3,6,7], target = 7
    Output: [[2,2,3],[7]]
    Explanation:
    2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
    7 is a candidate, and 7 = 7.
    These are the only two combinations.
    */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> combinationSum = new ArrayList<>();
        combinationSum(0, candidates, target, new ArrayList<>(), combinationSum);
        return combinationSum;
    }

    private void combinationSum(int index, int[] candidates, int target, ArrayList<Integer> combination, List<List<Integer>> combinationSum) {
        if (target == 0) {
            combinationSum.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) break;
            combination.add(candidates[i]);
            combinationSum(i, candidates, target - candidates[i], combination, combinationSum);
            combination.remove(combination.size() - 1);
        }
    }

    /*
    Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
    Each number in candidates may only be used once in the combination.
    Note: The solution set must not contain duplicate combinations.
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> combinationSum = new ArrayList<>();
        combinationSum2(0, candidates, target, new ArrayList<>(), combinationSum);
        return combinationSum;
    }

    private <E> void combinationSum2(int index, int[] candidates, int target, ArrayList<E> combination, List<List<Integer>> combinationSum) {
        if(target == 0) {

        }
    }

}
