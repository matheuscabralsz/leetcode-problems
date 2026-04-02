package p0651_four_keys_keyboard;

import java.util.HashMap;
import java.util.Map;

/**
 * 651. 4 Keys Keyboard
 *
 * Imagine you have a special keyboard with the following four keys:
 * Key 1: (A) - Print one 'A' on screen.
 * Key 2: (Ctrl-A) - Select the whole screen.
 * Key 3: (Ctrl-C) - Copy selection to buffer.
 * Key 4: (Ctrl-V) - Print buffer on screen appending it after what has already been printed.
 *
 * Now, you can only press the keyboard for N times (with the above four keys),
 * find out the maximum numbers of 'A' you can print on screen.
 *
 * Constraints:
 * 1 <= n <= 50
 */
public interface Solution {
    default int maxA(int n) {
        return maxA(n, new HashMap<>());
    }

    default int maxA(int n, Map<Integer, Integer> memo) {
        return maxA(n);
    }
}
