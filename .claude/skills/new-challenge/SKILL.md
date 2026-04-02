---
name: new-challenge
description: Scaffold a new LeetCode challenge with solution files and parameterized tests. Use when the user wants to add a new LeetCode problem.
user-invocable: true
argument-hint: <problem-number>
---

You are scaffolding a new LeetCode challenge. The problem number is: $ARGUMENTS

Follow these steps:

1. **Look up the problem**: Determine the LeetCode problem name and its method signature (method name, return type, and parameters). Use your knowledge of LeetCode problems. If you are unsure or don't know the problem, search the web for "LeetCode problem $ARGUMENTS" to find the details.

2. **Ask the user which language(s)** to scaffold using `AskUserQuestion`:
   - Java
   - TypeScript
   - Both

3. **Derive the arguments** for the chosen scaffolding script(s):

   For **Java** (`scripts/new-challenge.sh`):
   - `number`: the problem number (e.g., `1`)
   - `snake_case_name`: the problem name in snake_case (e.g., `two_sum`)
   - `method_name`: the method name in camelCase as defined by LeetCode (e.g., `twoSum`)
   - `return_type`: the Java return type (e.g., `int[]`)
   - `params`: the Java method parameters (e.g., `int[] nums, int target`)

   For **TypeScript** (`scripts/new-challenge-ts.sh`):
   - `number`: the problem number (e.g., `1`)
   - `snake_case_name`: the problem name in snake_case (e.g., `two_sum`)
   - `function_name`: the function name in camelCase as defined by LeetCode (e.g., `twoSum`)
   - `return_type`: the TypeScript return type (e.g., `number[]`)
   - `params`: the TypeScript function parameters (e.g., `nums: number[], target: number`)

4. **Confirm with the user**: Before running anything, present the problem details to the user and ask for confirmation using `AskUserQuestion`. Show:
   - Problem number and title
   - Method/function signature for each chosen language
   - Directory name that will be created (`problems/p{NNNN}_{snake_case_name}`)
   Ask the user to confirm this is the correct problem. Only proceed if the user confirms. If the user says it's wrong, ask them to provide the correct details or problem number.

5. **Run the script(s)**:

   For Java:
   ```bash
   bash scripts/new-challenge.sh <number> <snake_case_name> <methodName> '<returnType>' '<params>'
   ```

   For TypeScript:
   ```bash
   bash scripts/new-challenge-ts.sh <number> <snake_case_name> <functionName> '<returnType>' '<params>'
   ```

6. **Add test cases**: After the script(s) create the files, read the generated test file(s) and replace the placeholder tests with real test cases. Include:
   - All LeetCode example test cases (one test method each, with a descriptive name).
   - A few edge cases and boundary tests where appropriate (e.g., empty input, single element, extremes).

   For Java: Follow the existing test conventions (see `problems/p0219_contains_duplicate_ii/_SolutionTest.java` for reference).
   For TypeScript: Use `describe.each(solutions)` with `test()` blocks and `expect()` assertions.

Important notes:
- Use types that match LeetCode's signature exactly for each language.
- For Java: use `List<List<Integer>>` or other generic types with necessary imports. If the problem uses custom types (e.g., `TreeNode`, `ListNode`), add the required class definitions.
- For TypeScript: use native TS types (`number[]`, `string`, `boolean`, etc.). If the problem uses custom types, define them in `solution.ts`.
- For `void` return types (in-place mutation problems), test by asserting on the mutated input rather than a return value.
- Make sure the test files compile/pass type-checking with correct assertions.
