import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            int curr = 0;
            IndexPQ<Integer> pq = new IndexPQ<>(G.numberOfV());
            HashSet<Integer> visited = new HashSet<>();
            HashMap<Integer, Integer> tracking = new HashMap<>();
            int cost = 0;
            visited.add(curr);
            tracking.put(curr, 0);

//            for (int vertex : G.vertices) {
//                if (vertex == 0) continue;
//
//            }



            while (visited.size() != G.numberOfV()) {
                for (Edge e : G.edges(curr)) {
                    int other = e.other(curr);
                    int weight = e.weight();
                    if (tracking.containsKey(other) && weight < tracking.get(other)) {
                        tracking.replace(other, weight);
                        if (pq.contains(other)) {
                            pq.changeKey(other, weight);
                        } else if (!visited.contains(other)) {
                            pq.insert(other, weight);
                        }
                    } else if (!tracking.containsKey(other)) {
                        tracking.put(other, weight);
                        if (!visited.contains(other) && !pq.contains(other)) {
                            pq.insert(other, weight);
                        }
                    }
                }

                int min = pq.delMin();
                while (visited.contains(min)) {
                    min = pq.delMin();
                }

                visited.add(min);
                cost += tracking.get(min);
                curr = min;
            }
            return cost;
        }

    }

