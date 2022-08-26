// This class represents the Pawn piece in Chess
public class Pawn extends Piece {

    // TODO pawn promotion
    // private boolean hasPromoted = false;

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
    public boolean canMove(int newX, int newY) {
        // 1) are coordinates in range
        if (!validCoordinates(newX, newY))
            return false;

        // 2) does the move put its king into check? if so cant move period
        if (!Board.testMove(isBlack, x, y, newX, newY))
            return false;

        // Checks piece your as it can only move in one direction
        if (isBlack) {
            // no moving backwards, too far, or left/right more than 1 square
            if (y >= newY || newY - y > 2 || Math.abs(newX - x) > 1) {
                return false;
            }
            // checks movement if still in home row, can move two squares
            if (newY - y == 2 && y == 1 && x == newX) {
                if ((Board.getPiece(x, y + 1) == null) && (Board.getPiece(x, y + 2) == null)) {
                    return true;
                } else {
                    return false;
                }
            }
            // pawn is moving 1 square ahead in its row
            if (newY - y == 1 && x == newX) {
                if (Board.getPiece(x, y + 1) == null) {
                    return true;
                } else {
                    return false;
                }
            }
            // pawn is capturing a piece diagonally
            if (newY - y == 1 && Math.abs(x - newX) == 1) {
                if (validCoordinates(newX, newY) && Board.getPiece(newX, newY) != null) {
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
                    return true;
                } else {
                    return false;
                }
            }
            // pawn is moving 1 square ahead in its row
            if (y - newY == 1 && x == newX) {
                if (Board.getPiece(x, y - 1) == null) {
                    return true;
                } else {
                    return false;
                }
            }
            // pawn is capturing a piece diagonally
            if (y - newY == 1 && Math.abs(x - newX) == 1) {
                if (validCoordinates(newX, newY) && Board.getPiece(newX, newY) != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    @Override
    public void updateLegalMoves() {
        legalMoves.clear();
        if (isBlack) {
            // vertical moves
            if (canMove(x, y + 1))
                legalMoves.add(new Move(x, y + 1));
            if (canMove(x, y + 2))
                legalMoves.add(new Move(x, y + 2));

            // diagonal captures
            if (canMove(x + 1, y + 1))
                legalMoves.add(new Move(x + 1, y + 1));
            if (canMove(x - 1, y + 1))
                legalMoves.add(new Move(x - 1, y + 1));
        } else {
            // vertical moves
            if (canMove(x, y - 1))
                legalMoves.add(new Move(x, y - 1));
            if (canMove(x, y - 2))
                legalMoves.add(new Move(x, y - 2));

            // diagonal captures
            if (canMove(x + 1, y - 1))
                legalMoves.add(new Move(x + 1, y - 1));
            if (canMove(x - 1, y - 1))
                legalMoves.add(new Move(x - 1, y - 1));
        }

    }
}
