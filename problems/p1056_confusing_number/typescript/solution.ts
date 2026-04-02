/**
 * 1056. Confusing Number
 *
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid. Valid rotations: 0->0, 1->1, 6->9, 8->8, 9->6. Given an integer n, return true if it is a confusing number, or false otherwise.
 */
export type ConfusingNumberFn = (n: number) => boolean;

export const defaultConfusingNumberFn: ConfusingNumberFn = (n: number) => {
  let str = n.toString();

  const rotable = {
    '0': '0',
    '1': '1',
    '6': '9',
    '8': '8',
    '9': '6',
  }

  let rotatedString = '';

  let i = str.length - 1;
  while (i >= 0) {
    const char = str[i]
    let rotatedChar = rotable[char]
    if (rotatedChar) {
      rotatedString += rotatedChar;
    } else {
      return false
    }

    i--
  }
  return rotatedString !== str
};

export const solutions: ConfusingNumberFn[] = [defaultConfusingNumberFn];
