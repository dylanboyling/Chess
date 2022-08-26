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

    public boolean isCheckmate() {

        return false;
    }

    @Override
    public boolean canMove(int newX, int newY) {
        // 1) are coordinates in range
        if (!validCoordinates(newX, newY))
            return false;

        // figure out direction, up and right are positive numbers
        int xDir = newX - x;
        int yDir = y - newY;

        // 2) can only move 1 space in any direction
        if (Math.abs(xDir) > 1 || Math.abs(yDir) > 1 || xDir == 0 && yDir == 0)
            return false;

        // 3) does the move put its king into check? if so cant move period
        if (!Board.testMove(isBlack, x, y, newX, newY))
            return false;

        return true;
    }

    @Override
    public void updateLegalMoves() {
        legalMoves.clear();

        // upper left
        if (canMove(x - 1, y - 1))
            legalMoves.add(new Move(x - 1, y - 1));
        // upper right
        if (canMove(x + 1, y - 1))
            legalMoves.add(new Move(x + 1, y - 1));
        // bottom left
        if (canMove(x - 1, y + 1))
            legalMoves.add(new Move(x - 1, y + 1));
        // bottom right
        if (canMove(x + 1, y + 1))
            legalMoves.add(new Move(x + 1, y + 1));

        // above
        if (canMove(x, y - 1))
            legalMoves.add(new Move(x, y - 1));
        // below
        if (canMove(x, y + 1))
            legalMoves.add(new Move(x, y + 1));
        // right
        if (canMove(x + 1, y))
            legalMoves.add(new Move(x + 1, y));
        // left
        if (canMove(x - 1, y))
            legalMoves.add(new Move(x - 1, y));
    }
}