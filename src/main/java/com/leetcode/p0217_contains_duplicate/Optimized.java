package com.leetcode.p0217_contains_duplicate;

import java.util.HashSet;
import java.util.Set;

public class Optimized implements Solution {
    @Override
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) return true;
        }
        return false;
    };
}
