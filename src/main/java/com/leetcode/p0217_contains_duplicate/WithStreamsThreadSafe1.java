package com.leetcode.p0217_contains_duplicate;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class WithStreamsThreadSafe1 implements Solution {
    @Override
    public boolean containsDuplicate(int[] nums) {
        // Safe — sequential stream, no concurrency concerns
        Set<Integer> seen = new HashSet<>();
        return IntStream.of(nums).anyMatch(n -> !seen.add(n));
    };
}
