package p0219_contains_duplicate_ii;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class _SolutionTest {

    static Stream<Solution> solutions() {
        return Stream.of(
                new BruteForce(),
                new OptimalHashMap(),
                new WithStreams()
        );
    }

    // --- Provided examples ---

    @ParameterizedTest
    @MethodSource("solutions")
    void example1_duplicateWithinRange(Solution solution) {
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example2_adjacentDuplicates(Solution solution) {
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example3_duplicateOutOfRange(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }

    // --- Edge cases ---

    @ParameterizedTest
    @MethodSource("solutions")
    void emptyArray(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(new int[]{}, 0));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void singleElement(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(new int[]{1}, 1));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void twoIdenticalElements_kEquals1(Solution solution) {
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 1}, 1));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void twoDistinctElements(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 2}, 1));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void kIsZero_noDuplicatePossible(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 1, 1}, 0));
    }

    // --- Boundary of k ---

    @ParameterizedTest
    @MethodSource("solutions")
    void duplicateExactlyAtDistanceK(Solution solution) {
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 4, 1}, 4));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void duplicateOneMoreThanK(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 4, 1}, 3));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void kLargerThanArrayLength(Solution solution) {
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 2, 1}, 100));
    }

    // --- All same elements ---

    @ParameterizedTest
    @MethodSource("solutions")
    void allSameElements(Solution solution) {
        assertTrue(solution.containsNearbyDuplicate(new int[]{5, 5, 5, 5}, 1));
    }

    // --- All distinct elements ---

    @ParameterizedTest
    @MethodSource("solutions")
    void allDistinctElements(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 4, 5}, 3));
    }

    // --- Negative numbers ---

    @ParameterizedTest
    @MethodSource("solutions")
    void negativeNumbers_withinRange(Solution solution) {
        assertTrue(solution.containsNearbyDuplicate(new int[]{-1, -2, -1}, 2));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void negativeNumbers_outOfRange(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(new int[]{-1, 2, 3, -1}, 2));
    }

    // --- Multiple duplicates, only one pair qualifies ---

    @ParameterizedTest
    @MethodSource("solutions")
    void multipleDuplicates_onlyLaterPairQualifies(Solution solution) {
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 2, 1}, 2));
    }

    // --- Large k but no duplicates ---

    @ParameterizedTest
    @MethodSource("solutions")
    void largeK_noDuplicates(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(new int[]{10, 20, 30, 40}, 10000));
    }

    // --- Integer extremes ---

    @ParameterizedTest
    @MethodSource("solutions")
    void integerMinAndMaxValues(Solution solution) {
        assertTrue(solution.containsNearbyDuplicate(
                new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}, 2));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void integerMaxNotDuplicate(Solution solution) {
        assertFalse(solution.containsNearbyDuplicate(
                new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1));
    }
}
