package p0651_four_keys_keyboard;

import java.util.Map;

public class DynamicProgrammingMemo implements Solution {

    @Override
    public int maxA(int n, Map<Integer, Integer> memo) {
        if (n <= 6) return n;

        if (memo.containsKey(n)) return memo.get(n);

        int globalMax = n;

        for (int i = n - 3; i >= 1; i--) {
            int multiplier = n - i - 1;
            int max = maxA(i, memo) * multiplier;

            if (max > globalMax) globalMax = max;
        }

        memo.put(n, globalMax);
        return globalMax;
    }
}
