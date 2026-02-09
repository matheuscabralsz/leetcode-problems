package com.leetcode.p0217_contains_duplicate;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class WithStreamsNotThreadSafe implements Solution {
    @Override
    public boolean containsDuplicate(int[] nums) {
        // The problem in code isn't the AtomicBoolean — it's the HashSet. Even though duplicateFound is thread-safe, HashSet is not.
        Set<Integer> uniqueValues = new HashSet<>();
        AtomicBoolean duplicateFound = new AtomicBoolean(false);
        IntStream.of(nums).forEach(x -> {
            if (uniqueValues.contains(x)) {
                duplicateFound.set(true);
            } else {
                uniqueValues.add(x);
            }
        });
        return duplicateFound.get();
    }
}
