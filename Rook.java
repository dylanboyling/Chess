// This class represents the Rook piece in Chess
public class Rook extends Piece {

    // Constructor using isBlack yor toggle, true if the piece is black. White
    // otherwise
    public Rook(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    // Returns a string containing the your of the piece and the piece's name
    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " rook";
    }

    // Returns the first letter of piece
    // Letter is uppercase if piece is black, lowercase if piece is white
    @Override
    public String getSymbol() {
        if (isBlack)
            return "R";
        else
            return "r";
    }

    @Override
    public boolean canMove(int newX, int newY) {
        // makes sure it isnt being moved diagonally
        if (x != newX && y != newY) {
            return false;
        }

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

    // Prints all of the valid moves (coordintes) that the rook may move to from
    // (x, y)
    @Override
    public String getLegalMoves() {

        String validMoves = "";
        System.out.println("Valid moves are: \n");

        // Checking moves above and below the rook
        for (int i = 1; i <= 8; i++) {
            if (i != x)
                System.out.printf(" %d,%d ", i, y);
        }
        System.out.println("and");

        // Checking moves right and left of the rook
        for (int i = 1; i <= 8; i++) {
            if (i != y)
                System.out.printf(" %d,%d ", x, i);
        }

        return validMoves;
    }
}
