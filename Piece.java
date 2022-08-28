import java.util.ArrayList;
import java.util.Map;

/**
 * This is an abstract class Piece that will be used as a template for the
 * various chess pieces to inherit and implement.
 */
public abstract class Piece {

    /** Color toggle, true if piece is black and false if white */
    protected boolean isBlack;
    /** X coordinate of the piece */
    protected int x;
    /** Y coordinate of the piece */
    protected int y;
    /** Translating integers to column input for outputting movies */
    protected static Map<Integer, String> cols = Map.of(0, "a", 1, "b",
            2, "c", 3, "d", 4, "e", 5, "f",
            6, "g", 7, "h");
    /** ArrayList containing all the legal moves a piece may make */
    protected ArrayList<Move> legalMoves;

    /**
     * Creates a new Piece with a given color at (x,y)
     * 
     * @param isBlack Color of the piece, true if is black and white if false
     * @param x       X coordinate of the piece
     * @param y       Y coordinate of the piece
     */
    public Piece(boolean isBlack, int x, int y) {
        this.isBlack = isBlack;
        this.x = x;
        this.y = y;
        legalMoves = new ArrayList<Move>();
    }

    /**
     * Gets the X coordinate of the piece
     * 
     * @return X coordinate of the piece
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate of the piece
     * 
     * @return Y coordinate of the piece
     */
    public int getY() {
        return y;
    }

    /**
     * Gets a boolean representing the color of the piece. True if it is black,
     * false if it is white
     * 
     * @returns boolean representing the color of the piece. True if it is black,
     *          false if it is white
     */
    public boolean getColor() {
        return isBlack;
    }

    /**
     * Returns a string containing the color of the piece and the piece's name
     * 
     * @return
     */
    public abstract String getName();

    /**
     * Gets a one letter representation of the piece on the chess board
     * 
     * @return one letter representation of the piece, Letter is uppercase if piece
     *         is black, lowercase if piece is white
     */
    public abstract String getSymbol();

    /**
     * Moves a piece to (newX, newY)
     * 
     * @param newX Destination X coordinate
     * @param newY Destination Y coordinate
     */
    public void movePiece(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * Verifies coordinates are within bounds of the chessboard and that the
     * destination doesn't have a piece of the same color
     * 
     * @param newX Destination X coordinate
     * @param newY Destination Y coordinate
     * @return true if coordinates are valid, false if they are not
     */
    public boolean validCoordinates(int newX, int newY) {
        // out of bounds check
        if (newX < 0 || newY < 0 || newX > 7 || newY > 7)
            return false;

        // cant capture your own piece
        Piece other = Board.getPiece(newX, newY);
        if ((other != null) && (other.getColor() == isBlack))
            return false;

        return true;
    }

    /**
     * Checks if a piece can move to a location by verifying the coordinates are
     * valid and that there are no pieces in the way (unless its a knight moving).
     * 
     * @param newX Destination X coordinate
     * @param newY Destination Y coordinate
     * @return true if piece can move, false if it can not
     */
    public abstract boolean canMove(int newX, int newY);

    /**
     * Returns a string of legal moves for this piece in the form [piece
     * symbol][destination coordinates]
     * e.g. Ra2 for rook can move to a2
     * 
     * @return String representation of all legal moves
     */
    public String getLegalMoves() {
        String legalMovesString = "";
        for (Move move : legalMoves) {
            legalMovesString += getSymbol().toUpperCase() + move + " ";
        }
        return legalMovesString;
    }

    /** Updates the list of legal moves by checking all the squares around it */
    public abstract void updateLegalMoves();

    /**
     * Checks if piece has any legal moves
     * 
     * @return true if there is at least 1 legal move, false if none
     */
    public boolean hasLegalMoves() {
        if (legalMoves.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a given move is legal by checking the list of legalMoves it has
     * 
     * @return true if move is legal, false if not
     */
    public boolean isMoveLegal(Move move) {
        return legalMoves.contains(move);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Piece otherPiece = (Piece) o;
        // two pieces are the same if coordinates are the same and are the same class
        return (this.x == otherPiece.getX()) && (this.y == otherPiece.getY());
    }
}
