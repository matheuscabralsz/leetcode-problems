package p1114_print_in_order;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class _SolutionTest {

    static Stream<Supplier<Solution>> solutions() {
        return Stream.of(
                Default::new
        );
    }

    private String runWithThreadOrder(Supplier<Solution> factory, int[] order) throws InterruptedException {
        Solution solution = factory.get();
        StringBuilder sb = new StringBuilder();
        CountDownLatch done = new CountDownLatch(3);

        Runnable first = () -> {
            try { solution.first(() -> sb.append("first")); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            finally { done.countDown(); }
        };
        Runnable second = () -> {
            try { solution.second(() -> sb.append("second")); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            finally { done.countDown(); }
        };
        Runnable third = () -> {
            try { solution.third(() -> sb.append("third")); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            finally { done.countDown(); }
        };

        Runnable[] tasks = { first, second, third };

        for (int idx : order) {
            new Thread(tasks[idx - 1]).start();
        }

        assertTrue(done.await(5, TimeUnit.SECONDS), "Timed out waiting for threads");
        return sb.toString();
    }

    // --- Provided examples ---

    @ParameterizedTest
    @MethodSource("solutions")
    void example1_threadsInOrder(Supplier<Solution> factory) throws InterruptedException {
        assertEquals("firstsecondthird", runWithThreadOrder(factory, new int[]{1, 2, 3}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example2_thirdBeforeSecond(Supplier<Solution> factory) throws InterruptedException {
        assertEquals("firstsecondthird", runWithThreadOrder(factory, new int[]{1, 3, 2}));
    }

    // --- All orderings ---

    @ParameterizedTest
    @MethodSource("solutions")
    void reverseOrder(Supplier<Solution> factory) throws InterruptedException {
        assertEquals("firstsecondthird", runWithThreadOrder(factory, new int[]{3, 2, 1}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void secondFirstThird(Supplier<Solution> factory) throws InterruptedException {
        assertEquals("firstsecondthird", runWithThreadOrder(factory, new int[]{2, 1, 3}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void secondThirdFirst(Supplier<Solution> factory) throws InterruptedException {
        assertEquals("firstsecondthird", runWithThreadOrder(factory, new int[]{2, 3, 1}));
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void thirdFirstSecond(Supplier<Solution> factory) throws InterruptedException {
        assertEquals("firstsecondthird", runWithThreadOrder(factory, new int[]{3, 1, 2}));
    }
}
