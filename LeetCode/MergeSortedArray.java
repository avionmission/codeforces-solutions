import java.util.Arrays;

public class MergeSortedArray {
    // Driver code
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        int m = nums1.length-nums2.length;
        int n = nums2.length;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;  // Last element of nums1
        int j = n - 1;  // Last element of nums2
        int k = m + n - 1;  // Last element of merged array

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }
}
