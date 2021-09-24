/**
 * Status: Accepted
 * 362 / 362 test cases passed
 * Runtime: 99 ms
 * Memory Usage: 39.2 MB
 * Details:
 * - Your runtime is faster than 9.19% of Java online submissions for Two Sum.
 * - Your memory usage are less than 69.54% of Java online submissions for Two Sum.
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] output = new int[2];
        
        for(int i = 0; i < nums.length; i ++){
            for(int j = 0; j < nums.length; j++){
                if(i != j && nums[i] + nums[j] == target){
                    output[0] = i;
                    output[1] = j;
                    return output;
                }
            }
        }
        
        return null;
    }
}