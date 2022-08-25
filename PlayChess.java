import java.util.Random;

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

/** This class launches the game of chess and handles users input */
public class PlayChess {

    public static void main(String[] args) {

        Board.newGame();

        Random coinFlip = new Random(); // random player goes first
        boolean playerTurn = coinFlip.nextBoolean(); // white for false, black for true
        boolean playerMoved;

        System.out.printf("%nWelcome to my command line interpretation of chess!%n"
                + "White chess pieces are indicated in lower case, black pieces in upper case.%n"
                + "P = pawn, R = rook, B = bishop, N = knight, Q = queen, K = king.%n"
                + "To move a piece, enter the piece's location follow by the location you wish to move it to.%n"
                + "e.g. b2 b4 to move the piece at b2 to b4%n"
                + "Enter ? to view legal moves or Q to quit.%n%n");

        // Variables for menu selection
        final String LEGAL_MOVES = "?"; // displays all legal moves
        final String QUIT = "q"; // quits the program
        String option = ""; // storing user input

        System.out.println((playerTurn ? "Black has randomly been decided to go first"
                : "White has randomly been decided to go first"));

        // Handles input of menu, will not exit unless Q is entered (case insensitive)
        do {
            Board.draw();
            System.out.println((playerTurn ? "It is black's turn" : "It is white's turn"));
            playerMoved = false;
            // Menu selection, only exits when player enters a valid move
            do {
                option = UserInput.inputOption();
                switch (option) {
                    case LEGAL_MOVES:
                        System.out.println(Board.legalMoves());
                        break;
                    case QUIT:
                        playerMoved = true; // exits game
                        break;
                    default:
                        playerMoved = Board.movePiece(option, playerTurn);
                }
            } while (!playerMoved);
            playerTurn = !playerTurn; // alternate players
        } while (!option.equalsIgnoreCase(QUIT));

        UserInput.closeInput();
        System.out.println("Program ending...");
        System.out.println("Program by Dylan Boyling");
    }
}
