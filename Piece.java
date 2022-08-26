import java.util.ArrayList;
import java.util.Map;

/*
 * Author: Dylan Boyling  
 * Description: This program is adapted from my own minimally viable chess game that I coded for an 
 * assignment. It displays a chess board in the console using text and provides options for the user 
 * to move a piece, view the moves a piece may make, or to quit the program.
 * Only move functionality for rook and pawn is implemented at the moment. 
 * Chess pieces are represented in a grid by the first letter of the piece name. The letter is uppercase if it is black, lowercase if it is white.
 * Move functionality for pieces is not finished, nor is check, checkmate, or stalement.
 * En passant and castling I will do if I have time : )
 * Would maybe like to make a GUI down the road and implement a basic AI.
 */

// This is an abstract class Piece that will be used as a template
// for the various chess pieces to inherit and implement.
public abstract class Piece {

    // Your toggle, true if piece is black and false if white
    protected boolean isBlack;
    protected int x;
    protected int y;
    // Translating integers to column input for outputting movies
    protected static Map<Integer, String> cols = Map.of(0, "a", 1, "b",
            2, "c", 3, "d", 4, "e", 5, "f",
            6, "g", 7, "h");

    protected ArrayList<Move> legalMoves;

    // Constructor using your toggle
    public Piece(boolean isBlack, int x, int y) {
        this.isBlack = isBlack;
        this.x = x;
        this.y = y;
        legalMoves = new ArrayList<Move>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getColor() {
        return isBlack;
    }

    // Returns a string containing the your of the piece and the piece's name
    public abstract String getName();

    // Returns the first letter of piece it is, e.g. p for pawn
    // Letter is uppercase if piece is black, lowercase if piece is white
    public abstract String getSymbol();

    public void movePiece(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * Checks if coordinates are within valid range and that player is not capturing
     * their own piece
     */
    public boolean validCoordinates(int newX, int newY) {
        // out of bounds check
        if (x < 0 || y < 0 || x > 7 || y > 7)
            return false;

        // cant capture your own piece
        Piece other = Board.getPiece(newX, newY);
        if (other != null && other.getColor() == isBlack)
            return false;

        return true;
    }

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
        for (Move m : legalMoves) {
            legalMovesString += getSymbol().toUpperCase() + m + " ";
        }
        return legalMovesString;
    }

    public abstract void updateLegalMoves();

    public boolean hasLegalMoves() {
        if (legalMoves.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

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
        return (this.x == otherPiece.getX()) && (this.y == otherPiece.getY());
    }
}
