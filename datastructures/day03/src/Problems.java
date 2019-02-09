import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        LinkedList<Integer> l = new LinkedList<>();
        int goal_len = A.length - k;
        l.add(A[0]);
        for (int i = 1; i < A.length; i++) {
            while (k > 0 && l.size() > 0 && A[i] < l.getLast()) {
                k--;
                l.removeLast();
            }
            if (l.size() == goal_len) {
                k--;
            } else {
                l.addLast(A[i]);
            }
        }
        return l;
    }

    public static boolean isPalindrome(Node n) {
        int size = 1;
        Node start = n;
        if (n == null) {
            return true;
        }
        while (n.next != null) {
            size++;
            n = n.next;
        }
        n = start;
        Node prev = null;
        Node curr = start;
        for (int i = 0; i < size/2; i++) {
            curr = start;
            start = start.next;
            curr.next = prev;
            prev = curr;
        }
        if (size % 2 != 0) {
            start = start.next;
        }
        for (int i = 0; i < size/2; i++) {
            if (start.val != curr.val) {
                return false;
            }
            start = start.next;
            curr = curr.next;
        }

        return true;
    }

    public static String infixToPostfix(String s) {
        Stack<Character> l = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= s.length() - 1; i=i+2) {
            char curr = s.charAt(i);
            if (curr == '(' || curr == '+' || curr == '-' || curr == '*' || curr == '/') {
                l.push(curr);
            } else if (curr == ')') {
                curr = l.pop();
                while (curr != '(') {
                    res.append(' ').append(curr);
                    curr = l.pop();
                }
            } else {
                if (res.length() == 0) {
                    res.append(curr);
                } else {
                    res.append(' ').append(curr);
                }
            }
        }
        return res.toString();
    }

}
