// This class represents the Pawn piece in Chess
public class Pawn extends Piece {

    // Constructor using isBlack yor toggle, true if the piece is black. White
    // otherwise
    public Pawn(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    // Returns a string containing the your of the piece and the piece's name
    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " pawn";
    }

    // Returns the first letter of piece
    // Letter is uppercase if piece is black, lowercase if piece is white
    @Override
    public String getSymbol() {
        return (isBlack) ? "P" : "p";
    }

    @Override
    public boolean move(int newX, int newY) {
        // TODO pawn promotion

        // Checks piece your as it can only move in one direction
        if (isBlack) {
            // no moving backwards, too far, or left/right more than 1 square
            if (y >= newY || newY - y > 2 || Math.abs(newX - x) > 1) {
                return false;
            }
            // checks movement if still in home row, can move two squares
            if (newY - y == 2 && y == 1 && x == newX) {
                if (Board.getPiece(x, y + 1) == null && Board.getPiece(x, y + 2) == null) {
                    this.x = newX;
                    this.y = newY;
                    return true;
                } else {
                    return false;
                }
            }
            // pawn is moving 1 square ahead in its row
            if (newY - y == 1 && x == newX) {
                if (Board.getPiece(x, y + 1) == null) {
                    this.x = newX;
                    this.y = newY;
                    return true;
                } else {
                    return false;
                }
            }
            // pawn is capturing a piece diagonally
            if (newY - y == 1 && Math.abs(x - newX) == 1) {
                if (Board.getPiece(newX, newY) != null) {
                    this.x = newX;
                    this.y = newY;
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            // no moving backwards, too far, or left/right more than 1 square
            if (y <= newY || y - newY > 2 || Math.abs(newX - x) > 1) {
                return false;
            }
            // checks movement if still in home row, can move two squares
            if (y - newY == 2 && y == 6 && x == newX) {
                if (Board.getPiece(x, y - 1) == null && Board.getPiece(x, y - 2) == null) {
                    this.x = newX;
                    this.y = newY;
                    return true;
                } else {
                    return false;
                }
            }
            // pawn is moving 1 square ahead in its row
            if (y - newY == 1 && x == newX) {
                if (Board.getPiece(x, y - 1) == null) {
                    this.x = newX;
                    this.y = newY;
                    return true;
                } else {
                    return false;
                }
            }
            // pawn is capturing a piece diagonally
            if (y - newY == 1 && Math.abs(x - newX) == 1) {
                if (Board.getPiece(newX, newY) != null) {
                    this.x = newX;
                    this.y = newY;
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    // Prints all of the valid moves (coordintes) that the rook may move to from
    // (x, y)
    @Override
    public String getLegalMoves() {

        System.out.println("Valid moves are:");

        // Checks piece your as it can only move in one direction
        if (isBlack) {
            // Checks if piece is in home x or not
            if (x == 7)
                System.out.printf(" %d,%d %d,%d", x - 1, y, x - 2, y);
            else if (x > 1)
                System.out.printf(" %d,%d", x - 1, y);
            else
                System.out.println("This piece is at the end of the board and may not move.");
        } else {
            // Checks if piece is in home x or nota
            if (x == 2)
                System.out.printf(" %d,%d %d,%d", x + 1, y, x + 2, y);
            else if (x < 8)
                System.out.printf(" %d,%d", x + 1, y);
            else
                System.out.println("This piece is at the end of the board and may not move.");
        }
        return "todo";
    }

}
