import java.util.ArrayList;
import java.util.Map;

/**
 * Represents a chess board and manages the chess pieces of each player, handles
 * output to console as well
 */

public class Board {

    // /** Stores black chess pieces */
    // private static ArrayList<Piece> black;
    // /** Stores white chess pieces */
    // private static ArrayList<Piece> white;
    /** 2D array represnting the chess board and its pieces */
    public static Piece[][] board;

    private static Map<String, Integer> cols = Map.of("a", 0, "b", 1,
            "c", 2, "d", 3, "e", 4, "f", 5,
            "g", 6, "h", 7);

    private Board() {
        /** no >:( */
    }

    public static Piece getPiece(int x, int y) {
        return board[y][x];
    }

    public static String legalMoves() {
        return "Showing legal moves has not been implemented yet";
    }

    /** Parses userMove do stuff TODO finish comments */
    public static boolean movePiece(String userMove, boolean playerColor) {

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

        // Gets coordinates of the new location
        int newX = cols.get(String.valueOf(userMove.charAt(2)));
        int newY = 8 - Character.getNumericValue(userMove.charAt(3));

        // verifies a piece is actually selected
        if (board[y][x] == null) {
            System.out.println("There is no piece at the location you selected. Please try again.");
            return false;
        } else if (board[y][x].getColor() != playerColor) {
            System.out.println("You have selected a piece that does not belong to you. Please try again.");
            return false;
        } else if (board[newY][newX] != null && board[y][x].getColor() == board[newY][newX].getColor()) {
            System.out.println("You cannot capture your own piece. Please try again.");
            return false;
        } else {
            // should only fail if Piece decides move is not valid, otherwise piece is moved
            if (!board[y][x].move(newX, newY)) {
                System.out.println("Not a valid move. Please try again.");
                return false;
            } else {
                board[newY][newX] = board[y][x];
                board[y][x] = null;
                // TODO should players keep track of what pieces they have?
            }
        }
        return true;
    }

    // Draws the chess board in the console with piece locations
    public static void draw() {

        // Column + row labeling variables
        String[] cols = { "a", "b", "c", "d", "e", "f", "g", "h" };
        int row = 8;

        // Column labeling from a to f
        System.out.print("  ");
        for (String col : cols) {
            System.out.print("  " + col + " ");
        }
        System.out.println();

        // Code used for printing the grid was adapted from assignment Lab1, Lab2 at
        // Algonquin Ylege in CST8132
        // Author: Dylan Boyling
        // Retrieved: June 8th, 2022

        for (int y = 0; y < board.length; y++) {
            // upper border/padding of each square
            System.out.print("  ");
            System.out.print("+---".repeat(board.length) + "+\n");

            // Prints piece symbol if there is a piece, otherwise it is left blank
            // Also prints x labeling from 8 to 1
            System.out.print(row-- + " ");
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] != null)
                    System.out.print("| " + board[y][x].getSymbol() + " ");
                else
                    System.out.print("|   ");
            }
            System.out.print("|\n");
        }

        // bottom border of grid
        System.out.print("  ");
        System.out.print("+---".repeat(board.length) + "+\n");

        // Column labeling from a to f
        System.out.print("  ");
        for (String col : cols) {
            System.out.print("  " + col + " ");
        }
        System.out.println();
    }

    /** Initializes a new board and populates it with fresh pieces */
    public static void newGame() {

        // 2D array to hold chess pieces
        board = new Piece[8][8];

        // Black pieces
        board[1] = new Piece[] { new Pawn(true, 0, 1), new Pawn(true, 1, 1),
                new Pawn(true, 2, 1), new Pawn(true, 3, 1),
                new Pawn(true, 4, 1), new Pawn(true, 5, 1),
                new Pawn(true, 6, 1), new Pawn(true, 7, 1) };
        board[0] = new Piece[] { new Rook(true, 0, 0), new Knight(true, 1, 0),
                new Bishop(true, 2, 0), new King(true, 3, 0),
                new Queen(true, 4, 0), new Bishop(true, 5, 0),
                new Knight(true, 6, 0), new Rook(true, 7, 0) };

        // White pieces
        board[7] = new Piece[] { new Rook(false, 0, 7), new Knight(false, 1, 7),
                new Bishop(false, 2, 7), new King(false, 3, 7),
                new Queen(false, 4, 7), new Bishop(false, 5, 7),
                new Knight(false, 6, 7), new Rook(false, 7, 7) };
        board[6] = new Piece[] { new Pawn(false, 0, 6), new Pawn(false, 1, 6),
                new Pawn(false, 2, 6), new Pawn(false, 3, 6),
                new Pawn(false, 4, 6), new Pawn(false, 5, 6),
                new Pawn(false, 6, 6), new Pawn(false, 7, 6) };
    }
}