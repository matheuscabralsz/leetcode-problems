# LeetCode Java Solutions

## Project Overview

A Java 17 Maven project for solving LeetCode problems with multiple solution approaches per problem and parameterized JUnit 5 tests.

## Tech Stack

- Java 17
- Maven (no wrapper checked in)
- JUnit Jupiter 5.10.2 (parameterized tests)

## Project Structure

```
src/main/java/com/leetcode/p{NNNN}_{snake_name}/
  Solution.java      — interface defining the LeetCode method signature
  Default.java       — stub/first implementation
  *.java             — additional solution approaches (e.g., BruteForce, OptimalHashMap)

src/test/java/com/leetcode/p{NNNN}_{snake_name}/
  SolutionTest.java  — parameterized tests that run against ALL implementations
```

## Conventions

### Package naming
- Format: `com.leetcode.p{NNNN}_{snake_case_name}` (number is zero-padded to 4 digits)
- Examples: `p0001_two_sum`, `p0217_contains_duplicate`, `p0219_contains_duplicate_ii`

### Solution pattern
- Each problem defines a `Solution` interface with the LeetCode method signature
- The `Solution` interface includes a Javadoc comment with the problem number, title, and description
- Each approach is a separate class implementing `Solution`
- Name classes descriptively: `BruteForce`, `OptimalHashMap`, `WithStreams`, etc.

### Test pattern
- One `SolutionTest` class per problem
- A `solutions()` static method returns a `Stream<Solution>` of all implementations
- Every test method is `@ParameterizedTest` + `@MethodSource("solutions")` so it runs against all approaches
- Test method names are descriptive: `example1_duplicateWithinRange`, `emptyArray`, `kIsZero_noDuplicatePossible`
- Tests are grouped with comments: `// --- Provided examples ---`, `// --- Edge cases ---`, `// --- Boundary of k ---`, etc.
- Include all LeetCode examples plus edge cases (empty input, single element, extremes, boundary values)

## Commands

- `mvn test` — run all tests
- `mvn test -pl . -Dtest="com.leetcode.p0219_contains_duplicate_ii.SolutionTest"` — run tests for a specific problem
- `/new-challenge <number>` — scaffold a new problem (looks up LeetCode details, generates files, writes tests)
- `bash scripts/new-challenge.sh <number> <snake_name> <methodName> '<returnType>' '<params>' '[title]' '[description]'` — manual scaffolding

## When Adding a New Problem

1. Use `/new-challenge <number>` or run `scripts/new-challenge.sh` manually
2. Write solution implementations as classes that implement the `Solution` interface
3. Register each implementation in `SolutionTest.solutions()`
4. Run `mvn test` to verify
