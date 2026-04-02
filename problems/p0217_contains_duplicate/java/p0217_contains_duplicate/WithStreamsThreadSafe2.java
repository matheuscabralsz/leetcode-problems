package p0217_contains_duplicate;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class WithStreamsThreadSafe2 implements Solution {
    @Override
    public boolean containsDuplicate(int[] nums) {
        // Parallel-safe with ConcurrentHashMap
        Set<Integer> seen = ConcurrentHashMap.newKeySet();
        AtomicBoolean duplicateFound = new AtomicBoolean(false);
        IntStream.of(nums).parallel().forEach(x -> {
            if (!seen.add(x)) {
                duplicateFound.set(true);
            }
        });
        return duplicateFound.get();
    };
}
