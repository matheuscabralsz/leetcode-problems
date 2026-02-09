You are scaffolding a new LeetCode challenge. The problem number is: $ARGUMENTS

Follow these steps:

1. **Look up the problem**: Determine the LeetCode problem name and its method signature (method name, return type, and parameters). Use your knowledge of LeetCode problems. If you are unsure or don't know the problem, search the web for "LeetCode problem $ARGUMENTS" to find the details.

2. **Derive the arguments** for `scripts/new-challenge.sh`:
   - `number`: the problem number (e.g., `1`)
   - `snake_case_name`: the problem name in snake_case (e.g., `two_sum`)
   - `method_name`: the method name in camelCase as defined by LeetCode (e.g., `twoSum`)
   - `return_type`: the Java return type (e.g., `int[]`)
   - `params`: the Java method parameters (e.g., `int[] nums, int target`)

3. **Confirm with the user**: Before running anything, present the problem details to the user and ask for confirmation using `AskUserQuestion`. Show:
   - Problem number and title
   - Method signature: `returnType methodName(params)`
   - Package name that will be created
   Ask the user to confirm this is the correct problem. Only proceed if the user confirms. If the user says it's wrong, ask them to provide the correct details or problem number.

4. **Run the script**:
   ```bash
   bash scripts/new-challenge.sh <number> <snake_case_name> <methodName> '<returnType>' '<params>'
   ```

5. **Add test cases**: After the script creates the files, read the generated `SolutionTest.java` and replace the placeholder test with test cases for this problem. Include:
   - All LeetCode example test cases (one `@ParameterizedTest` method each, with a descriptive name).
   - A few edge cases and boundary tests where appropriate (e.g., empty input, single element, extremes).
   Follow the existing test conventions in this project (see `src/test/java/com/leetcode/p0219_contains_duplicate_ii/SolutionTest.java` for reference).

Important notes:
- Use Java types that match LeetCode's Java signature exactly.
- For `List<List<Integer>>` or other generic types, include the necessary imports in both the source and test files.
- If the problem uses custom types (e.g., `TreeNode`, `ListNode`), add the required class definitions to the source files.
- For `void` return types (in-place mutation problems), test by asserting on the mutated input rather than a return value.
- Make sure the test file compiles by using correct assertions (`assertEquals`, `assertTrue`, `assertArrayEquals`, etc.) based on the return type.
