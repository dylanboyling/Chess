import java.util.Map;

/**
 * This class represents a move on the chess board. It is mainly used for
 * storing and printing legal moves a player may make in an ArrayList.
 */
public class Move {
    /** X coordinate on the board */
    private int x;
    /** Y coordinate on the baord */
    private int y;
    /** Used for translation column number into user friendly letters */
    private static Map<Integer, String> cols = Map.of(0, "a", 1, "b",
            2, "c", 3, "d", 4, "e", 5, "f",
            6, "g", 7, "h");

    /**
     * Creates a move a player may make on the board using the values a piece is
     * accessed from the array representing the chessboard.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets X coordinate
     * 
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets Y coordinate
     * 
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Translates array coordinates into destination coordinates a user may move a
     * piece to
     */
    public String toString() {
        return cols.get(x) + "" + (8 - y);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Move otherMove = (Move) o;
        return (x == otherMove.getX()) && (y == otherMove.getY());
    }
}