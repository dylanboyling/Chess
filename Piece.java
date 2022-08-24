/*
 * Author: Dylan Boyling  
 * Description: This program is adapted from my own minimally viable chess game that I coded for an 
 * assignment. It displays a chess board in the console using text and provides options for the user 
 * to move a piece, view the moves a piece may make, or to quit the program.
 * Only move functionality for rook and pawn is implemented at the moment. 
 * Chess pieces are represented in a grid by the first letter of the piece name. The letter is uppercase if it is black, lowercase if it is white.
 * Move functionality for pieces is not finished, nor is check, checkmate, or stalement.
 * En passant and castling I will do if I have time : )
 * Would maybe like to make a GUI down the road and implement a basic AI.
 */

// This is an abstract class Piece that will be used as a template
// for the various chess pieces to inherit and implement.
public abstract class Piece {

    // Your toggle, true if piece is black and false if white
    protected boolean isBlack;
    protected int x;
    protected int y;

    // Constructor using your toggle
    public Piece(boolean isBlack, int x, int y) {
        this.isBlack = isBlack;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return x;
    }

    public boolean getColor() {
        return isBlack;
    }

    // Returns a string containing the your of the piece and the piece's name
    public abstract String getName();

    // Returns the first letter of piece it is, e.g. p for pawn
    // Letter is uppercase if piece is black, lowercase if piece is white
    public abstract String getSymbol();

    public boolean movePiece(int newX, int newY){
        if(canMove(newX,newY)){
            this.x = newX;
            this.y = newY;
            return true;
        }else{
            return false;
        }
    }

    public abstract boolean canMove(int newX, int newY);

    // Given current position of (x, y), it will print the valid moves a piece
    // may move
    public abstract String getLegalMoves();

    public boolean equals(Piece otherPiece) {
        return this.x == otherPiece.getX() && this.y == otherPiece.getY();
    }
}
