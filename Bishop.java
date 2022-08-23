// This class represents the Queen piece in Chess
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

    @Override
    public boolean move(int newX, int newY) {

        // figure out direction, up and right are positive numbers
        int xDir = newX - x;
        int yDir = y - newY;

        // makes sure it isnt being moved vertically or horizontally
        if (x == newX || y == newY || Math.abs(xDir) != Math.abs(yDir)) {
            return false;
        }

        // top right diagonal
        if (xDir > 0 && yDir > 0) {
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

        // top left diagonal
        if (xDir < 0 && yDir > 0) {
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

        // bottom right diagonal
        if (xDir > 0 && yDir < 0) {
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

        // bottom left diagonal
        if (xDir < 0 && yDir < 0) {
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

        // shouldnt get to this step, something is wrong otherwise
        return false;
    }

    // Prints all of the valid moves (coordintes) that the rook may move to from
    // (x, y)
    @Override
    public String getLegalMoves() {
        return "Move functionality not implemented for this piece.";
    }
}
