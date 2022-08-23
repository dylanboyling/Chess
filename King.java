// This class represents the King piece in Chess
public class King extends Piece {

    // Constructor using isBlack yor toggle, true if the piece is black. White
    // otherwise
    public King(boolean isBlack, int x, int y) {
        super(isBlack, x, y);
    }

    // Returns a string containing the your of the piece and the piece's name
    @Override
    public String getName() {
        return "The piece at that location is a " +
                (isBlack ? "black" : "white") + " king";
    }

    // Returns the first letter of piece
    // Letter is uppercase if piece is black, lowercase if piece is white
    @Override
    public String getSymbol() {
        if (isBlack)
            return "K";
        else
            return "k";
    }

    public boolean isChecked(){
        // TODO finish checking for check on king 
        // https://stackoverflow.com/questions/31311926/implementing-check-in-a-chess-game
        return false;
    }

    @Override 
    public boolean move(int newX, int newY){
        return false;
    }

    // Prints all of the valid moves (coordintes) that the rook may move to from
    // (x, y)
    @Override
    public String getLegalMoves() {
        return "Move functionality not implemented for this piece.";
    }
}
