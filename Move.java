import java.util.Map;

public class Move {
    private int x;
    private int y;

    private static Map<Integer, String> cols = Map.of(0, "a", 1, "b",
            2, "c", 3, "d", 4, "e", 5, "f",
            6, "g", 7, "h");

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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