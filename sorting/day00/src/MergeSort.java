
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     *
     * Best-case runtime: O(nlog(n))
     * Worst-case runtime: O(nlog(n))
     * Average-case runtime: O(nlog(n))
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length<=1) {
            return array;
        }
        int[] left = new int[array.length/2];
        int[] right = new int[array.length-left.length];
        System.arraycopy(array, 0, left, 0, array.length/2);
        System.arraycopy(array, array.length/2, right,0, array.length-left.length);
        int[] sortL = sort(left);
        int[] sortR = sort(right);
        array = merge(sortL, sortR);
        return array;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int indexa = 0;
        int indexb = 0;
        int[] arr = new int[a.length+b.length];
        for (int i = 0; i < a.length+b.length; i++) {
            if (indexa >= a.length) {
                arr[i] = b[indexb];
                indexb++;
            } else if (indexb >= b.length) {
                arr[i] = a[indexa];
                indexa++;
            } else if (a[indexa] < b[indexb]) {
                arr[i] = a[indexa];
                indexa++;
            } else {
                arr[i] = b[indexb];
                indexb++;
            }
        }
        return arr;
    }

}