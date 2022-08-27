import java.util.ArrayList;
import java.util.Map;

/**
 * Represents a chess board. Manages the chess pieces of each player, processes
 * the moves a user makes, outputs the board to the console, verifies if moves
 * are valid, and keeps track of the win state.
 */

public class Board {

    /** Stores black chess pieces */
    private static ArrayList<Piece> black;
    /** Stores white chess pieces */
    private static ArrayList<Piece> white;
    /** 2D array represnting the chess board and its pieces */
    private static Piece[][] board;

    /** Used for translation user input into an integer representing the column */
    private static Map<String, Integer> cols = Map.of("a", 0, "b", 1,
            "c", 2, "d", 3, "e", 4, "f", 5,
            "g", 6, "h", 7);

    /** you can't instantiate the Board, use static methods >:( */
    private Board() {
        /** no >:( */
    }

    /** Goes through each player's pieces and updates the legal moves for each */
    public static void updateAllLegalMoves() {
        for (Piece p : black) {
            if (p != null)
                p.updateLegalMoves();
        }
        for (Piece p : white) {
            if (p != null)
                p.updateLegalMoves();
        }
    }

    /**
     * Gets a piece at the coordinates (x,y)
     * 
     * @param x
     * @param y
     * @return
     */
    public static Piece getPiece(int x, int y) {
        if (x >= 0 && y >= 0 && x <= 7 && y <= 7)
            return board[y][x];
        return null;
    }

    /**
     * Gets the instance of a player's king
     * 
     * @param isBlack Color of the player (true for black)
     * @return instance of the player's king
     */
    public static Piece getKing(boolean isBlack) {
        // was using p instanceof king, but changed to compare the symbol while
        // debugging. noticed this way later but it works! not gonna change it back
        if (isBlack) {
            for (Piece p : black) {
                if (p.getSymbol().toLowerCase().equals("k")) {
                    return p;
                }
            }
        } else {
            for (Piece p : white) {
                if (p.getSymbol().toLowerCase().equals("k")) {
                    return p;
                }
            }
        }

        return null;
    }

    /**
     * Gets x dimension of the board, it is assumed that the baord is square
     * 
     * @return x dimension of the board
     */
    public static int getDimension() {
        return board.length;
    }

    /**
     * Gets an instance of the ArrayList containing the pieces of a given player
     * 
     * @param isBlack Color of the player (true for black)
     * @return Instance of ArrayList containg pieces of player
     */
    public static ArrayList<Piece> getPieces(boolean isBlack) {
        return isBlack ? black : white;
    }

    /**
     * Gets the legal moves of every piece of a given player and puts them all in
     * one string
     * 
     * @param isBlack color of the player (black is true)
     * @return string containing the legal moves of every piece
     */
    public static String getLegalMoves(boolean isBlack) {
        String legalMoves = "";

        if (isBlack) {
            ArrayList<Piece> black = Board.getPieces(isBlack);
            for (Piece p : black) {
                legalMoves += p.getLegalMoves();
            }
        } else {
            ArrayList<Piece> white = Board.getPieces(isBlack);
            for (Piece p : white) {
                legalMoves += p.getLegalMoves();
            }

        }

        return legalMoves;
    }

    /**
     * Checks if the player is currently in check
     * 
     * @param playerColor Color of the player (true for black)
     * @return true if player is in check, false otherwise
     */
    public static boolean isCheck(boolean playerColor) {
        King king = (King) getKing(playerColor);

        // if any piece can move to the opposite team's king, they are in check
        if (playerColor) {
            ArrayList<Piece> white = Board.getPieces(!playerColor);
            for (Piece p : white) {
                if (p.canMove(king.getX(), king.getY()))
                    return true;

            }
        } else {
            ArrayList<Piece> black = Board.getPieces(!playerColor);
            for (Piece p : black) {
                if (p.canMove(king.getX(), king.getY()))
                    return true;
            }
        }

        return false;
    }

