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
        // 1) are coordinates in range
        if (!validCoordinates(newX, newY))
            return false;

        // 2) does the move put its king into check? if so cant move period
        if (!Board.testMove(isBlack, x, y, newX, newY))
            return false;

        int xDir = Math.abs(newX - x);
        int yDir = Math.abs(newY - y);

        if (xDir == 1 && yDir == 2 || xDir == 2 && yDir == 1) {
            return true;
        }

        return false;
    }

    @Override
    public void updateLegalMoves() {
        legalMoves.clear();

        // up and left/right
        if (canMove(x - 1, y - 2))
            legalMoves.add(new Move(x - 1, y - 2));
        if (canMove(x + 1, y - 2))
            legalMoves.add(new Move(x + 1, y - 2));

        // down and left/right
        if (canMove(x - 1, y + 2))
            legalMoves.add(new Move(x - 1, y + 2));
        if (canMove(x + 1, y + 2))
            legalMoves.add(new Move(x + 1, y + 2));

        // right and up/down
        if (canMove(x + 2, y - 1))
            legalMoves.add(new Move(x + 2, y - 1));
        if (canMove(x + 2, y + 1))
            legalMoves.add(new Move(x + 2, y + 1));

        // left and up/down
        if (canMove(x - 2, y - 1))
            legalMoves.add(new Move(x - 2, y - 1));
        if (canMove(x - 2, y + 1))
            legalMoves.add(new Move(x - 2, y + 1));
    }
}
