# LeetCode Java & TypeScript Solutions

## Project Overview

A Java 17 Maven + TypeScript Vitest project for solving LeetCode problems with multiple solution approaches per problem and parameterized tests.

## Tech Stack

- Java 17, Maven, JUnit Jupiter 5.10.2 (parameterized tests)
- TypeScript (ES2022), Vitest (parameterized tests via `describe.each`)

## Project Structure

All problems are co-located in a single flat directory per problem under `problems/`:

```
problems/p{NNNN}_{snake_name}/
  Solution.java        — interface defining the LeetCode method signature
  Default.java         — stub/first implementation
  *.java               — additional solution approaches (e.g., BruteForce, OptimalHashMap)
  _SolutionTest.java   — parameterized tests that run against ALL Java implementations
  solution.ts          — type alias, named exports (one function per approach), solutions array
  _solution.test.ts    — vitest parameterized tests using describe.each
```

## Conventions

### Directory naming
- Format: `p{NNNN}_{snake_case_name}` (number is zero-padded to 4 digits)
- Examples: `p0001_two_sum`, `p0217_contains_duplicate`, `p0219_contains_duplicate_ii`
- Snake_case is used for all directories (Java packages cannot contain hyphens)

### Test file naming
- Test files are prefixed with underscore (`_`) to visually separate them from source files
- Java: `_SolutionTest.java` (class `_SolutionTest`)
- TypeScript: `_solution.test.ts`

### Java

#### Package naming
- Format: `p{NNNN}_{snake_case_name}` (number is zero-padded to 4 digits)
- Examples: `p0001_two_sum`, `p0217_contains_duplicate`, `p0219_contains_duplicate_ii`

#### Solution pattern
- Each problem defines a `Solution` interface with the LeetCode method signature
- The `Solution` interface includes a Javadoc comment with the problem number, title, and description
- Each approach is a separate class implementing `Solution`
- Name classes descriptively: `BruteForce`, `OptimalHashMap`, `WithStreams`, etc.

#### Test pattern
- One `_SolutionTest` class per problem
- A `solutions()` static method returns a `Stream<Solution>` of all implementations
- Every test method is `@ParameterizedTest` + `@MethodSource("solutions")` so it runs against all approaches
- Test method names are descriptive: `example1_duplicateWithinRange`, `emptyArray`, `kIsZero_noDuplicatePossible`
- Tests are grouped with comments: `// --- Provided examples ---`, `// --- Edge cases ---`, `// --- Boundary of k ---`, etc.
- Include all LeetCode examples plus edge cases (empty input, single element, extremes, boundary values)

### TypeScript

#### Solution pattern
- A type alias defines the function signature (e.g., `type TwoSumFn = (nums: number[], target: number) => number[]`)
- Each approach is a named export implementing the type alias
- A `solutions` array export collects all implementations
- Name functions descriptively: `bruteForce`, `withHashMap`, `withStreams`, etc.

#### Test pattern
- One `_solution.test.ts` file per problem, colocated with `solution.ts`
- Uses `describe.each(solutions)` to run all tests against every implementation
- Test names are descriptive: `"example 1"`, `"empty array"`, `"single element"`
- Include all LeetCode examples plus edge cases

## Commands

### Java
- `mvn test` — run all Java tests
- `mvn test -Dtest="p0219_contains_duplicate_ii._SolutionTest"` — run tests for a specific problem

### TypeScript
- `npm test` — run all TypeScript tests
- `npx vitest run problems/p0001_two_sum/` — run tests for a specific problem
- `npm run test:watch` — run tests in watch mode

### Scaffolding
- `/new-challenge <number>` — scaffold a new problem (asks for language choice, looks up details, generates files, writes tests)
- `bash scripts/new-challenge.sh <number> <snake_name> <methodName> '<returnType>' '<params>' '[title]' '[description]'` — manual Java scaffolding
- `bash scripts/new-challenge-ts.sh <number> <snake_name> <functionName> '<returnType>' '<params>' '[title]' '[description]'` — manual TypeScript scaffolding

## When Adding a New Problem

1. Use `/new-challenge <number>` or run the appropriate scaffolding script manually
2. Write solution implementations (Java: classes implementing `Solution`; TypeScript: named function exports)
3. Register each implementation (Java: `_SolutionTest.solutions()`; TypeScript: `solutions` array in `solution.ts`)
4. Run tests to verify (`mvn test` for Java, `npm test` for TypeScript)
