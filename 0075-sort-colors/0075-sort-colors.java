class Solution {
    public void sortColors(int[] nums) {
        int low = 0;              // boundary for 0s
        int mid = 0;              // current index
        int high = nums.length - 1; // boundary for 2s
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                // swap nums[low] and nums[mid]
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++; // leave 1s in the middle
            } else { // nums[mid] == 2
                // swap nums[mid] and nums[high]
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
                // don't increment mid here, because swapped value needs checking
            }
        }
    }
}
