/** This class represents the King piece in Chess */
public class King extends Piece {

    /**
     * Creates a new Piece with a given color at (x,y)
     * 
     * @param isBlack Color of the piece, true if is black and white if false
     * @param x       X coordinate of the piece
     * @param y       Y coordinate of the piece
     */
    public King(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " king";
    }

    @Override
    public String getSymbol() {
        if (isBlack)
            return "K";
        else
            return "k";
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

        return true;
    }

    @Override
    public void updateLegalMoves() {
        legalMoves.clear();

        // upper left
        if (Board.testMove(isBlack, x, y, x - 1, y - 1))
            legalMoves.add(new Move(x - 1, y - 1));
        // upper right
        if (Board.testMove(isBlack, x, y, x + 1, y - 1))
            legalMoves.add(new Move(x + 1, y - 1));
        // bottom left
        if (Board.testMove(isBlack, x, y, x - 1, y + 1))
            legalMoves.add(new Move(x - 1, y + 1));
        // bottom right
        if (Board.testMove(isBlack, x, y, x + 1, y + 1))
            legalMoves.add(new Move(x + 1, y + 1));

        // above
        if (Board.testMove(isBlack, x, y, x, y - 1))
            legalMoves.add(new Move(x, y - 1));
        // below
        if (Board.testMove(isBlack, x, y, x, y + 1))
            legalMoves.add(new Move(x, y + 1));
        // right
        if (Board.testMove(isBlack, x, y, x + 1, y))
            legalMoves.add(new Move(x + 1, y));
        // left
        if (Board.testMove(isBlack, x, y, x - 1, y))
            legalMoves.add(new Move(x - 1, y));
    }
}