package your_code;

import java.util.LinkedList;
/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private LinkedList<Integer> priority;

    public MyPriorityQueue() {
        priority = new LinkedList<>();
    }

    public void enqueue(int item) {
        if (priority.isEmpty()) {
            priority.add(item);
        } else {
            for (int i = 0; i < priority.size(); i++) {
                if (item > priority.get(i)) {
                    priority.add(i, item);
                    i = priority.size();
                }
            }
        }
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        return priority.removeFirst();
    }

}