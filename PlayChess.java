import java.util.Random;

/**
 * Author: Dylan Boyling  
 * Description: This program is adapted from my own minimally viable chess game that I coded for an 
 * assignment. It displays a chess board in the console using text and provides options for the user 
 * to move a piece, view the moves a piece may make, or to quit the program.
 * Only move functionality for rook and pawn is implemented at the moment. 
 * Chess pieces are represented in a grid by the first letter of the piece name. The letter is uppercase if it is black, lowercase if it is white.
 * En passant and castling I will do if I have time : )
 * Would maybe like to make a GUI down the road and implement a basic AI with minimaxing.
 */

/** This class launches the game of chess and handles users input */
public class PlayChess {

    public static void main(String[] args) {

        Board.newGame();

        int blackScore = 0;
        int whiteScore = 0;

        Random coinFlip = new Random(); // random player goes first
        boolean playerTurn = coinFlip.nextBoolean(); // white for false, black for true
        boolean hasPlayerMoved = false;
        boolean isGameOver = false;

        System.out.printf("%nWelcome to my command line interpretation of chess!%n"
                + "White chess pieces are indicated in lower case, black pieces in upper case.%n"
                + "P = pawn, R = rook, B = bishop, N = knight, Q = queen, K = king.%n"
                + "To move a piece, enter the piece's location follow by the location you wish to move it to.%n"
                + "e.g. b2 b4 to move the piece at b2 to b4%n"
                + "Enter ? to view legal moves or Q to quit.%n%n");

        // Variables for menu selection
        final String LEGAL_MOVES = "?";
        final String QUIT = "q";
        String option = "";

        System.out.println((playerTurn ? "Black has randomly been decided to go first"
                : "White has randomly been decided to go first"));

        // Handles input of menu, will not exit unless Q is entered or checkmate
        do {
            Board.draw();
            System.out.println((playerTurn ? "It is black's turn" : "It is white's turn"));

            // Menu selection, only exits when player enters a valid move or game over
            do {
                hasPlayerMoved = false;
                // if player does not have any moves to move it is either checkmate or stalemate
                if (!Board.hasLegalMoves(playerTurn)) {
                    if (Board.isCheck(playerTurn)) {
                        System.out.println((playerTurn ? "\nBlack is in checkmate! Game over."
                                : "\nWhite is in checkmate! Game over.") + "\nWould you like to play again?");
                        if (playerTurn) {
                            whiteScore++;
                        } else {
                            blackScore++;
                        }
                    } else
                        System.out.println("You cannot make any moves, the game is a stalemate.");

                    System.out.printf("%nBlack player's score: %d%nWhite player's score: %d%n",
                            blackScore, whiteScore);
                    isGameOver = true;
                } else { // if user is not in check, they can move
                    option = UserInput.inputOption();

                    switch (option) {
                        case LEGAL_MOVES:
                            System.out.println("Legal moves : " + Board.getLegalMoves(playerTurn));
                            break;
                        case QUIT:
                            hasPlayerMoved = true; // exits game
                            break;
                        default:
                            hasPlayerMoved = Board.processMove(option, playerTurn);
                    }

                }

            } while (!hasPlayerMoved && !isGameOver);

            if (isGameOver) {
                if (UserInput.playAgain()) {
                    isGameOver = false;
                    playerTurn = coinFlip.nextBoolean();
                    Board.newGame();
                } else
                    option = QUIT;
            } else
                playerTurn = !playerTurn;// alternate players
        } while (!option.equalsIgnoreCase(QUIT));

        UserInput.closeInput();
        System.out.println("Program ending...");
        System.out.println("Program by Dylan Boyling");
    }
}