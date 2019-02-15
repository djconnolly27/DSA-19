import java.util.HashMap;
import java.lang.Math;
public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        HashMap<Double, Integer> boomHash = new HashMap<>();
        int total = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i==j) { continue; }
                double dist = Math.sqrt(Math.pow((points[i][0] - points[j][0]),2) + Math.pow((points[i][1] - points[j][1]),2));
                boomHash.put(dist, boomHash.getOrDefault(dist, 0)+1);
            }
            for (int val: boomHash.values()) {
                total += val*(val-1);
            }
            boomHash.clear();
        }
        return total;
    }
}

