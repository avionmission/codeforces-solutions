class MinSizeSubArraySum {
    //Driver Code
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }

    // O(n) Sliding window solution
    public static int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        for(int end=0; end<nums.length; end++) {
            sum+=nums[end];

            while(sum >= target){
                min = Math.min(min, end-start+1);
                sum = sum - nums[start];
                start++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}