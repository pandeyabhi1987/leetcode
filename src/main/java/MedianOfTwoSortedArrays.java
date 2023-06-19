import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {

    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap =new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        int i =0, j =0;
        while(i < nums1.length && j < nums2.length){

        }

        return 0.0;
    }
}
