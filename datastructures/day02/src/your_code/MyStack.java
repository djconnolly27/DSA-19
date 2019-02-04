package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxE;

    public MyStack() {
        ll = new LinkedList<>();
        maxE = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (maxE.isEmpty()) {
            maxE.addFirst(e);
        } else if (e >= maxE.getFirst()) {
            maxE.addFirst(e);
        }
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if (pop == maxE.getFirst()) {
            maxE.removeFirst();
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        Integer maxNum = maxE.getFirst();
        return maxNum;
    }
}
