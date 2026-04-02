const { workerData } = require("node:worker_threads");
const { stateBuffer, outputBuffer, outputIdxBuffer, method } = workerData;
const state = new Int32Array(stateBuffer);
const output = new Int32Array(outputBuffer);
const outputIdx = new Int32Array(outputIdxBuffer);

function print(id) {
  const idx = Atomics.add(outputIdx, 0, 1);
  Atomics.store(output, idx, id);
}

if (method === 1) {
  print(1);
  Atomics.store(state, 0, 1);
  Atomics.notify(state, 0);
} else if (method === 2) {
  while (Atomics.load(state, 0) < 1) {
    Atomics.wait(state, 0, 0);
  }
  print(2);
  Atomics.store(state, 0, 2);
  Atomics.notify(state, 0);
} else if (method === 3) {
  while (Atomics.load(state, 0) < 2) {
    const val = Atomics.load(state, 0);
    if (val < 2) Atomics.wait(state, 0, val);
  }
  print(3);
}
