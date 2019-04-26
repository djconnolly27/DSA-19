import java.util.ArrayList;
import java.util.List;

public class DiceRollSum {

    // Runtime: O(
    // Space: TODO
    public static int diceRollSum(int N) {
        int memo[] = new int[N+1];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
        int solution = 0;
        solution = diceRecurse(memo, N);
        return solution;
    }

    public static int diceRecurse(int[] memo, int n) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 0;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = diceRecurse(memo, n-1) + diceRecurse(memo, n-2) + diceRecurse(memo, n-3) + diceRecurse(memo, n-4) + diceRecurse(memo, n-5) + diceRecurse(memo, n-6);
        return memo[n];
    }

}
