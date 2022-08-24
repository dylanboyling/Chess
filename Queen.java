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
    public boolean move(int newX, int newY) {
        // figure out direction, up and right are positive numbers
        int xDir = newX - x;
        int yDir = y - newY;

        // some flavor of right movement
        if (xDir > 0) {
            // top right diagonal
            if (yDir > 0) {
                for (int i = x; i < newX; i++) {
                    for (int j = y; j > newY; j--) {
                        // checks travel path, else it is the square we are moving to
                        if (i + 1 != newX && j - 1 != newY && Board.getPiece(i + 1, j - 1) != null) {
                            return false;
                        } else if (i + 1 == newX && j - 1 == newY) {
                            this.x = newX;
                            this.y = newY;
                            return true;
                        }
                    }
                }
            }
            // bottom right diagonal
            else if (yDir < 0) {
                for (int i = x; i < newX; i++) {
                    for (int j = y; j < newY; j++) {
                        // checks travel path, else it is the square we are moving to
                        if (i + 1 != newX && j + 1 != newY && Board.getPiece(i + 1, j + 1) != null) {
                            return false;
                        } else if (i + 1 == newX && j + 1 == newY) {
                            this.x = newX;
                            this.y = newY;
                            return true;
                        }
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
                        this.x = newX;
                        this.y = newY;
                        return true;
                    }
                }
            }
        }
        if (xDir < 0) {
            // top left diagonal
            if (yDir > 0) {
                for (int i = x; i > newX; i--) {
                    for (int j = y; j > newY; j--) {
                        // checks travel path, else it is the square we are moving to
                        if (i - 1 != newX && j - 1 != newY && Board.getPiece(i - 1, j - 1) != null) {
                            return false;
                        } else if (i - 1 == newX && j - 1 == newY) {
                            this.x = newX;
                            this.y = newY;
                            return true;
                        }
                    }
                }
            }
            // bottom left diagonal
            else if (yDir < 0) {
                for (int i = x; i > newX; i--) {
                    for (int j = y; j < newY; j++) {
                        // checks travel path, else it is the square we are moving to
                        if (i - 1 != newX && j + 1 != newY && Board.getPiece(i - 1, j + 1) != null) {
                            return false;
                        } else if (i - 1 == newX && j + 1 == newY) {
                            this.x = newX;
                            this.y = newY;
                            return true;
                        }
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
                        this.x = newX;
                        this.y = newY;
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
                    this.x = newX;
                    this.y = newY;
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
                    this.x = newX;
                    this.y = newY;
                    return true;
                }
            }
        }

        // shouldnt get to this step, something is wrong otherwise
        return false;
    }

    // @Override
    public String getLegalMoves() {
        return "Move functionality not implemented for this piece.";
    }
}
