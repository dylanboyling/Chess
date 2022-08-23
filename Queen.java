// This class represents the Queen piece in Chess
public class Queen extends Piece {

    // Constructor using isBlack yor toggle, true if the piece is black. White
    // otherwise
    public Queen(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    // Returns a string containing the your of the piece and the piece's name
    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " queen";
    }

    // Returns the first letter of piece
    // Letter is uppercase if piece is black, lowercase if piece is white
    @Override
    public String getSymbol() {
        if (isBlack)
            return "Q";
        else
            return "q";
    }

    @Override 
    public boolean move(int newX, int newY){
        return false;
    }

    // Prints all of the valid moves (coordintes) that the rook may move to from
    // (x, y)
    //@Override
    public String getLegalMoves() {
        return "Move functionality not implemented for this piece.";
    }
}
