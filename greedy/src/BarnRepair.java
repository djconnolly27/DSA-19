import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        Arrays.sort(occupied);
        int covered = occupied[occupied.length-1] - occupied[0] + 1;
        PriorityQueue<Integer> gaps = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 1; i < occupied.length; i++) {
            int dist = occupied[i] - occupied[i-1];
            if (dist > 1) {
                gaps.offer(dist-1);
            }
        }

        while (M > 1 && gaps.size() > 0) {
            int space = gaps.poll();
            covered -= space;
            M--;
        }
        return covered;
    }

}