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
    public boolean canMove(int newX, int newY) {
        // 1) are coordinates in range
        if (!validCoordinates(newX, newY))
            return false;

        // 3) does the move put its king into check? if so cant move period
        if (!Board.testMove(isBlack, x, y, newX, newY))
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

        int testX = x;
        int testY = y;
        // checking moves above
        while (--testY >= 0) {
            if (canMove(x, testY))
                legalMoves.add(new Move(x, testY));
            else
                break;
        }

        testX = x;
        testY = y;
        // checking moves below
        while (++testY <= 7) {
            if (canMove(x, testY))
                legalMoves.add(new Move(x, testY));
            else
                break;
        }

        testX = x;
        testY = y;
        // Checking moves right
        while (++testX <= 7) {
            if (canMove(testX, y))
                legalMoves.add(new Move(testX, y));
            else
                break;
        }

        testX = x - 1;
        testY = y;
        // Checking moves left
        while (testX >= 0) {
            if (canMove(testX, y))
                legalMoves.add(new Move(testX, y));
            else
                break;
            testX--;
        }

        // top right
        testX  = x + 1;
        testY = y - 1;

        while (testX <= 7 || testY >= 0) {
            if (canMove(testX, testY))
                legalMoves.add(new Move(testX, testY));
            else
                break;
            testX++;
            testY--;
        }

        // top left
        testX = x - 1;
        testY = y - 1;

        while (testX >= 0 || testY >= 0) {
            if (canMove(testX, testY))
                legalMoves.add(new Move(testX, testY));
            else
                break;
            testX--;
            testY--;
        }

        // bottom right
        testX = x + 1;
        testY = y + 1;

        while (testX <= 7 || testY <= 7) {
            if (canMove(testX, testY))
                legalMoves.add(new Move(testX, testY));
            else
                break;
            testX++;
            testY++;
        }

        // bottom left
        testX = x - 1;
        testY = y + 1;

        while (testX >= 0 || testY <= 7) {
            if (canMove(testX, testY))
                legalMoves.add(new Move(testX, testY));
            else
                break;
            testX--;
            testY++;
        }
    }
}
