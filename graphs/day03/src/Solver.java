/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;
    private Board initBoard;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            cost = board.manhattan() + moves;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        public int compareTo(State s) {
            return this.cost - s.cost;
        }

    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        while (state.prev != null) {
            state = state.prev;
        }
        return state;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        initBoard = initial;
        solution();

    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return initBoard.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        if (!initBoard.solvable()) {
            return null;
        }

        PriorityQueue<State> queue = new PriorityQueue<State>(State::compareTo);
        State start = new State(initBoard,0,null);
        HashSet<State> visited = new HashSet<State>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (curr.board.isGoal()) {
                solutionState = curr;
                minMoves = curr.moves;
                solved = true;
                LinkedList<Board> ll = new LinkedList<Board>();
                while (curr.prev != null) {
                    ll.addFirst(curr.board);
                    curr = curr.prev;
                }
                return ll;
            }
            Iterable<Board> neighbors = curr.board.neighbors();
            for (Board b : neighbors) {
                State s = new State(b, curr.moves+1 ,curr);
                if (!visited.contains(s)) {
                    visited.add(s);
                    queue.offer(s);
                }
            }


        }
        return null;

    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