    /**
     * Checks a player's pieces to see if they have any legal moves to make
     * 
     * @param playerColor color of the player (true for black)
     * @return true if player has at least one legal move they can make, false
     *         otherwise
     */
    public static boolean hasLegalMoves(boolean playerColor) {
        if (playerColor) {
            ArrayList<Piece> black = Board.getPieces(playerColor);
            for (Piece p : black) {
                if (p.hasLegalMoves())
                    return true;
            }
        } else {
            ArrayList<Piece> white = Board.getPieces(playerColor);
            for (Piece p : white) {
                if (p.hasLegalMoves())
                    return true;
            }
        }

        return false;
    }

    /**
     * Tests moving piece at (x,y) to (newX, newY) by verifying if the move is valid
     * and if the move will put @playerColor in check.
     * 
     * @param playerColor Color of the player (true for black)
     * @param x           X coordinate of piece to be moved
     * @param y           Y coordinate of piece to be moved
     * @param newX        X coordinate of destination
     * @param newY        Y coordinate of destination
     * @return true if move is legal, false if it is illegal
     */
    public static boolean testMove(boolean playerColor, int x, int y, int newX, int newY) {
        // coordinates are in range
        if (newX < 0 || newY < 0 || newX > 7 || newY > 7)
            return false;

        // can the piece actually move there?
        if (!board[y][x].canMove(newX, newY))
            return false;

        Piece otherPiece = board[newY][newX];

        // removed captured piece
        if (playerColor)
            white.remove(otherPiece);
        else
            black.remove(otherPiece);

        // moves piece
        board[newY][newX] = board[y][x];
        board[y][x].movePiece(newX, newY);
        board[y][x] = null;

        // if the move results in check for the player, false will be returned
        boolean isCheck = Board.isCheck(playerColor);

        // undoes the move by returning pieces to their original states and adds any
        // captured piece back into the lists
        board[y][x] = board[newY][newX];
        board[newY][newX].movePiece(x, y);
        board[newY][newX] = otherPiece;

        if (otherPiece != null) {
            if (playerColor)
                white.add(otherPiece);
            else
                black.add(otherPiece);
        }

        return !isCheck; // if in check, can't move
    }

    /**
     * Moves piece at (x,y) to (newX, newY) by verifying if the move is valid, if it
     * is the piece will be moved and it's previous spot will be emptied
     * 
     * @param playerColor Color of the player (true for black)
     * @param x           X coordinate of piece to be moved
     * @param y           Y coordinate of piece to be moved
     * @param newX        X coordinate of destination
     * @param newY        Y coordinate of destination
     * @return true if move is legal, false if it is illegal
     */
    public static boolean movePiece(boolean playerColor, int x, int y, int newX, int newY) {
        // can this piece actually move to the coordinates?
        if (!Board.testMove(playerColor, x, y, newX, newY))
            return false;

        // removes piece from player's pieces if captured
        if (board[newY][newX] != null) {
            if (playerColor)
                white.remove(board[newY][newX]);
            else
                black.remove(board[newY][newX]);
        }

        Piece piece = Board.getPiece(x, y);
        // moves the piece
        board[newY][newX] = piece;
        piece.movePiece(newX, newY);
        board[y][x] = null;

        return true;
    }

    /**
     * Parses the move entered by the user to get the piece they selected and where
     * they wish to move it to.
     * 
     * @param userMove    String containing the coordinates of the piece being moved
     *                    and the destination coordinates in the form
     *                    [col][row][destCol][destRow]
     * @param playerColor Color of the player making the move (true if black)
     * @return true if the move is legal (piece is moved), false if move is illegal
     *         or invald format (board remains unchanged)
     */
    public static boolean processMove(String userMove, boolean playerColor) {

        // Verifies move entered by user are valid coordinates
        userMove = userMove.replace(" ", "");
        String pattern = "[a-hA-H][1-8][a-hA-H][1-8]";

        if (!userMove.matches(pattern)) {
            System.out.println("Not a valid move. Please try again.");
            return false;
        }

        // Gets coordinates of selected piece
        int x = cols.get(String.valueOf(userMove.charAt(0)));
        int y = 8 - Character.getNumericValue(userMove.charAt(1));
        Piece piece = board[y][x];

        // Gets coordinates of the new location
        int newX = cols.get(String.valueOf(userMove.charAt(2)));
        int newY = 8 - Character.getNumericValue(userMove.charAt(3));

        if (x == newX && y == newY) {
            System.out.println("You did not move anywhere.");
            return false;
        }
        // verifies a piece is actually selected
        else if (piece == null) {
            System.out.println("There is no piece at this location.");
            return false;
        } else if (piece.getColor() != playerColor) {
            System.out.println("You have selected a piece of the other player.");
            return false;
        } else {
            if (Board.movePiece(playerColor, x, y, newX, newY)) {
                Board.updateAllLegalMoves();
                return true;
            } else {
                System.out.println("Illegal move.");
                return false;
            }
        }
    }

