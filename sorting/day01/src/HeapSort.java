public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        if (size-1 >= leftChild(i)) {
            int maxIdx = leftChild(i);
            if (size-1 >= rightChild(i) && heap[rightChild(i)] > heap[leftChild(i)]) {
                maxIdx = rightChild(i);
            }
            if (heap[i] < heap[maxIdx]) {
                swap(heap, i, maxIdx);
                sink(maxIdx);
            }
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            swap(heap, 0, i);
            size--;
            sink(0);
        }

        return heap;
    }
}
