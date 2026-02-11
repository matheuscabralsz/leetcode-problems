package com.leetcode.p2327_number_of_people_aware_of_a_secret;

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
    void example1_sixDays_delay2_forget4(Solution solution) {
        assertEquals(5, solution.peopleAwareOfSecret(6, 2, 4));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example2_fourDays_delay1_forget3(Solution solution) {
        assertEquals(6, solution.peopleAwareOfSecret(4, 1, 3));
    }

    // --- Edge cases ---

    @ParameterizedTest
    @MethodSource("solutions")
    void singleDay_onlyOriginalPerson(Solution solution) {
        assertEquals(1, solution.peopleAwareOfSecret(1, 2, 4));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void twoDays_delayNotReached(Solution solution) {
        assertEquals(1, solution.peopleAwareOfSecret(2, 3, 5));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void forgetOnDayN_personForgetsExactlyAtEnd(Solution solution) {
        // n=4, delay=2, forget=4 → person 1 discovers on day 1, forgets on day 5
        // Day 1: person A knows (1)
        // Day 2: A can't share yet (delay=2, shares starting day 3) (1)
        // Day 3: A shares with B (2)
        // Day 4: A shares with C (3)
        // Person A forgets on day 5, so still knows on day 4
        assertEquals(3, solution.peopleAwareOfSecret(4, 2, 4));
    }

    // --- Boundary of delay and forget ---

    @ParameterizedTest
    @MethodSource("solutions")
    void delayEqualsOne_shareImmediately(Solution solution) {
        // delay=1 means person shares starting day 2 (1 day after discovery)
        // forget=3 means person forgets on day 4
        // Day 1: A (1)
        // Day 2: A shares → B (2)
        // Day 3: A shares → C, B shares → D (4)
        assertEquals(4, solution.peopleAwareOfSecret(3, 1, 3));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void delayEqualsForgotMinusOne_shareOnlyOneDay(Solution solution) {
        // delay=3, forget=4 → each person shares only on day (discovery + 3)
        // Day 1: A (1)
        // Day 2: A can't share (1)
        // Day 3: A can't share (1)
        // Day 4: A shares → B (2), A forgets on day 5
        // Day 5: A forgets. B can't share yet (delay=3 from day 4 → shares on day 7) (1)
        assertEquals(1, solution.peopleAwareOfSecret(5, 3, 4));
    }

    // --- Larger values ---

    @ParameterizedTest
    @MethodSource("solutions")
    void largerN_modHandling(Solution solution) {
        // n=684, delay=18, forget=496 — tests that modulo 10^9+7 is applied correctly
        // Expected result from LeetCode: 653668527
        assertEquals(653668527, solution.peopleAwareOfSecret(684, 18, 496));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void nEqualsForget_originalPersonForgetsOnDayN(Solution solution) {
        // n=4, delay=2, forget=4 → same as forgetOnDayN test
        // Person A discovers day 1, forgets day 5 (still knows on day 4)
        assertEquals(3, solution.peopleAwareOfSecret(4, 2, 4));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void delayEqualsTwo_forgetEqualsTwo_noSharing(Solution solution) {
        // delay=2, forget=2 → person discovers day 1, can share starting day 3, but forgets on day 3
        // Cannot share on the day of forgetting, so no sharing ever happens
        // Only original person, who forgets on day 3
        // Day 1: A knows (1)
        // Day 2: A knows (1)
        // Day 3: A forgets (0)
        assertEquals(0, solution.peopleAwareOfSecret(3, 2, 2));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void allPeopleStillKnow_nLessThanForget(Solution solution) {
        // n=3, delay=1, forget=100 → nobody forgets within n days
        // Day 1: A (1)
        // Day 2: A shares → B (2)
        // Day 3: A shares → C, B shares → D (4)
        assertEquals(4, solution.peopleAwareOfSecret(3, 1, 100));
    }
}
