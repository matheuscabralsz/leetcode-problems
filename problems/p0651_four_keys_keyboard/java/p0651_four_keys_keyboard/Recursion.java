package p0651_four_keys_keyboard;

public class Recursion implements Solution {
    @Override
    public int maxA(int n) {

        if (n <= 6) return n;

        int i = n - 3;
        int globalMax = n;

        while (i >= 1) {
            int multiplier = n - i - 1;
            int defaultMax = maxA(i);
            int max = defaultMax * multiplier;

            if (max > globalMax) globalMax = max;

            i--;
        }

        return globalMax;
    }
}
