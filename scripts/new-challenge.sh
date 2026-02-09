#!/usr/bin/env bash
set -euo pipefail

# Usage: new-challenge.sh <number> <snake_case_name> <methodName> <returnType> <params>
# Example: new-challenge.sh 1 two_sum twoSum "int[]" "int[] nums, int target"

if [[ $# -lt 5 ]]; then
  echo "Usage: $0 <number> <snake_case_name> <methodName> <returnType> <params>"
  echo "Example: $0 1 two_sum twoSum 'int[]' 'int[] nums, int target'"
  exit 1
fi

NUMBER="$1"
NAME="$2"
METHOD="$3"
RETURN_TYPE="$4"
PARAMS="$5"

# Zero-pad number to 4 digits
PADDED=$(printf "%04d" "$NUMBER")

PACKAGE="p${PADDED}_${NAME}"
PKG_DOT="com.leetcode.${PACKAGE}"

# Resolve project root (parent of scripts/)
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

MAIN_DIR="${PROJECT_ROOT}/src/main/java/com/leetcode/${PACKAGE}"
TEST_DIR="${PROJECT_ROOT}/src/test/java/com/leetcode/${PACKAGE}"

if [[ -d "$MAIN_DIR" ]]; then
  echo "Error: package already exists: ${MAIN_DIR}"
  exit 1
fi

mkdir -p "$MAIN_DIR" "$TEST_DIR"

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
cat > "${MAIN_DIR}/Solution.java" <<EOF
package ${PKG_DOT};

public interface Solution {
    ${RETURN_TYPE} ${METHOD}(${PARAMS});
}
EOF

# --- Default.java (stub implementation) ---
cat > "${MAIN_DIR}/Default.java" <<EOF
package ${PKG_DOT};

public class Default implements Solution {
    @Override
    public ${RETURN_TYPE} ${METHOD}(${PARAMS}) {
        ${DEFAULT_RETURN}
    }
}
EOF

# --- SolutionTest.java (parameterized test template) ---
cat > "${TEST_DIR}/SolutionTest.java" <<EOF
package ${PKG_DOT};

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

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
echo "  ${MAIN_DIR}/Solution.java"
echo "  ${MAIN_DIR}/Default.java"
echo "  ${TEST_DIR}/SolutionTest.java"
