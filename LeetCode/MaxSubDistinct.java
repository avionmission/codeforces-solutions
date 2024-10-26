import java.util.HashMap;
import java.util.Map;

public class MaxSubDistinct {

    public static void main(String[] args) {
        int[] nums = {1,5,4,2,9,9,9};
        int k = 3; // correct output: 15
        System.out.println(bruteSolve(nums, k));
        System.out.println(maximumSubarraySum(nums, k));
    }

    // Optimized approach
    static long maximumSubarraySum(int[] nums, int k) {
        long max = 0;
        long sum = 0;
        int distincts = 0;

        Map<Integer, Integer> seen = new HashMap<>();
        int start = 0;

        for(int i=0; i<nums.length; i++) {
            // Add current element to window sum
            sum += nums[i];

            // if this number wasn't seen in current window
            if(seen.getOrDefault(nums[i],-1) < start)
                distincts++;

            // Update the last seen position of current num
            seen.put(nums[i], i);

            // if window size becomes > k, shrink it form start
            if(i - start + 1 > k) {
                // remove first element from start
                sum -= nums[start];

                // if the element we are removing is the last occurrence of that number in our window, decrease distinct count
                if(seen.getOrDefault(nums[start], -1) == start) {
                    distincts--;
                }
                start++;
            }

            // if we have exactly k distinct elements in window
            if(distincts == k && i - start + 1 == k)
                max = Math.max(sum, max);
        }
        return max;
    }

    // Brute force approach
    static long bruteSolve(int[] nums, int k){
        long max_sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length-k+1;i++){
            long curr_sum = 0;
            for(int j=i;j<i+k;j++){
                if(!map.containsKey(nums[j])){
                    curr_sum += nums[j];
                    map.put(nums[j], j);
                } else {
                    curr_sum = 0;
                    break;
                }
            }
            map.clear();
            max_sum = Math.max(curr_sum, max_sum);
        }
        return max_sum;
    }

}
