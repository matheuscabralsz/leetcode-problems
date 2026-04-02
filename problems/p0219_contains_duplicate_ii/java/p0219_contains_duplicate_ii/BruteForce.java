package p0219_contains_duplicate_ii;

public class BruteForce implements Solution {

    @Override
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int i = 0;
        while (i < nums.length) {
            int j = i + 1;
            while (j < nums.length) {
                if (nums[i] == nums[j]) {
                    int distance = j - i;
                    if (distance <= k) return true;
                }
                j++;
            }
            i++;
        }
        return false;
    }
}
