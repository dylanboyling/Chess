import java.util.Scanner;

/**
 * This class is for interfacing with the user. Note: only really gets a string
 * at the moment, it used to do a little more but I changed how coordinates were
 * entered
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
            if (input == "y")
                return true;
            else if (input == "n")
                return false;
            else
                System.out.print("%nPlease enter y or n: ");
        } while (true);
    }

    /** Closes UserInput's resources */
    public static void closeInput() {
        keyboard.close();
    }
}