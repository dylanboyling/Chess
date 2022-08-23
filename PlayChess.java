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

        boolean playerColor = false; // white

        System.out.printf("%nWelcome to my command line interpretation of chess!%n"
                + "White chess pieces are indicated in lower case, black pieces in upper case.%n"
                + "P = pawn, R = rook, B = bishop, N = knight, Q = queen, K = king.%n"
                + "To move a piece, enter the piece's location follow by the location you wish to move it to.%n"
                + "e.g. b2 b4 to move the piece at b2 to b4%n"
                + "Enter ? to view legal moves or Q to quit.%n%n");

        // Variables for menu selection
        final String LEGAL_MOVES = "?"; // displays all legal moves
        final String QUIT = "q"; // quits the program

        // Variables for user input
        String option = "";
        Boolean isOptionValid = false;

        // Handles input of menu, will not exit unless Q is entered (case insensitive)
        do {
            Board.draw();

            // User for a menu selection, only accepts 1, 2, 3 or Q. Will repeat until a
            // valid option is entered.
            do {
                option = UserInput.inputOption();
                switch (option) {
                    case LEGAL_MOVES:
                        System.out.println(Board.legalMoves());
                        isOptionValid = true;
                        break;
                    case QUIT:
                        // do anything else here???
                        isOptionValid = true;
                        break;
                    default:
                        Board.movePiece(option, playerColor);
                        isOptionValid = true;
                }
            } while (!isOptionValid);
        } while (!option.equalsIgnoreCase(QUIT));

        UserInput.closeInput();
        System.out.println("Program ending...");
        System.out.println("Program by Dylan Boyling");
    }
}
