// This class represents the Knight piece in Chess
public class Knight extends Piece {

    /**
     * Creates a new Piece with a given color at (x,y)
     * 
     * @param isBlack Color of the piece, true if is black and white if false
     * @param x       X coordinate of the piece
     * @param y       Y coordinate of the piece
     */
    public Knight(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    @Override
    public String getName() {
        return "Knight";
    }

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

        int xDistance = Math.abs(newX - x);
        int yDistance = Math.abs(newY - y);

        if (xDistance == 1 && yDistance == 2 || xDistance == 2 && yDistance == 1)
            return true;
        else
            return false;
    }

    @Override
    public void updateLegalMoves() {
        legalMoves.clear();

        // up and left/right
        if (Board.testMove(isBlack, x, y, x - 1, y - 2))
            legalMoves.add(new Move(x - 1, y - 2));
        if (Board.testMove(isBlack, x, y, x + 1, y - 2))
            legalMoves.add(new Move(x + 1, y - 2));

        // down and left/right
        if (Board.testMove(isBlack, x, y, x - 1, y + 2))
            legalMoves.add(new Move(x - 1, y + 2));
        if (Board.testMove(isBlack, x, y, x + 1, y + 2))
            legalMoves.add(new Move(x + 1, y + 2));

        // right and up/down
        if (Board.testMove(isBlack, x, y, x + 2, y - 1))
            legalMoves.add(new Move(x + 2, y - 1));
        if (Board.testMove(isBlack, x, y, x + 2, y + 1))
            legalMoves.add(new Move(x + 2, y + 1));

        // left and up/down
        if (Board.testMove(isBlack, x, y, x - 2, y - 1))
            legalMoves.add(new Move(x - 2, y - 1));
        if (Board.testMove(isBlack, x, y, x - 2, y + 1))
            legalMoves.add(new Move(x - 2, y + 1));
    }
}
