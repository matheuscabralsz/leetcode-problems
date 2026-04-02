#!/usr/bin/env bash
set -euo pipefail

# Usage: new-challenge-ts.sh <number> <snake_case_name> <functionName> <returnType> <params> [title] [description]
# Example: new-challenge-ts.sh 1 two_sum twoSum "number[]" "nums: number[], target: number" "Two Sum" "Given an array..."

if [[ $# -lt 5 ]]; then
  echo "Usage: $0 <number> <snake_case_name> <functionName> <returnType> <params> [title] [description]"
  echo "Example: $0 1 two_sum twoSum 'number[]' 'nums: number[], target: number' 'Two Sum' 'Given an array...'"
  exit 1
fi

NUMBER="$1"
NAME="$2"
FUNC="$3"
RETURN_TYPE="$4"
PARAMS="$5"
TITLE="${6:-}"
DESCRIPTION="${7:-}"

# Zero-pad number to 4 digits
PADDED=$(printf "%04d" "$NUMBER")

DIR_NAME="p${PADDED}_${NAME}"

# Resolve project root (parent of scripts/)
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

PROBLEM_DIR="${PROJECT_ROOT}/problems/${DIR_NAME}"
TS_DIR="${PROBLEM_DIR}/typescript"

if [[ -d "$TS_DIR" ]]; then
  echo "Error: TypeScript directory already exists: ${TS_DIR}"
  exit 1
fi

mkdir -p "$TS_DIR"

# --- Build type alias name from function name (PascalCase + Fn) ---
TYPE_NAME="$(echo "${FUNC:0:1}" | tr '[:lower:]' '[:upper:]')${FUNC:1}Fn"

# --- Build default return based on return type ---
case "$RETURN_TYPE" in
  boolean)    DEFAULT_RETURN="return false;" ;;
  number)     DEFAULT_RETURN="return 0;" ;;
  string)     DEFAULT_RETURN='return "";' ;;
  void)       DEFAULT_RETURN="// TODO" ;;
  *\[\])      DEFAULT_RETURN="return [];" ;;
  *)          DEFAULT_RETURN="return null as any;" ;;
esac

# --- Build description comment ---
COMMENT=""
if [[ -n "$TITLE" || -n "$DESCRIPTION" ]]; then
  COMMENT="/**"$'\n'
  if [[ -n "$TITLE" ]]; then
    COMMENT+=" * ${NUMBER}. ${TITLE}"$'\n'
    COMMENT+=" *"$'\n'
  fi
  if [[ -n "$DESCRIPTION" ]]; then
    while IFS= read -r line; do
      COMMENT+=" * ${line}"$'\n'
    done <<< "$DESCRIPTION"
  fi
  COMMENT+=" */"$'\n'
fi

# --- solution.ts ---
cat > "${TS_DIR}/solution.ts" <<EOF
${COMMENT}export type ${TYPE_NAME} = (${PARAMS}) => ${RETURN_TYPE};

export const default${TYPE_NAME}: ${TYPE_NAME} = (${PARAMS}) => {
  ${DEFAULT_RETURN}
};

export const solutions: ${TYPE_NAME}[] = [default${TYPE_NAME}];
EOF

# --- _solution.test.ts ---
cat > "${PROBLEM_DIR}/_solution.test.ts" <<EOF
import { describe, test, expect } from "vitest";
import { solutions } from "./solution";

describe.each(solutions)("${FUNC} - %o", (${FUNC}) => {
  test("example 1", () => {
    // TODO: add test case
  });
});
EOF

echo "Scaffolded LeetCode #${NUMBER} (${NAME}) in ${DIR_NAME}"
echo "  ${TS_DIR}/solution.ts"
echo "  ${PROBLEM_DIR}/_solution.test.ts"
