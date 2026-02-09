package com.leetcode.p0217_contains_duplicate;

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
    void example1_hasDuplicate(Solution solution) {
        assertTrue(solution.containsDuplicate(new int[]{1, 2, 3, 1}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example2_noDuplicate(Solution solution) {
        assertFalse(solution.containsDuplicate(new int[]{1, 2, 3, 4}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example3_multipleDuplicates(Solution solution) {
        assertTrue(solution.containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }

    // --- Edge cases ---

    @ParameterizedTest
    @MethodSource("solutions")
    void emptyArray(Solution solution) {
        assertFalse(solution.containsDuplicate(new int[]{}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void singleElement(Solution solution) {
        assertFalse(solution.containsDuplicate(new int[]{1}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void twoIdenticalElements(Solution solution) {
        assertTrue(solution.containsDuplicate(new int[]{1, 1}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void twoDistinctElements(Solution solution) {
        assertFalse(solution.containsDuplicate(new int[]{1, 2}));
    }

    // --- All same elements ---

    @ParameterizedTest
    @MethodSource("solutions")
    void allSameElements(Solution solution) {
        assertTrue(solution.containsDuplicate(new int[]{5, 5, 5, 5}));
    }

    // --- Negative numbers ---

    @ParameterizedTest
    @MethodSource("solutions")
    void negativeNumbers_withDuplicate(Solution solution) {
        assertTrue(solution.containsDuplicate(new int[]{-1, -2, -3, -1}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void negativeNumbers_noDuplicate(Solution solution) {
        assertFalse(solution.containsDuplicate(new int[]{-1, -2, -3}));
    }

    // --- Integer extremes ---

    @ParameterizedTest
    @MethodSource("solutions")
    void integerMinAndMaxValues_noDuplicate(Solution solution) {
        assertFalse(solution.containsDuplicate(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void integerMinDuplicate(Solution solution) {
        assertTrue(solution.containsDuplicate(new int[]{Integer.MIN_VALUE, 0, Integer.MIN_VALUE}));
    }

    // --- Duplicate at end ---

    @ParameterizedTest
    @MethodSource("solutions")
    void duplicateOnlyAtEnd(Solution solution) {
        assertTrue(solution.containsDuplicate(new int[]{1, 2, 3, 4, 5, 1}));
    }
}
