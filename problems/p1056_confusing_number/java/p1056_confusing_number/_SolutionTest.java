package p1056_confusing_number;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class _SolutionTest {

    static Stream<Solution> solutions() {
        return Stream.of(
                new Default()
        );
    }

    // --- Provided examples ---

    @ParameterizedTest
    @MethodSource("solutions")
    void example1_6isConfusing(Solution solution) {
        assertTrue(solution.confusingNumber(6));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example2_89isConfusing(Solution solution) {
        assertTrue(solution.confusingNumber(89));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example3_11isNotConfusing(Solution solution) {
        assertFalse(solution.confusingNumber(11));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example4_25isNotConfusing(Solution solution) {
        assertFalse(solution.confusingNumber(25));
    }

    // --- Edge cases ---

    @ParameterizedTest
    @MethodSource("solutions")
    void zero_rotatedIsSame(Solution solution) {
        assertFalse(solution.confusingNumber(0));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void one_rotatedIsSame(Solution solution) {
        assertFalse(solution.confusingNumber(1));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void eight_rotatedIsSame(Solution solution) {
        assertFalse(solution.confusingNumber(8));
    }

    // --- Invalid digits ---

    @ParameterizedTest
    @MethodSource("solutions")
    void containsTwo_notConfusing(Solution solution) {
        assertFalse(solution.confusingNumber(2));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void containsThree_notConfusing(Solution solution) {
        assertFalse(solution.confusingNumber(3));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void containsFour_notConfusing(Solution solution) {
        assertFalse(solution.confusingNumber(4));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void containsFive_notConfusing(Solution solution) {
        assertFalse(solution.confusingNumber(5));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void containsSeven_notConfusing(Solution solution) {
        assertFalse(solution.confusingNumber(7));
    }

    // --- Valid digits but same after rotation ---

    @ParameterizedTest
    @MethodSource("solutions")
    void sixNine_rotatedIsSame(Solution solution) {
        assertFalse(solution.confusingNumber(69));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nineSix_rotatedIsSame(Solution solution) {
        assertFalse(solution.confusingNumber(96));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void eightyEight_rotatedIsSame(Solution solution) {
        assertFalse(solution.confusingNumber(88));
    }

    // --- Confusing multi-digit numbers ---

    @ParameterizedTest
    @MethodSource("solutions")
    void ten_isConfusing(Solution solution) {
        assertTrue(solution.confusingNumber(10));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void sixZero_isConfusing(Solution solution) {
        assertTrue(solution.confusingNumber(60));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nineHundred_isConfusing(Solution solution) {
        assertTrue(solution.confusingNumber(900));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void sixEightNine_rotatedIsSame(Solution solution) {
        assertFalse(solution.confusingNumber(689));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void sixEightZero_isConfusing(Solution solution) {
        assertTrue(solution.confusingNumber(680));
    }

    // --- Larger numbers ---

    @ParameterizedTest
    @MethodSource("solutions")
    void largeConfusingNumber(Solution solution) {
        assertTrue(solution.confusingNumber(1000000006));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void largeNonConfusingWithInvalidDigit(Solution solution) {
        assertFalse(solution.confusingNumber(123));
    }
}
