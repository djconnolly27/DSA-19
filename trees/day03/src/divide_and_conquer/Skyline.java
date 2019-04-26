package divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    public static class Point {
        public int x;
        public int y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Building {
        private int l, r, h;
        public Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    public static List<Point> skyline(Building[] B) {
        List<Point> arr = new ArrayList<>();
        if (B.length == 1) {
            Point p = new Point(B[0].l, B[0].h);
            Point p2 = new Point(B[0].r, 0);
            arr.add(p);
            arr.add(p2);
            return arr;
        }
        Building[] left = new Building[B.length/2];
        Building[] right = new Building[B.length - left.length];
        System.arraycopy(B, 0, left, 0, B.length/2);
        System.arraycopy(B, B.length/2, right, 0, B.length-left.length);
        List<Point> arr1 = skyline(left);
        List<Point> arr2 = skyline(right);
        arr = combine(arr1, arr2);

        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).y == arr.get(i-1).y) {
                arr.remove(i);
                i--;
            }
        }
        return arr;
    }

    public static List<Point> combine(List<Point> a, List<Point> b) {

        List<Point> arr = new ArrayList<>();
        int indexa = 0;
        int indexb = 0;
        int lastAy = 0;
        int lastBy = 0;
        while(indexa < a.size() || indexb < b.size()) {
            if (indexa >= a.size()) {
                arr.add(b.get(indexb));
                indexb++;
            } else if (indexb >= b.size()) {
                arr.add(a.get(indexa));
                indexa++;
            } else {
                Point ptA = a.get(indexa);
                Point ptB = b.get(indexb);
                if (ptA.x < ptB.x) {
                    arr.add(new Point(ptA.x, Math.max(ptA.y, lastBy)));
                    lastAy = ptA.y;
                    indexa++;
                } else if (ptA.x > ptB.x) {
                    arr.add(new Point(ptB.x, Math.max(ptB.y, lastAy)));
                    lastBy = ptB.y;
                    indexb++;
                } else {
                    arr.add(new Point(ptA.x, Math.max(ptA.y, ptB.y)));
                    lastAy = ptA.y;
                    lastBy = ptB.y;
                    indexa++;
                    indexb++;
                }

            }
        }
        if (arr.get(arr.size()-1).y == arr.get(arr.size()-2).y) arr.remove(arr.size()-1);
        return arr;
    }

}
