import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.sort;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {

     // Attempt to solve with sorting, moving in one by one:
        sort(arr);
        int total = 0;
        for (int i = 0; i < arr.length-2; i++) {
            int smaller = i+1;
            int larger = arr.length-1;
            while (smaller < larger) {
                if (arr[i] + arr[smaller] + arr[larger] == sum) {
                    total++;
                    smaller++;
                }
                if (arr[i] + arr[smaller] + arr[larger] < sum) {
                    smaller++;
                } else {
                    larger--;
                }
            }
        }

        return total;
    }
}

// Attempt to solve with Hashmap: (passed 3 of 6 tests)

//        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
//
//        for (int i = 0; i < arr.length; i++) {
//            if (map.get(arr[i]) == null) {
//                ArrayList<Integer> ll = new ArrayList<>();
//                ll.add(i);
//                map.put(arr[i], ll);
//            } else {
//                ArrayList<Integer> ll = map.get(arr[i]);
//                ll.add(i);
//                map.put(arr[i], ll);
//            }
//        }
//        int total = 0;
//        for (int i = 0; i < arr.length-1; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                System.out.print(map.get(sum - (arr[i]+arr[j])));
//                if (map.get(sum - (arr[i] + arr[j])) != null && !map.get(sum - (arr[i]+arr[j])).contains(i) && !map.get(sum - (arr[i]+arr[j])).contains(j)) {
//                    total++;
//                }
//            }
//        }


//        for (int i = 0; i < arr.length-1; i++) {
//            for (int j = i+1; j < arr.length; j++) {
//                if (map.get(arr[i] + arr[j]) == null) {
//                    ArrayList<Integer> ll = new ArrayList<>();
//                    ll.add(i);
//                    ll.add(j);
//                    map.put(arr[i] + arr[j], ll);
//                } else {
//                    if (map.get(arr[i] + arr[j]).contains(i)) {
//                        ArrayList<Integer> lx = map.get(arr[i] + arr[j]);
//                        lx.add(j);
//                        map.put(arr[i] + arr[j], lx);
//                    } else if (map.get(arr[i] + arr[j]).contains(j)) {
//                        ArrayList<Integer> lx = map.get(arr[i] + arr[j]);
//                        lx.add(i);
//                        map.put(arr[i] + arr[j], lx);
//                    } else {
//                        ArrayList<Integer> lx = map.get(arr[i] + arr[j]);
//                        lx.add(i);
//                        lx.add(j);
//                        map.put(arr[i] + arr[j], lx);
//                    }
//                }
//
//            }
//        }
//        int total = 0;
//        for (int i = 0; i < arr.length; i++) {
//            if (map.get(sum - arr[i]) != null && !map.get(sum - arr[i]).contains(i)) {
//                total++;
//            }
//        }
