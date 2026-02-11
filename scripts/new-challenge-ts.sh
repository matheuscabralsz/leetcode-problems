#!/usr/bin/env bash
set -euo pipefail

# Usage: new-challenge-ts.sh <number> <kebab-case-name> <functionName> <returnType> <params> [title] [description]
# Example: new-challenge-ts.sh 1 two-sum twoSum "number[]" "nums: number[], target: number" "Two Sum" "Given an array..."

if [[ $# -lt 5 ]]; then
  echo "Usage: $0 <number> <kebab-case-name> <functionName> <returnType> <params> [title] [description]"
  echo "Example: $0 1 two-sum twoSum 'number[]' 'nums: number[], target: number' 'Two Sum' 'Given an array...'"
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

DIR_NAME="p${PADDED}-${NAME}"

# Resolve project root (parent of scripts/)
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

SRC_DIR="${PROJECT_ROOT}/typescript/src/${DIR_NAME}"

if [[ -d "$SRC_DIR" ]]; then
  echo "Error: directory already exists: ${SRC_DIR}"
  exit 1
fi

mkdir -p "$SRC_DIR"

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
cat > "${SRC_DIR}/solution.ts" <<EOF
${COMMENT}export type ${TYPE_NAME} = (${PARAMS}) => ${RETURN_TYPE};

export const default${TYPE_NAME}: ${TYPE_NAME} = (${PARAMS}) => {
  ${DEFAULT_RETURN}
};

export const solutions: ${TYPE_NAME}[] = [default${TYPE_NAME}];
EOF

# --- solution.test.ts ---
cat > "${SRC_DIR}/solution.test.ts" <<EOF
import { describe, test, expect } from "vitest";
import { solutions } from "./solution";

describe.each(solutions)("${FUNC} - %o", (${FUNC}) => {
  test("example 1", () => {
    // TODO: add test case
  });
});
EOF

echo "Scaffolded LeetCode #${NUMBER} (${NAME}) in ${DIR_NAME}"
echo "  ${SRC_DIR}/solution.ts"
echo "  ${SRC_DIR}/solution.test.ts"
