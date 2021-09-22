/**
 * Status: Accepted
 * 362 / 362 test cases passed
 * Runtime: 10 ms
 * Memory Usage: 44.9 MB
 * Details:
 * - Your runtime beats 6.67 % of java submissions
 * - Your memory usage beats 15.20 % of java submissions
 */

class Solution {
    public int removeDuplicates(int[] nums) {
    int newNumsSize = nums.length;
    int aux, i;
    for(i=nums.length-1; i > 0; i--){
        if(nums[i] == nums[i-1]){
            nums[i] = -101;
            int j = i;
            while(j < nums.length-1 && nums[j+1] != -101){
                aux = nums[j+1];
                nums[j+1] = nums[j];
                nums[j] = aux;
                j++;
            }
            newNumsSize--;
        }
    }
    return newNumsSize;
    }
}