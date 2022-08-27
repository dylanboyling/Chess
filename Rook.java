// This class represents the Rook piece in Chess
public class Rook extends Piece {

    /**
     * Creates a new Piece with a given color at (x,y)
     * 
     * @param isBlack Color of the piece, true if is black and white if false
     * @param x       X coordinate of the piece
     * @param y       Y coordinate of the piece
     */
    public Rook(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " rook";
    }

    @Override
    public String getSymbol() {
        if (isBlack)
            return "R";
        else
            return "r";
    }

    @Override
    public boolean canMove(int newX, int newY) {
        // 1) are coordinates in range
        if (!validCoordinates(newX, newY))
            return false;

        // 2) makes sure it isnt being moved diagonally
        if (x != newX && y != newY)
            return false;

        // figure out direction, up and right are positive numbers
        int xDir = newX - x;
        int yDir = y - newY;

        // right
        if (xDir > 0) {
            for (int i = x; i < newX; i++) {
                // checks travel path, else it is the square we are moving to
                if (i + 1 != newX && Board.getPiece(i + 1, y) != null) {
                    return false;
                } else if (i + 1 == newX) {
                    return true;
                }
            }
        }

        // left
        if (xDir < 0) {
            for (int i = x; i > newX; i--) {
                // checks travel path, else it is the square we are moving to
                if (i - 1 != newX && Board.getPiece(i - 1, y) != null) {
                    return false;
                } else if (i - 1 == newX) {
                    return true;
                }
            }
        }

        // up
        if (yDir > 0) {
            for (int i = y; i > newY; i--) {
                // checks travel path, else it is the square we are moving to
                if (i - 1 != newY && Board.getPiece(x, i - 1) != null) {
                    return false;
                } else if (i - 1 == newY) {
                    return true;
                }
            }

        }

        // down
        if (yDir < 0) {
            for (int i = y; i < newY; i++) {
                // checks travel path, else it is the square we are moving to
                if (i + 1 != newY && Board.getPiece(x, i + 1) != null) {
                    return false;
                } else if (i + 1 == newY) {
                    return true;
                }
            }
        }

        // shouldnt get to this step, something is wrong otherwise
        return false;
    }

    @Override
    public void updateLegalMoves() {
        legalMoves.clear();

        // checking moves above
        int testX = x;
        int testY = y;
        while (--testY >= 0) {
            if (Board.testMove(isBlack, x, y, testX, testY))
                legalMoves.add(new Move(x, testY));
            else
                break;
        }

        // checking moves below
        testX = x;
        testY = y;
        while (++testY <= 7) {
            if (Board.testMove(isBlack, x, y, testX, testY))
                legalMoves.add(new Move(x, testY));
            else
                break;
        }

        // Checking moves right
        testX = x;
        testY = y;
        while (++testX <= 7) {
            if (Board.testMove(isBlack, x, y, testX, testY))
                legalMoves.add(new Move(testX, y));
            else
                break;
        }

        // Checking moves left
        testX = x;
        testY = y;
        while (--testX >= 0) {
            if (Board.testMove(isBlack, x, y, testX, testY))
                legalMoves.add(new Move(testX, y));
            else
                break;
        }
    }

}
