import java.lang.reflect.Array;
import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // Recursively find the median and put that value as root, then recursively
        // put the median of either side as the left/right children
        // Q: How to find median of list when i can't sort
        Collections.sort(values);
        Integer[] ll = new Integer[values.size()];
        List<Integer> l2 = new ArrayList<>();
        int lo = 0;
        int hi = values.size();

        l2 = reOrderList(values, l2, lo, hi);
        for (int i = 0; i < l2.size(); i++) {
            ll[i] = l2.get(i);
        }

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.addAll(ll);

        return bst;
    }

    public static List<Integer> reOrderList(List<Integer> sortedVals, List<Integer> ll, int lo, int hi) {
        if (lo >= hi) {
            return ll;
        }
        ll.add(sortedVals.get(lo+(hi-lo)/2));
        reOrderList(sortedVals, ll, lo, lo+(hi-lo)/2);
        reOrderList(sortedVals, ll, lo+(hi-lo)/2 + 1, hi);

        return ll;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
