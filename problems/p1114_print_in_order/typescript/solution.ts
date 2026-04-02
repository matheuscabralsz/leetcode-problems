/**
 * 1114. Print in Order
 *
 * Design a mechanism to ensure that first() is always called before second(),
 * and second() is always called before third(), regardless of call order.
 *
 * Uses real worker threads with SharedArrayBuffer + Atomics for synchronization.
 */

import { Worker } from "node:worker_threads";
import { join, dirname } from "node:path";
import { fileURLToPath } from "node:url";

export type PrintInOrderFn = (order: number[]) => Promise<string>;

const __dirname = dirname(fileURLToPath(import.meta.url));
const workerPath = join(__dirname, "worker.js");

export const withAtomics: PrintInOrderFn = (order: number[]) => {
  return new Promise<string>((resolve, reject) => {
    // Shared memory visible to all threads (main + workers).
    // SharedArrayBuffer is the only way threads can share state in Node.js.

    // Tracks progress: 0 = nothing done, 1 = first done, 2 = second done.
    // Workers use Atomics.wait() to block until this reaches their required value,
    // and Atomics.store() + Atomics.notify() to advance it and wake waiting threads.
    const stateBuffer = new SharedArrayBuffer(4);

    // Records which method printed at each position (3 slots, one per method).
    // Each worker atomically claims the next index, so the final array reflects
    // the actual execution order — which should always be [1, 2, 3].
    const outputBuffer = new SharedArrayBuffer(3 * 4);

    // A single atomic counter workers use to claim the next write position
    // in outputBuffer. Atomics.add() returns the old value and increments,
    // so concurrent workers never write to the same slot.
    const outputIdxBuffer = new SharedArrayBuffer(4);

    let completed = 0;
    const names = ["", "first", "second", "third"];

    // Spawn one real OS thread per method. The `order` array controls which
    // threads start first — e.g. [3,1,2] starts third's thread before first's.
    // The synchronization in worker.js ensures correct output regardless.
    for (const method of order) {
      const worker = new Worker(workerPath, {
        // Each worker receives the same shared buffers (zero-copy, same memory)
        // plus which method (1/2/3) it should execute.
        workerData: { stateBuffer, outputBuffer, outputIdxBuffer, method },
      });

      worker.on("error", reject);
      worker.on("exit", () => {
        completed++;
        // Once all 3 workers have exited, read the shared output buffer
        // to build the result string. If synchronization worked correctly,
        // this will always be "firstsecondthird".
        if (completed === 3) {
          const output = new Int32Array(outputBuffer);
          resolve(names[output[0]] + names[output[1]] + names[output[2]]);
        }
      });
    }
  });
};

export const solutions: PrintInOrderFn[] = [withAtomics];
