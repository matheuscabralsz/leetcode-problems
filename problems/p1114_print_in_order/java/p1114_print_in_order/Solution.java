package p1114_print_in_order;

/**
 * 1114. Print in Order
 *
 * Suppose we have a class:
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 *
 * The same instance of Foo will be passed to three different threads. Thread A will call first(),
 * thread B will call second(), and thread C will call third(). Design a mechanism and modify the
 * program to ensure that second() is executed after first(), and third() is executed after second().
 */
public interface Solution {
    void first(Runnable printFirst) throws InterruptedException;
    void second(Runnable printSecond) throws InterruptedException;
    void third(Runnable printThird) throws InterruptedException;
}
