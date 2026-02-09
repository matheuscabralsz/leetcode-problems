package com.leetcode.p0219_contains_duplicate_ii;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class WithStreams implements Solution {

    @Override
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        return IntStream.range(0, nums.length).anyMatch(i -> {
            boolean found = map.containsKey(nums[i]);
            if (found) {
                int distance = i - map.get(nums[i]);
                if (distance <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
            return false;
        });
    }
}
