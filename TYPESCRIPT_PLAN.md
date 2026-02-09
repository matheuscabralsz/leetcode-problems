# Plan: Add TypeScript/JavaScript LeetCode Solutions

This plan adds TypeScript support alongside the existing Java setup. The Java workflow remains completely untouched.

## Step 1: Create the TypeScript directory structure

```bash
mkdir -p typescript/src
```

## Step 2: Initialize the TypeScript project

```bash
cd typescript
npm init -y
npm install --save-dev typescript vitest @types/node
```

## Step 3: Configure TypeScript (`typescript/tsconfig.json`)

```json
{
  "compilerOptions": {
    "target": "ES2022",
    "module": "ESNext",
    "moduleResolution": "bundler",
    "strict": true,
    "esModuleInterop": true,
    "outDir": "dist",
    "rootDir": "src",
    "declaration": true
  },
  "include": ["src"]
}
```

## Step 4: Configure Vitest (`typescript/vitest.config.ts`)

```ts
import { defineConfig } from "vitest/config";

export default defineConfig({
  test: {
    globals: true,
    include: ["src/**/*.test.ts"],
  },
});
```

## Step 5: Add npm scripts to `typescript/package.json`

```json
{
  "scripts": {
    "test": "vitest run",
    "test:watch": "vitest"
  }
}
```

## Step 6: Define the problem structure convention

Each problem lives in its own directory under `typescript/src/`:

```
typescript/src/p0001-two-sum/
  solution.ts        — named exports: one function per approach
  solution.test.ts   — vitest parameterized tests using test.each
```

### Example: `solution.ts`

```ts
type TwoSumFn = (nums: number[], target: number) => number[];

export const bruteForce: TwoSumFn = (nums, target) => {
  // TODO
  return [];
};

export const withHashMap: TwoSumFn = (nums, target) => {
  // TODO
  return [];
};

export const solutions: TwoSumFn[] = [bruteForce, withHashMap];
```

### Example: `solution.test.ts`

```ts
import { describe, test, expect } from "vitest";
import { solutions } from "./solution";

describe.each(solutions)("twoSum - %o", (twoSum) => {
  test("example 1", () => {
    expect(twoSum([2, 7, 11, 15], 9)).toEqual([0, 1]);
  });

  test("example 2", () => {
    expect(twoSum([3, 2, 4], 6)).toEqual([1, 2]);
  });
});
```

## Step 7: Create the TypeScript scaffolding script (`scripts/new-challenge-ts.sh`)

Mirror the Java scaffolding script but generate:
- `typescript/src/p{NNNN}-{kebab-name}/solution.ts` — type alias + stub export + `solutions` array
- `typescript/src/p{NNNN}-{kebab-name}/solution.test.ts` — vitest `describe.each` template

Note: TypeScript uses **kebab-case** for directory names (e.g., `p0001-two-sum`) instead of Java's snake_case.

## Step 8: Update the `/new-challenge` Claude command

Update `.claude/commands/new-challenge.md` to:
1. Ask which language (Java, TypeScript, or both)
2. Run the appropriate scaffolding script(s)
3. Write tests for the chosen language(s)

## Step 9: Update `.gitignore`

Add these entries:

```
typescript/node_modules/
typescript/dist/
```

## Step 10: Update `.claude/CLAUDE.md`

Add the TypeScript conventions, commands, and structure to the project instructions.

## Step 11: Update `README.md`

Document the TypeScript workflow alongside Java.

## Summary of new files

```
typescript/
  package.json
  tsconfig.json
  vitest.config.ts
  src/              (empty, ready for problems)
scripts/
  new-challenge-ts.sh
```

## Files to modify

```
.gitignore                        (add node_modules, dist)
.claude/CLAUDE.md                 (add TS conventions)
.claude/commands/new-challenge.md (support language choice)
README.md                         (document TS workflow)
```
