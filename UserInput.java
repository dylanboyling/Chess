import java.util.Scanner;

/**
 * This class is for interfacing with the user.
 */
public class UserInput {

    /** For interfacing with the user */
    private static Scanner keyboard = new Scanner(System.in);

    /**
     * Gets a move from the user
     * 
     * @return String containing the move the player wishes to make
     */
    public static String inputOption() {
        System.out.print("Enter your move: ");
        return keyboard.nextLine().toLowerCase();
    }

    /**
     * Asks the user if they would like to play again, only accepts y or n as inputs
     * 
     * @return true if player would like to play again, false if not
     */
    public static boolean playAgain() {
        String input;

        do {
            System.out.print("Would you like to play another game (Y/N)? ");
            input = keyboard.nextLine().toLowerCase();
            if (input.equals("y"))
                return true;
            else if (input.equals("n"))
                return false;
            else
                System.out.print("%nPlease enter y or n: ");
        } while (true);
    }

    /**
     * Asks the user which piece they would like to promote a pawn to
     * 
     * @return int corresponding to piece selection using the enum Pawn.Promotion
     */
    public static int promotePawn() {
        int userOption = 0;
        System.out.printf("Your pawn can be promoted! What would you like to promote it to?%n"
                + "1) QUEEN%n2) ROOK%n3) BISHOP%n4) KNIGHT%n");

        while (userOption < 1 || userOption > 4) {
            if (keyboard.hasNextInt()) {
                userOption = keyboard.nextInt();
            } else {
                System.out.print("Please enter a number between 1 and 4: ");
            }
        }

        return userOption;
    }

    /** Closes UserInput's resources */
    public static void closeInput() {
        keyboard.close();
    }
}