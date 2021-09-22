/**
 * Status: Accepted
 * 37 / 37 test cases passed.
 * Runtime: 1 ms
 * Memory Usage: 56.4 MB
 * Details:
 * - Your runtime beats 68.89 % of java submissions.
 * - Your memory usage beats 50.23 % of java submissions.
 */

class Solution {
    public void rotate(int[] nums, int k) {
        int[] numsAux = nums.clone();
        for(int i=0; i < nums.length; i++){
            if(i+k < nums.length){
                nums[i+k] = numsAux[i];
            } else {
                int j = i+k;
                while(j >= nums.length){
                    j = j - nums.length;
                }
                nums[j] = numsAux[i];
            }
        }
    }
}