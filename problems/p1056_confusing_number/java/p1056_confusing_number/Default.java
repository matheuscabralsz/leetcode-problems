package p1056_confusing_number;

import java.util.Map;

public class Default implements Solution {
    @Override
    public boolean confusingNumber(int n) {

        String str = String.valueOf(n);

        Map<Character, Character> rotable = Map.of(
                '0', '0',
                '1', '1',
                '6', '9',
                '8', '8',
                '9', '6'
        );

        StringBuilder rotatedString = new StringBuilder();

        int i = str.length() - 1;

        //TODO: try with lambda

        while (i >= 0) {
            char c = str.charAt(i);
            Character rotatedChar = rotable.get(c);

            if (rotatedChar == null) return false;

            rotatedString.append(rotatedChar);

            i--;
        }
        return !rotatedString.toString().equals(str);
    }
}
