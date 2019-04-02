import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> permutations = new LinkedList<>();
        LinkedList<Integer> unused = new LinkedList<>(A);
        LinkedList<Integer> current = new LinkedList<>();
        permutationsHelper(unused, current, permutations);
        return permutations;
    }

    public static void permutationsHelper(LinkedList<Integer> unused, LinkedList<Integer> current, List<List<Integer>> permutations) {
        if (unused.size() == 0) {
            permutations.add(new LinkedList<>(current));
        }


        for (int i = 0; i < unused.size(); i++) {
            current.add(unused.get(i));
            unused.remove(i);

            permutationsHelper(unused, current, permutations);

            unused.add(i, current.getLast());
            current.removeLast();


        }
    }



}
