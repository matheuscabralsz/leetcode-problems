import { describe, test, expect } from "vitest";
import { solutions } from "./solution";

describe.each(solutions)("maxA - %o", (maxA) => {
  // --- Provided examples ---

  test("example 1: n=3 => 3 (A,A,A)", () => {
    expect(maxA(3)).toBe(3);
  });

  test("example 2: n=7 => 9 (A,A,A,Ctrl-A,Ctrl-C,Ctrl-V,Ctrl-V)", () => {
    expect(maxA(7)).toBe(9);
  });

  // --- Small values ---

  test("n=1, single keystroke", () => {
    expect(maxA(1)).toBe(1);
  });

  test("n=2", () => {
    expect(maxA(2)).toBe(2);
  });

  test("n=4, no benefit from copy-paste yet", () => {
    expect(maxA(4)).toBe(4);
  });

  test("n=5", () => {
    expect(maxA(5)).toBe(5);
  });

  test("n=6, first time copy-paste ties with just typing", () => {
    expect(maxA(6)).toBe(6);
  });

  // --- Medium values ---

  test("n=8", () => {
    expect(maxA(8)).toBe(12);
  });

  test("n=9", () => {
    expect(maxA(9)).toBe(16);
  });

  test("n=10", () => {
    expect(maxA(10)).toBe(20);
  });

  test("n=11", () => {
    expect(maxA(11)).toBe(27);
  });

  // --- Larger values ---

  test("n=15", () => {
    expect(maxA(15)).toBe(81);
  });

  test("n=20", () => {
    expect(maxA(20)).toBe(324);
  });

  // --- Upper bound ---

  test("n=50, upper constraint", () => {
    expect(maxA(50)).toBe(1327104);
  });
});
