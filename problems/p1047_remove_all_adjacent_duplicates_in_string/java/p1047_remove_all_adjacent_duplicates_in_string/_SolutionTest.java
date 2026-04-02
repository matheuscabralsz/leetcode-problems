package p1047_remove_all_adjacent_duplicates_in_string;

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
    void example1_abbaca(Solution solution) {
        assertEquals("ca", solution.removeDuplicates("abbaca"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example2_azxxzy(Solution solution) {
        assertEquals("ay", solution.removeDuplicates("azxxzy"));
    }

    // --- Edge cases ---

    @ParameterizedTest
    @MethodSource("solutions")
    void singleCharacter(Solution solution) {
        assertEquals("a", solution.removeDuplicates("a"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void twoSameCharacters_removedEntirely(Solution solution) {
        assertEquals("", solution.removeDuplicates("aa"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void twoDifferentCharacters(Solution solution) {
        assertEquals("ab", solution.removeDuplicates("ab"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void noDuplicates(Solution solution) {
        assertEquals("abcdef", solution.removeDuplicates("abcdef"));
    }

    // --- Cascading removals ---

    @ParameterizedTest
    @MethodSource("solutions")
    void cascadingRemoval_allRemoved(Solution solution) {
        // "abba" -> remove "bb" -> "aa" -> remove "aa" -> ""
        assertEquals("", solution.removeDuplicates("abba"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void cascadingRemoval_partialRemoval(Solution solution) {
        // "aababba" -> remove first "aa" -> "babba" -> remove "bb" -> "baa" -> remove "aa" -> "b"
        // Or using stack approach: a,a->remove, b,a,b,b->remove, a -> "ba"... let me trace carefully:
        // "aababba": a a b a b b a
        // stack: push a, push a (== top) -> pop -> stack: []
        // push b -> [b], push a -> [b,a], push b -> [b,a,b], push b (== top) -> pop -> [b,a]
        // push a (== top of [b,a] is a) -> pop -> [b]
        // result: "b"
        assertEquals("b", solution.removeDuplicates("aababba"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void deeplyNestedCascade(Solution solution) {
        // "abccba" -> remove "cc" -> "abba" -> remove "bb" -> "aa" -> remove "aa" -> ""
        assertEquals("", solution.removeDuplicates("abccba"));
    }

    // --- All same characters ---

    @ParameterizedTest
    @MethodSource("solutions")
    void allSameEvenLength(Solution solution) {
        assertEquals("", solution.removeDuplicates("aaaa"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void allSameOddLength(Solution solution) {
        assertEquals("a", solution.removeDuplicates("aaa"));
    }

    // --- Duplicates at boundaries ---

    @ParameterizedTest
    @MethodSource("solutions")
    void duplicatesAtStart(Solution solution) {
        assertEquals("bc", solution.removeDuplicates("aabc"));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void duplicatesAtEnd(Solution solution) {
        assertEquals("ab", solution.removeDuplicates("abcc"));
    }

    // --- Longer string with multiple pairs ---

    @ParameterizedTest
    @MethodSource("solutions")
    void multipleSeparatePairs(Solution solution) {
        // "aabccbdd" -> remove "aa" -> "bccbdd" -> remove "cc" -> "bbdd" -> remove "bb" -> "dd" -> remove "dd" -> ""
        assertEquals("", solution.removeDuplicates("aabccbdd"));
    }
}
