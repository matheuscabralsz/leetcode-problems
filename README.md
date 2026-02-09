# LeetCode Java Solutions

LeetCode solutions in Java 17 with multiple approaches per problem and parameterized tests.

## Structure

Each problem lives in its own package under `com.leetcode.p{number}_{name}`:

- `Solution.java` — interface defining the method signature
- `Default.java` / other classes — different solution approaches
- `SolutionTest.java` — parameterized tests that run against all implementations

## Running Tests

```bash
mvn test
```

## Adding a New Problem

```bash
bash scripts/new-challenge.sh <number> <snake_case_name> <methodName> '<returnType>' '<params>'
```

Example:

```bash
bash scripts/new-challenge.sh 1 two_sum twoSum 'int[]' 'int[] nums, int target'
```
