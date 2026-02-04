import java.util.HashMap;

class Solution {
    public int singleNumber(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int current = nums[i];

            if (map.containsKey(current)) {
                map.remove(current);
            } else {
                map.put(current, 1);
            }
        }

        // Only one key will be left
        for (int key : map.keySet()) {
            return key;
        }

        return -1;
    }
}
