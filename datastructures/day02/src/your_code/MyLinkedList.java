package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node myNode = new Node(c);
        if (size == 0) {
            head = myNode;
            tail = myNode;
        } else {
            tail.next = myNode;
            myNode.prev = tail;
            tail = myNode;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        Node myNode = new Node(c);
        if (size == 0) {
            head = myNode;
            tail = myNode;
        } else {
            head.prev = myNode;
            myNode.next = head;
            head = myNode;
        }
        size++;
    }

    public Chicken get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node myNode = head;
        for (int i = 0; i < index; i++) {
            myNode = myNode.next;
        }
        return myNode.val;
    }

    public Chicken remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.val;
    }

    public Chicken removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Chicken temp = head.val;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            size--;
            return temp;
        }
    }

    public Chicken removeLast() {
        if (size == 0) {
            return null;
        } else {
            Chicken temp = tail.val;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            size--;
            return temp;
        }
    }
}