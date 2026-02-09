# LeetCode Java Solutions

Scaffold LeetCode challenges locally in seconds. Give it a problem number and get a full project setup — solution interface, stub implementation, and parameterized tests — ready to solve in your IDE.

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
bash scripts/new-challenge.sh <number> <snake_case_name> <methodName> '<returnType>' '<params>' '[title]' '[description]'
```

```bash
# Example
bash scripts/new-challenge.sh 1 two_sum twoSum 'int[]' 'int[] nums, int target' 'Two Sum' 'Given an array of integers...'
```

## Structure

Each problem lives in its own package under `com.leetcode.p{number}_{name}`:

- `Solution.java` — interface with the method signature and the problem description as a Javadoc comment
- `Default.java` / other classes — different solution approaches
- `SolutionTest.java` — parameterized tests that run against all implementations

## Running Tests

```bash
mvn test
```
