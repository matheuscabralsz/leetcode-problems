package com.leetcode.p0217_contains_duplicate;

import java.util.stream.IntStream;

public class WithStreamsPureFunctional implements Solution {
    @Override
    public boolean containsDuplicate(int[] nums) {
        return IntStream.of(nums).distinct().count() != nums.length;
    };
}
