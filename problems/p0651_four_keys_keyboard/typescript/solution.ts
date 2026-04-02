export type MaxAFn = (n: number) => number;

export const recursion: MaxAFn = (n: number) => {

  if (n <= 6) return n;

  let i = n - 3;
  let globalMax = n;

  while (i >= 1) {
    const multiplier = n - i - 1; // 1 original + (n-i-2) pastes
    const defaultMax = recursion(i);
    const max = defaultMax * multiplier;

    if (max > globalMax) globalMax = max;
    i--;
  }

  return globalMax;
};

export const dp: MaxAFn = (n:number) => {
  return 0;
}

export const solutions: MaxAFn[] = [recursion, dp];
