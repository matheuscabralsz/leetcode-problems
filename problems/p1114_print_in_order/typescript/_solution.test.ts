import { describe, test, expect } from "vitest";
import { solutions } from "./solution";

describe.each(solutions)("printInOrder - %o", (runInOrder) => {
  // --- All permutations ---

  test("order [1,2,3]", async () => {
    expect(await runInOrder([1, 2, 3])).toBe("firstsecondthird");
  });

  test("order [1,3,2]", async () => {
    expect(await runInOrder([1, 3, 2])).toBe("firstsecondthird");
  });

  test("order [2,1,3]", async () => {
    expect(await runInOrder([2, 1, 3])).toBe("firstsecondthird");
  });

  test("order [2,3,1]", async () => {
    expect(await runInOrder([2, 3, 1])).toBe("firstsecondthird");
  });

  test("order [3,1,2]", async () => {
    expect(await runInOrder([3, 1, 2])).toBe("firstsecondthird");
  });

  test("order [3,2,1]", async () => {
    expect(await runInOrder([3, 2, 1])).toBe("firstsecondthird");
  });
});
