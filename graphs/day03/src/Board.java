import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    private int blankY;
    private int blankX;
    public int[][] tiles;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{}};

//    private int[][] goal = new int[n][n];


    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        tiles = b;
        n = b.length;
        goal = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                goal[i][j] = i*n+j+1;
                if (tiles[i][j] == 0) {
                    blankX = j;
                    blankY = i;
                }
            }
        }
        goal[n-1][n-1] = 0;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int dist = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (tiles[y][x] != 0) {
                    int corrRow = (tiles[y][x] - 1) / n;
                    int corrCol = (tiles[y][x] - 1) % n;
                    int row = y;
                    int col = x;
                    dist = dist + Math.abs(row - corrRow) + Math.abs(col - corrCol);
//                    System.out.print("Dist: " + dist + " , ");
//                    System.out.println("Val: " + tiles[y][x]);
                }

            }
        }

        return dist;
    }



    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
//        return tiles.equals(goal);
        return manhattan() == 0;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        PriorityQueue minPQ = new PriorityQueue();
        int inversions = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                int curr = tiles[y][x];
                if (curr != 0) {
                    Iterator iter = minPQ.iterator();
                    int inv = 0;
                    while (iter.hasNext()) {
                        int next = (Integer) iter.next();
                        if (curr > next) {
                            inv++;
                        }
                    }
                    inversions = inversions + curr - inv - 1;
                    minPQ.offer(curr);
                }

            }
        }

        // https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
        if (n % 2 != 0 && inversions % 2 == 0) {
            return true;
        } else if (n % 2 == 0 && (n - blankY) % 2 == 0&& inversions % 2 != 0) {
            return true;
        } else if (n % 2 == 0 && (n - blankY) % 2 != 0 && inversions % 2 == 0) {
            return true;
        }
        return false;
    }

    public int[][] copyBoard() {
        int[][] copy = new int[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                copy[y][x] = tiles[y][x];
            }
        }
        return copy;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        LinkedList ll = new LinkedList<Board>();
        if (blankX > 0) {
            int[][] neighbor1 = copyBoard();
            neighbor1[blankY][blankX] = neighbor1[blankY][blankX-1];
            neighbor1[blankY][blankX-1] = 0;
            ll.add(new Board(neighbor1));
//            neighbors[blankY][blankX-1] = neighbors[blankY][blankX];
//            neighbors[blankY][blankX] = 0;
        }
        if (blankX < n-1) {
            int[][] neighbor2 = copyBoard();
            neighbor2[blankY][blankX] = neighbor2[blankY][blankX+1];
            neighbor2[blankY][blankX+1] = 0;
            ll.add(new Board(neighbor2));
//            tiles[blankY][blankX+1] = tiles[blankY][blankX];
//            tiles[blankY][blankX] = 0;
        }
        if (blankY > 0) {
            int[][] neighbor3 = copyBoard();
            neighbor3[blankY][blankX] = neighbor3[blankY-1][blankX];
            neighbor3[blankY-1][blankX] = 0;
            ll.add(new Board(neighbor3));
//            tiles[blankY-1][blankX] = tiles[blankY][blankX];
//            tiles[blankY][blankX] = 0;
        }
        if (blankY < n-1) {
            int[][] neighbor4 = copyBoard();
            neighbor4[blankY][blankX] = neighbor4[blankY+1][blankX];
            neighbor4[blankY+1][blankX] = 0;
            ll.add(new Board(neighbor4));
//            tiles[blankY+1][blankX] = tiles[blankY][blankX];
//            tiles[blankY][blankX] = 0;
        }
        return ll;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Goal: " + board.goal);
        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
        Iterator iter = it.iterator();
        while (iter.hasNext()) {
            Board b = (Board) iter.next();
            for (int y = 0; y < b.size(); y++) {
                for (int x = 0; x < b.size(); x++) {
                    System.out.print(b.tiles[y][x]);
                }
                System.out.println();
            }
            System.out.println();
//            System.out.println(b);
        }
    }
}
