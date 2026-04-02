#!/usr/bin/env bash
set -euo pipefail

# Usage: new-challenge.sh <number> <snake_case_name> <methodName> <returnType> <params> [title] [description]
# Example: new-challenge.sh 1 two_sum twoSum "int[]" "int[] nums, int target" "Two Sum" "Given an array..."

if [[ $# -lt 5 ]]; then
  echo "Usage: $0 <number> <snake_case_name> <methodName> <returnType> <params> [title] [description]"
  echo "Example: $0 1 two_sum twoSum 'int[]' 'int[] nums, int target' 'Two Sum' 'Given an array...'"
  exit 1
fi

NUMBER="$1"
NAME="$2"
METHOD="$3"
RETURN_TYPE="$4"
PARAMS="$5"
TITLE="${6:-}"
DESCRIPTION="${7:-}"

# Zero-pad number to 4 digits
PADDED=$(printf "%04d" "$NUMBER")

PACKAGE="p${PADDED}_${NAME}"

# Resolve project root (parent of scripts/)
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

PROBLEM_DIR="${PROJECT_ROOT}/problems/${PACKAGE}"
JAVA_DIR="${PROBLEM_DIR}/java/${PACKAGE}"

if [[ -d "$JAVA_DIR" ]]; then
  echo "Error: Java directory already exists: ${JAVA_DIR}"
  exit 1
fi

mkdir -p "$JAVA_DIR"

# --- Build default return statement based on return type ---
case "$RETURN_TYPE" in
  boolean)  DEFAULT_RETURN="return false;" ;;
  int)      DEFAULT_RETURN="return 0;" ;;
  long)     DEFAULT_RETURN="return 0L;" ;;
  double)   DEFAULT_RETURN="return 0.0;" ;;
  float)    DEFAULT_RETURN="return 0.0f;" ;;
  char)     DEFAULT_RETURN="return '\\0';" ;;
  void)     DEFAULT_RETURN="// TODO" ;;
  *)        DEFAULT_RETURN="return null;" ;;
esac

# --- Solution.java (interface) ---
{
  echo "package ${PACKAGE};"
  echo ""
  if [[ -n "$TITLE" || -n "$DESCRIPTION" ]]; then
    echo "/**"
    if [[ -n "$TITLE" ]]; then
      echo " * ${NUMBER}. ${TITLE}"
      echo " *"
    fi
    if [[ -n "$DESCRIPTION" ]]; then
      while IFS= read -r line; do
        echo " * ${line}"
      done <<< "$DESCRIPTION"
    fi
    echo " */"
  fi
  echo "public interface Solution {"
  echo "    ${RETURN_TYPE} ${METHOD}(${PARAMS});"
  echo "}"
} > "${JAVA_DIR}/Solution.java"

# --- Default.java (stub implementation) ---
cat > "${JAVA_DIR}/Default.java" <<EOF
package ${PACKAGE};

public class Default implements Solution {
    @Override
    public ${RETURN_TYPE} ${METHOD}(${PARAMS}) {
        ${DEFAULT_RETURN}
    }
}
EOF

# --- _SolutionTest.java (parameterized test template) ---
cat > "${JAVA_DIR}/_SolutionTest.java" <<EOF
package ${PACKAGE};

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class _SolutionTest {

    static Stream<Solution> solutions() {
        return Stream.of(
                new Default()
        );
    }

    @ParameterizedTest
    @MethodSource("solutions")
    void example1(Solution solution) {
        // TODO: add test case
    }
}
EOF

echo "Scaffolded LeetCode #${NUMBER} (${NAME}) in ${PACKAGE}"
echo "  ${JAVA_DIR}/Solution.java"
echo "  ${JAVA_DIR}/Default.java"
echo "  ${JAVA_DIR}/_SolutionTest.java"
