public class MyArrayList {
    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // Runtime: O(1)
    public void add(Cow c) {
        if (size == elems.length - 1) {
            Cow[] newArray = new Cow[elems.length*2];
            System.arraycopy(elems, 0, newArray, 0, size);
            elems = newArray;
        }
        elems[size] = c;
        size++;
    }

    // Runtime: O(1)
    public int size() {
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elems[index];
    }

    // Runtime: O(N)
    public Cow remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Cow removed = elems[index];
        for (int i = index; i < size; i++) {
            elems[i] = elems[i+1];
        }
        size--;
        if (size <= (elems.length/4)) {
            Cow[] newArray = new Cow[elems.length/2];
            System.arraycopy(elems, 0, newArray, 0, size);
            elems = newArray;
        }
        return removed;
    }

    // Runtime: O(N)
    public void add(int index, Cow c) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == elems.length - 1) {
            Cow[] newArray = new Cow[elems.length*2];
            System.arraycopy(elems, 0, newArray, 0, size);
            elems = newArray;
        }
        for (int i = size+1; i > index; i--) {
            elems[i] = elems[i-1];
        }
        elems[index] = c;
        size++;
    }
}