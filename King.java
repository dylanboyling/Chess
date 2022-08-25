import java.util.ArrayList;

// This class represents the King piece in Chess
public class King extends Piece {

    // Constructor using isBlack yor toggle, true if the piece is black. White
    // otherwise
    public King(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    // Returns a string containing the your of the piece and the piece's name
    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " king";
    }

    // Returns the first letter of piece
    // Letter is uppercase if piece is black, lowercase if piece is white
    @Override
    public String getSymbol() {
        if (isBlack)
            return "K";
        else
            return "k";
    }

    public boolean isCheckmate(){

        
        return false;
    }

    /**
     * Returns true if King is in check at its current location, false if it is not.
     * 
     * @returns check status, true if king is in check and false if not
     */
    public boolean isChecked() {

        if (isBlack) {
            ArrayList<Piece> white = Board.getWhitePieces();
            for (Piece p : white) {
                if (p.canMove(x, y))
                    return true;
            }
        } else {
            ArrayList<Piece> black = Board.getBlackPieces();
            for (Piece p : black) {
                if (p.canMove(x, y))
                    return true;
            }
        }

        return false;
    }

    /**
     * Returns true if King will be in check if it moves to (testX, testY), false if
     * not
     * 
     * @param testX X coord that the king might move to
     * @param testY Y coord that the king might move to
     * @returns check status, true if king is in check and false if not
     */
    public boolean isChecked(int testX, int testY) {

        if (isBlack) {
            ArrayList<Piece> white = Board.getWhitePieces();
            for (Piece p : white) {
                if (p.canMove(testX, testY))
                    return true;
            }
        } else {
            ArrayList<Piece> black = Board.getBlackPieces();
            for (Piece p : black) {
                if (p.canMove(testX, testY))
                    return true;
            }
        }

        return false;
    }

    @Override
    public boolean canMove(int newX, int newY) {
        // figure out direction, up and right are positive numbers
        int xDir = newX - x;
        int yDir = y - newY;

        // can only move 1 space in any direction
        if (Math.abs(xDir) > 1 || Math.abs(yDir) > 1 || xDir == 0 && yDir == 0)
            return false;

        // verifies king won't be in check at new location
        if (this.isChecked(newX, newY))
            return false;

        return true;
    }

    // Prints all of the valid moves (coordintes) that the rook may move to from
    // (x, y)
    @Override
    public String getLegalMoves() {
        return "Move functionality not implemented for this piece.";
    }
}