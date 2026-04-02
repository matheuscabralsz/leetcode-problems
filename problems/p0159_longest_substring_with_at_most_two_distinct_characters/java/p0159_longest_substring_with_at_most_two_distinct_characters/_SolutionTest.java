package p0159_longest_substring_with_at_most_two_distinct_characters;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class _SolutionTest {

    static Stream<Solution> solutions() {
        return Stream.of(
                new Default(),
                new WithStreams()
        );
    }

    // --- Provided examples ---

    @ParameterizedTest
    @MethodSource("solutions")
    void example1_eceba(Solution solution) {
        assertEquals(3, solution.lengthOfLongestSubstringTwoDistinct("eceba"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example2_ccaabbb(Solution solution) {
        assertEquals(5, solution.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

    // --- Edge cases ---

    @ParameterizedTest
    @MethodSource("solutions")
    void singleCharacter(Solution solution) {
        assertEquals(1, solution.lengthOfLongestSubstringTwoDistinct("a"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void twoDistinctCharacters_entireString(Solution solution) {
        assertEquals(4, solution.lengthOfLongestSubstringTwoDistinct("aabb"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void allSameCharacters(Solution solution) {
        assertEquals(5, solution.lengthOfLongestSubstringTwoDistinct("aaaaa"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void twoCharactersOnly(Solution solution) {
        assertEquals(2, solution.lengthOfLongestSubstringTwoDistinct("ab"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void emptyString(Solution solution) {
        assertEquals(0, solution.lengthOfLongestSubstringTwoDistinct(""));
    }

    // --- Longer strings ---

    @ParameterizedTest
    @MethodSource("solutions")
    void longestSubstringAtEnd(Solution solution) {
        assertEquals(4, solution.lengthOfLongestSubstringTwoDistinct("abcbbbb"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void longestSubstringAtStart(Solution solution) {
        assertEquals(4, solution.lengthOfLongestSubstringTwoDistinct("aaaabcd"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void alternatingCharacters(Solution solution) {
        assertEquals(6, solution.lengthOfLongestSubstringTwoDistinct("ababab"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void threeDistinctCharacters_middleIsLongest(Solution solution) {
        assertEquals(4, solution.lengthOfLongestSubstringTwoDistinct("abbbca"));
    }

    // --- Boundary: exactly two distinct in entire string ---

    @ParameterizedTest
    @MethodSource("solutions")
    void exactlyTwoDistinct_entireStringQualifies(Solution solution) {
        assertEquals(7, solution.lengthOfLongestSubstringTwoDistinct("aababba"));
    }

    // --- Many distinct characters ---

    @ParameterizedTest
    @MethodSource("solutions")
    void allDistinctCharacters(Solution solution) {
        assertEquals(2, solution.lengthOfLongestSubstringTwoDistinct("abcdef"));
    }
}
