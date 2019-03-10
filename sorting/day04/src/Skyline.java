import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
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
//        if (arr.size() >= 1) {
//            System.out.print(arr.get(0).x);
//            System.out.println(arr.get(0).y);
//        }
//        if (arr.size() >= 2) {
//            System.out.print(arr.get(1).x);
//            System.out.println(arr.get(1).y);
//        }
//        if (arr.size() >= 3) {
//            System.out.print(arr.get(2).x);
//            System.out.println(arr.get(2).y);
//        }
//
//
        return arr;
    }

    public static List<Point> combine(List<Point> a, List<Point> b) {

        List<Point> arr = new ArrayList<>();
        int currHeight = 0;
        int indexa = 0;
        int indexb = 0;
        int ha = 0;
        int hb = 0;
        for (int i = 0; i < a.size() + b.size(); i++) {
//            Point ptA = a.get(indexa);
//            Point ptB = b.get(indexb);
//            if (ptA.x < ptB.x) {
//                h1 = ptA.y;
//                arr.add(new Point(ptA.x, Math.max(h1, h2)));
//            } else {
//                h2 = ptB.y;
//                arr.add(new Po//            }int(ptB.x, Math.max(h1, h2)));
//            }

            if (indexa >= a.size()) {
                arr.add(b.get(indexb));
                indexb++;
            } else if (indexb >= b.size()) {
                arr.add(a.get(indexa));
                indexa++;
            } else {
                Point ptA = a.get(indexa);
                Point ptB = b.get(indexb);
                if (ptB.x < ptA.x) {
                    if (ptB.y > currHeight) {
                        arr.add(ptB);
                        currHeight = ptB.y;
                        indexb++;
                    } else if (indexa > 0) {
                        arr.add(new Point(ptB.x, a.get(indexa - 1).y));
                        currHeight = a.get(indexa - 1).y;
                        indexb++;
                    }

//                    else if (indexb < b.size() - 1 && b.get(indexb + 1).x > ptA.x) {
//                        Point newPoint = new Point(ptA.x, ptB.y);
//                        arr.add(newPoint);
//                        currHeight = newPoint.y;
//                        indexa++;
//                    } else if () {
//
//                    }
                } else if (ptA.x < ptB.x) {
                    if (ptA.y > currHeight) {
                        arr.add(ptA);
                        currHeight = ptA.y;
                        indexa++;
                    } else if (indexb > 0) {
                        arr.add(new Point(ptA.x, b.get(indexb - 1).y));
                        currHeight = b.get(indexb - 1).y;
                        indexa++;
                    }

//                    else if (indexa < a.size() - 1 && a.get(indexa + 1).x > ptB.x) {
//                        Point newPoint = new Point(ptB.x, ptA.y);
//                        arr.add(newPoint);
//                        currHeight = newPoint.y;
//                        indexb++;
//                    }
                } else {
                    if (ptA.y >= ptB.y) {
                        arr.add(new Point(ptA.x, ptA.y));
                        currHeight = ptA.y;
                        indexa++;
                        indexb++;
                    } else {
                        arr.add(new Point(ptB.x, ptB.y));
                        currHeight = ptB.y;
                        indexa++;
                        indexb++;
                    }
                }
            }

        }

        return arr;
    }

}