    /** Prints a chess board in the console with the locations of each piece */
    public static void draw() {

        // Column + row labeling variables
        String[] cols = { "a", "b", "c", "d", "e", "f", "g", "h" };
        int row = 8;

        // Top column labeling from a to f
        System.out.print("  ");
        for (String col : cols) {
            System.out.print("  " + col + " ");
        }
        System.out.println();

        for (int y = 0; y < board.length; y++) {
            // upper border/padding of each square
            System.out.print("  ");
            System.out.print("+---".repeat(board.length) + "+\n");

            // Prints piece symbol if there is a piece, otherwise it is left blank
            // Also prints x labeling from 8 to 1
            System.out.print(row + " ");
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] != null)
                    System.out.print("| " + board[y][x].getSymbol() + " ");
                else
                    System.out.print("|   ");
            }
            System.out.print("| " + row-- + "\n");
        }

        // bottom border of grid
        System.out.print("  ");
        System.out.print("+---".repeat(board.length) + "+\n");

        // bottom labeling from a to f
        System.out.print("  ");
        for (String col : cols) {
            System.out.print("  " + col + " ");
        }
        System.out.println();
    }

    /**
     * Initializes a new board and populates it with fresh pieces. Also checks all
     * the legal moves a piece may make.
     */
    public static void newGame() {

        // 2D array to hold chess pieces
        board = new Piece[8][8];
        black = new ArrayList<>(Board.getDimension());
        white = new ArrayList<>(Board.getDimension());

        // Black pieces
        board[1] = new Piece[] { new Pawn(true, 0, 1), new Pawn(true, 1, 1),
                new Pawn(true, 2, 1), new Pawn(true, 3, 1),
                new Pawn(true, 4, 1), new Pawn(true, 5, 1),
                new Pawn(true, 6, 1), new Pawn(true, 7, 1) };
        board[0] = new Piece[] { new Rook(true, 0, 0), new Knight(true, 1, 0),
                new Bishop(true, 2, 0), new King(true, 3, 0),
                new Queen(true, 4, 0), new Bishop(true, 5, 0),
                new Knight(true, 6, 0), new Rook(true, 7, 0) };
        for (Piece p : board[1]) {
            if (p != null)
                black.add(p);
        }
        for (Piece p : board[0]) {
            if (p != null)
                black.add(p);
        }

        // White pieces
        board[7] = new Piece[] { new Rook(false, 0, 7), new Knight(false, 1, 7),
                new Bishop(false, 2, 7), new King(false, 3, 7),
                new Queen(false, 4, 7), new Bishop(false, 5, 7),
                new Knight(false, 6, 7), new Rook(false, 7, 7) };
        board[6] = new Piece[] { new Pawn(false, 0, 6), new Pawn(false, 1, 6),
                new Pawn(false, 2, 6), new Pawn(false, 3, 6),
                new Pawn(false, 4, 6), new Pawn(false, 5, 6),
                new Pawn(false, 6, 6), new Pawn(false, 7, 6) };
        for (Piece p : board[7]) {
            if (p != null)
                white.add(p);
        }
        for (Piece p : board[6]) {
            if (p != null)
                white.add(p);
        }

        Board.updateAllLegalMoves();
    }
}