import java.util.HashMap;

public class SubArraySum {
    // Driver Code
    public static void main(String[] args) {
        System.out.println(optiSubArraySum(new int[]{1,1,1,2,1,1}, 2)); // 4
    }

    // O(n^2) solution
    static int subarraySum(int[] nums, int k) {
        int end = 0;
        int sum = 0;
        int count = 0;

        for(int start=0; start<nums.length; start++) {
            sum =0;
            end = start;
            while(end < nums.length){
                sum += nums[end];
                if(sum == k) {
                    count++;
                }
                end++;
            }
        }

        return count;
    }

    // O(n) solution using presum and hashmap
    static int optiSubArraySum(int[] nums, int k) {
        // edge case
        if(nums.length == 0) return 0;

        // subarray sum[L..R] = pref[R] - pref[L-1]
        int res = 0;
        int pref = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        for(int i=0; i<nums.length;i++) {
            pref += nums[i];
            int need = pref - k;
            res += map.getOrDefault(need, 0);
            map.put(pref, map.getOrDefault(pref, 0)+1);
        }
        return res;
    }
}
