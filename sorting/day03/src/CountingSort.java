public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        int[] arr = new int[max+1];
        for (int i = 0; i < A.length; i++) {
            arr[A[i]]++;
        }
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] > 0) {
                A[idx] = i;
                idx++;
                arr[i]--;
            }
        }
    }

}
