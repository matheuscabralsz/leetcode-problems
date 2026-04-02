import { describe, test, expect } from "vitest";
import { solutions } from "./solution";

describe.each(solutions)("confusingNumber - %o", (confusingNumber) => {
  // --- Provided examples ---

  test("example 1: 6 is confusing (rotates to 9)", () => {
    expect(confusingNumber(6)).toBe(true);
  });

  test("example 2: 89 is confusing (rotates to 68)", () => {
    expect(confusingNumber(89)).toBe(true);
  });

  test("example 3: 11 is not confusing (rotates to 11)", () => {
    expect(confusingNumber(11)).toBe(false);
  });

  test("example 4: 25 is not confusing (contains invalid digit)", () => {
    expect(confusingNumber(25)).toBe(false);
  });

  // --- Edge cases ---

  test("0 rotated is still 0", () => {
    expect(confusingNumber(0)).toBe(false);
  });

  test("1 rotated is still 1", () => {
    expect(confusingNumber(1)).toBe(false);
  });

  test("8 rotated is still 8", () => {
    expect(confusingNumber(8)).toBe(false);
  });

  // --- Invalid digits ---

  test("2 is not confusing (invalid digit)", () => {
    expect(confusingNumber(2)).toBe(false);
  });

  test("3 is not confusing (invalid digit)", () => {
    expect(confusingNumber(3)).toBe(false);
  });

  test("4 is not confusing (invalid digit)", () => {
    expect(confusingNumber(4)).toBe(false);
  });

  test("5 is not confusing (invalid digit)", () => {
    expect(confusingNumber(5)).toBe(false);
  });

  test("7 is not confusing (invalid digit)", () => {
    expect(confusingNumber(7)).toBe(false);
  });

  // --- Valid digits but same after rotation ---

  test("69 rotated is 69 (not confusing)", () => {
    expect(confusingNumber(69)).toBe(false);
  });

  test("96 rotated is 96 (not confusing)", () => {
    expect(confusingNumber(96)).toBe(false);
  });

  test("88 rotated is 88 (not confusing)", () => {
    expect(confusingNumber(88)).toBe(false);
  });

  // --- Confusing multi-digit numbers ---

  test("10 is confusing (rotates to 01 = 1)", () => {
    expect(confusingNumber(10)).toBe(true);
  });

  test("60 is confusing (rotates to 09 = 9)", () => {
    expect(confusingNumber(60)).toBe(true);
  });

  test("900 is confusing (rotates to 006 = 6)", () => {
    expect(confusingNumber(900)).toBe(true);
  });

  test("689 rotated is 689 (not confusing)", () => {
    expect(confusingNumber(689)).toBe(false);
  });

  test("680 is confusing (rotates to 089 = 89)", () => {
    expect(confusingNumber(680)).toBe(true);
  });

  // --- Larger numbers ---

  test("1000000006 is confusing", () => {
    expect(confusingNumber(1000000006)).toBe(true);
  });

  test("123 is not confusing (contains invalid digits)", () => {
    expect(confusingNumber(123)).toBe(false);
  });
});
