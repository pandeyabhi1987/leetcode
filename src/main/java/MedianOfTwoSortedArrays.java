public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];

        int i = 0, j = 0, k = 0;
        //Combine the two arrays nums1 and nums2 into a single sorted array.
        //You can use an additional array or modify one of the existing arrays.
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            merged[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            merged[k++] = nums2[j++];
        }
        //Check if the length is even or odd
        int mergedLength = merged.length;
        //If the length is even, calculate the average of the middle two elements.
        if (mergedLength % 2 == 0) {
            return (merged[mergedLength / 2] + merged[mergedLength / 2 - 1]) / 2.0;
        } else {
            //If the length is odd, return the middle element.
            return merged[mergedLength / 2];
        }

    }
}
