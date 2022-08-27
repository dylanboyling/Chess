/** This class represents the Queen piece in Chess */
public class Bishop extends Piece {

    // Constructor using isBlack yor toggle, true if the piece is black. White
    // otherwise
    public Bishop(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    // Returns a string containing the your of the piece and the piece's name
    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " bishop";
    }

    // Returns the first letter of piece
    // Letter is uppercase if piece is black, lowercase if piece is white
    @Override
    public String getSymbol() {
        if (isBlack)
            return "B";
        else
            return "b";
    }

    /**
     * Verifies if a piece can successful move to a space (i.e. no other pieces are
     * in its path and moves how it should)
     */
    @Override
    public boolean canMove(int newX, int newY) {
        // 1) are coordinates in range
        if (!validCoordinates(newX, newY))
            return false;

        // figure out direction, up and right are positive numbers
        int xDir = newX - x;
        int yDir = y - newY;

        // 2) makes sure it isnt being moved vertically or horizontally
        if (x == newX || y == newY || Math.abs(xDir) != Math.abs(yDir))
            return false;

        // // 3) does the move put its king into check? if so cant move period
        // if (!Board.testMove(isBlack, x, y, newX, newY))
        // return false;

        // top right diagonal
        if (xDir > 0 && yDir > 0) {
            int i = x;
            int j = y;
            while (++i <= newX && --j >= newY) {
                // checks travel path, else it is the square we are moving to
                if (i != newX && j != newY && Board.getPiece(i, j) != null) {
                    return false;
                } else if (i == newX && j == newY) {
                    return true;
                }
            }
        }
        // top left diagonal
        if (xDir < 0 && yDir > 0) {
            int i = x;
            int j = y;
            while (--i >= newX && --j >= newY) {
                // checks travel path, else it is the square we are moving to
                if (i != newX && j != newY && Board.getPiece(i, j) != null) {
                    return false;
                } else if (i == newX && j == newY) {
                    return true;
                }
            }
        }
        // bottom right diagonal
        if (xDir > 0 && yDir < 0) {
            int i = x;
            int j = y;
            while (++i <= newX && ++j <= newY) {
                // checks travel path, else it is the square we are moving to
                if (i != newX && j != newY && Board.getPiece(i, j) != null) {
                    return false;
                } else if (i == newX && j == newY) {
                    return true;
                }
            }
        }

        // bottom left diagonal
        if (xDir < 0 && yDir < 0) {
            int i = x;
            int j = y;
            while (--i >= newX && ++j <= newY) {
                // checks travel path, else it is the square we are moving to
                if (i != newX && j != newY && Board.getPiece(i, j) != null) {
                    return false;
                } else if (i == newX && j == newY) {
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

        // top right
        int testX = x;
        int testY = y;
        while (++testX <= 7 || --testY >= 0) {
            if (Board.testMove(isBlack, x, y, testX, testY))
                legalMoves.add(new Move(testX, testY));
            else
                break;
        }

        // top left
        testX = x;
        testY = y;
        while (--testX >= 0 || --testY >= 0) {
            if (Board.testMove(isBlack, x, y, testX, testY))
                legalMoves.add(new Move(testX, testY));
            else
                break;
        }

        // bottom right
        testX = x;
        testY = y;
        while (++testX <= 7 || ++testY <= 7) {
            if (Board.testMove(isBlack, x, y, testX, testY))
                legalMoves.add(new Move(testX, testY));
            else
                break;
        }

        // bottom left
        testX = x - 1;
        testY = y + 1;
        while (--testX >= 0 || ++testY <= 7) {
            if (Board.testMove(isBlack, x, y, testX, testY))
                legalMoves.add(new Move(testX, testY));
            else
                break;
        }
    }
}
