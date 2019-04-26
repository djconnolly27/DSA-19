import java.util.Collections;

public class LongestIncreasingSubsequence {

    // Runtime: O(n^2)
    // Space: O(n)
    public static int LIS(int[] A) {
        // Two arrays, one you go through each
        int[] x = new int[A.length];
        int max = 0;
        for (int k = 0; k < x.length; k++) {
            x[k] = 1;
        }
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j] && x[i] < x[j]+1) {
                    x[i]++;
                }
            }
            if (x[i] > max) {
                max = x[i];
            }
        }
        return max;
    }
}