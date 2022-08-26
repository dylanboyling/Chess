// This class represents the Knight piece in Chess
public class Knight extends Piece {

    // Constructor using isBlack yor toggle, true if the piece is black. White
    // otherwise
    public Knight(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    // Returns a string containing the your of the piece and the piece's name
    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " knight";
    }

    // Returns the first letter of piece
    // Letter is uppercase if piece is black, lowercase if piece is white
    @Override
    public String getSymbol() {
        if (isBlack)
            return "N";
        else
            return "n";
    }

    @Override
    public boolean canMove(int newX, int newY) {
        if (!validCoordinates(newX, newY))
            return false;

        int xDir = Math.abs(newX - x);
        int yDir = Math.abs(newY - y);

        if (xDir == 1 && yDir == 2 || xDir == 2 && yDir == 1) {
            return true;
        }

        return false;
    }

    @Override
    public void updateLegalMoves() {}
}
