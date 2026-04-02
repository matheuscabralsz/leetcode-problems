package p0651_four_keys_keyboard;

import java.util.List;
import java.util.Map;

public class DynamicProgramming implements Solution {

    @Override
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 0; i <= n - 3; i++) {
            int min = Math.min(n, i + 6);
            int op = i + 3;
            for (int j = op; j <= min; j++) {
                dp[j] = Math.max(dp[j], (j - i - 1) * dp[i]);
            }
        }
        return dp[n];
    }
}
