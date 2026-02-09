# LeetCode Java Solutions

LeetCode solutions in Java 17 with multiple approaches per problem and parameterized tests.

## Quick Start

Just give Claude Code a problem number and it handles everything:

```
/new-challenge 217
```

This single command will:

1. Look up the problem on LeetCode (name, method signature, types)
2. Run the scaffolding script to generate the package structure
3. Write comprehensive test cases — all LeetCode examples plus edge cases

You go from problem number to a fully test-driven setup in seconds. All that's left is writing the solution.

### Manual Scaffolding

The underlying script can also be run directly:

```bash
bash scripts/new-challenge.sh <number> <snake_case_name> <methodName> '<returnType>' '<params>'
```

```bash
# Example
bash scripts/new-challenge.sh 1 two_sum twoSum 'int[]' 'int[] nums, int target'
```

## Structure

Each problem lives in its own package under `com.leetcode.p{number}_{name}`:

- `Solution.java` — interface defining the method signature
- `Default.java` / other classes — different solution approaches
- `SolutionTest.java` — parameterized tests that run against all implementations

## Running Tests

```bash
mvn test
```
