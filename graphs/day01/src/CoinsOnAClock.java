import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> result = new ArrayList<>();
        char[] clock = new char[hoursInDay];
        coinsHelper(pennies,nickels,dimes,0,hoursInDay,clock,result);
        return result;
    }

    public static void coinsHelper(int pennies, int nickels, int dimes, int currHour, int hours, char[] clock, List<char[]> solutions) {
        if (pennies == 0 && nickels == 0 && dimes == 0) {
            char[] newSolution = new char[hours];
            System.arraycopy(clock,0,newSolution,0,hours);
            solutions.add(newSolution);
        }
        if (clock[currHour] == 0) {
            if (pennies != 0) {
                clock[currHour] = 'p';
                coinsHelper(pennies-1, nickels, dimes, (currHour+1)%hours, hours, clock, solutions);
                clock[currHour] = 0;
            }
            if (nickels != 0) {
                clock[currHour] = 'n';
                coinsHelper(pennies, nickels-1, dimes, (currHour+5)%hours, hours, clock, solutions);
                clock[currHour] = 0;
            }
            if (dimes != 0) {
                clock[currHour] = 'd';
                coinsHelper(pennies, nickels, dimes-1, (currHour+10)%hours, hours, clock, solutions);
                clock[currHour] = 0;
            }
        }

    }
}
