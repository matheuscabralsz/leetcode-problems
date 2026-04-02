import { describe, test, expect } from "vitest";
import { solutions } from "./solution";

describe.each(solutions)("lengthOfLongestSubstringTwoDistinct - %o", (lengthOfLongestSubstringTwoDistinct) => {
  // --- Provided examples ---

  test("example 1 - eceba", () => {
    expect(lengthOfLongestSubstringTwoDistinct("eceba")).toBe(3);
  });

  test("example 2 - ccaabbb", () => {
    expect(lengthOfLongestSubstringTwoDistinct("ccaabbb")).toBe(5);
  });

  // --- Edge cases ---

  test("single character", () => {
    expect(lengthOfLongestSubstringTwoDistinct("a")).toBe(1);
  });

  test("two distinct characters - entire string", () => {
    expect(lengthOfLongestSubstringTwoDistinct("aabb")).toBe(4);
  });

  test("all same characters", () => {
    expect(lengthOfLongestSubstringTwoDistinct("aaaaa")).toBe(5);
  });

  test("two characters only", () => {
    expect(lengthOfLongestSubstringTwoDistinct("ab")).toBe(2);
  });

  test("empty string", () => {
    expect(lengthOfLongestSubstringTwoDistinct("")).toBe(0);
  });

  // --- Longer strings ---

  test("longest substring at end", () => {
    expect(lengthOfLongestSubstringTwoDistinct("abcbbbb")).toBe(4);
  });

  test("longest substring at start", () => {
    expect(lengthOfLongestSubstringTwoDistinct("aaaabcd")).toBe(4);
  });

  test("alternating characters", () => {
    expect(lengthOfLongestSubstringTwoDistinct("ababab")).toBe(6);
  });

  test("three distinct - middle is longest", () => {
    expect(lengthOfLongestSubstringTwoDistinct("abbbca")).toBe(4);
  });

  // --- Boundary: exactly two distinct in entire string ---

  test("exactly two distinct - entire string qualifies", () => {
    expect(lengthOfLongestSubstringTwoDistinct("aababba")).toBe(7);
  });

  // --- Many distinct characters ---

  test("all distinct characters", () => {
    expect(lengthOfLongestSubstringTwoDistinct("abcdef")).toBe(2);
  });
});
