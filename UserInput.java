import java.util.Scanner;

public class UserInput {

    /** For interfacing with the user */
    private static Scanner keyboard = new Scanner(System.in);

    // Asks the user for a single coordinate on the board between 1 and 8
    public static int inputCoordinate() {

        // Variables for user input
        boolean isOptionValid = false;
        int coord = 0;

        // Will continue to loop until a valid input is received bewtween 1 and 8
        while (!isOptionValid) {
            // Checks if there is an integer in the input
            if (keyboard.hasNextInt()) {
                coord = keyboard.nextInt();
                // Verifies coordinate is between 1 and 8
                if (coord >= 1 && coord <= 8) {
                    isOptionValid = true;
                } else {
                    System.out.print("That is not a valid coordinate. Please enter a number between 1 and 8: ");
                }
            } else {
                System.out.print("That is not a valid coordinate. Please enter a number between 1 and 8: ");
            }
            keyboard.nextLine();
        }
        // keyboard.close(); breaks program for some reason??

        return coord;
    }

    /** Retrieves a menu selection from the user */
    public static String inputOption() {
        System.out.print("Enter your move: ");
        return keyboard.nextLine().toLowerCase();
    }

    /** Closes UserInput's resources */
    public static void closeInput() {
        keyboard.close();
    }
}