// This class represents the Queen piece in Chess
public class Queen extends Piece {

    /**
     * Creates a new Piece with a given color at (x,y)
     * 
     * @param isBlack Color of the piece, true if is black and white if false
     * @param x       X coordinate of the piece
     * @param y       Y coordinate of the piece
     */
    public Queen(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " queen";
    }

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

        // figure out direction, up and right are positive numbers
        int xDir = newX - x;
        int yDir = y - newY;

        // moving right in any y direction
        if (xDir > 0) {
            // top right diagonal
            if (yDir > 0) {
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
            } // bottom right diagonal
            else if (yDir < 0) {
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
            // right
            else {
                for (int i = x; i < newX; i++) {
                    // checks travel path, else it is the square we are moving to
                    if (i + 1 != newX && Board.getPiece(i + 1, y) != null) {
                        return false;
                    } else if (i + 1 == newX) {
                        return true;
                    }
                }
            }
        }

        // moving left in any y direction
        if (xDir < 0) {
            // top left diagonal
            if (yDir > 0) {
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

            // bottom left diagonal
            else if (yDir < 0) {
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
            // left
            else {
                for (int i = x; i > newX; i--) {
                    // checks travel path, else it is the square we are moving to
                    if (i - 1 != newX && Board.getPiece(i - 1, y) != null) {
                        return false;
                    } else if (i - 1 == newX) {
                        return true;
                    }
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

        // shouldnt get to this step, something is wrong otherwise??
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

        // top right
        testX = x;
        testY = y;
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
        testX = x;
        testY = y;
        while (--testX >= 0 || ++testY <= 7) {
            if (Board.testMove(isBlack, x, y, testX, testY))
                legalMoves.add(new Move(testX, testY));
            else
                break;
        }
    }
}
