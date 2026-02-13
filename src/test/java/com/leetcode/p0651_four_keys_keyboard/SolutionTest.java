package com.leetcode.p0651_four_keys_keyboard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    static Stream<Solution> solutions() {
        return Stream.of(
                new Default()
        );
    }

    // --- Provided examples ---

    @ParameterizedTest
    @MethodSource("solutions")
    void example1_nEquals3(Solution solution) {
        // A, A, A => 3
        assertEquals(3, solution.maxA(3));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example2_nEquals7(Solution solution) {
        // A, A, A, Ctrl-A, Ctrl-C, Ctrl-V, Ctrl-V => 9
        assertEquals(9, solution.maxA(7));
    }

    // --- Small values ---

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals1_singleKeystroke(Solution solution) {
        assertEquals(1, solution.maxA(1));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals2(Solution solution) {
        assertEquals(2, solution.maxA(2));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals4(Solution solution) {
        // A, A, A, A => 4 (no benefit from copy-paste yet)
        assertEquals(4, solution.maxA(4));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals5(Solution solution) {
        // A, A, A, A, A => 5 (copy-paste doesn't help at n=5)
        assertEquals(5, solution.maxA(5));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals6(Solution solution) {
        // A, A, A, Ctrl-A, Ctrl-C, Ctrl-V => 6
        assertEquals(6, solution.maxA(6));
    }

    // --- Medium values ---

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals8(Solution solution) {
        assertEquals(12, solution.maxA(8));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals9(Solution solution) {
        assertEquals(16, solution.maxA(9));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals10(Solution solution) {
        assertEquals(20, solution.maxA(10));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals11(Solution solution) {
        assertEquals(27, solution.maxA(11));
    }

    // --- Larger values ---

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals15(Solution solution) {
        assertEquals(81, solution.maxA(15));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals20(Solution solution) {
        assertEquals(324, solution.maxA(20));
    }

    // --- Upper bound ---

    @ParameterizedTest
    @MethodSource("solutions")
    void nEquals50_upperBound(Solution solution) {
        assertEquals(1327104, solution.maxA(50));
    }
}
