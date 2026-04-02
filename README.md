# LeetCode Java & TypeScript Solutions

Scaffold LeetCode challenges locally in seconds. Give it a problem number and get a full project setup — solution interface/type, stub implementation, and parameterized tests — ready to solve in your IDE.

## Quick Start

Just give Claude Code a problem number and it handles everything:

```
/new-challenge 217
```

This single command will:

1. Ask which language(s) to scaffold (Java, TypeScript, or both)
2. Look up the problem on LeetCode (name, method signature, types)
3. Run the scaffolding script to generate the project structure
4. Write comprehensive test cases — all LeetCode examples plus edge cases

You go from problem number to a fully test-driven setup in seconds. All that's left is writing the solution.

### Manual Scaffolding

#### Java

```bash
bash scripts/new-challenge.sh <number> <snake_case_name> <methodName> '<returnType>' '<params>' '[title]' '[description]'
```

```bash
# Example
bash scripts/new-challenge.sh 1 two_sum twoSum 'int[]' 'int[] nums, int target' 'Two Sum' 'Given an array of integers...'
```

#### TypeScript

```bash
bash scripts/new-challenge-ts.sh <number> <snake_case_name> <functionName> '<returnType>' '<params>' '[title]' '[description]'
```

```bash
# Example
bash scripts/new-challenge-ts.sh 1 two_sum twoSum 'number[]' 'nums: number[], target: number' 'Two Sum' 'Given an array of integers...'
```

## Structure

Each problem lives in its own directory under `problems/p{number}_{name}`:

- `Solution.java` — interface with the method signature and the problem description as a Javadoc comment
- `Default.java` / other classes — different Java solution approaches
- `_SolutionTest.java` — parameterized Java tests that run against all implementations
- `solution.ts` — type alias, named function exports (one per approach), `solutions` array
- `_solution.test.ts` — vitest parameterized tests using `describe.each`

Test files are prefixed with `_` to visually separate them from source files.

## Running Tests

### Java

```bash
mvn test
```

### TypeScript

```bash
npm test
```
