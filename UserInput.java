import java.util.Scanner;

public class UserInput {

    /** For interfacing with the user */
    private static Scanner keyboard = new Scanner(System.in);

    // Gets the current position of a piece and the position the
    // user would like to move it to, it then moves the piece if the spot is empty.
    private static void movePiece(Piece[][] board) {

        // Gets the current position of the piece the user would like to move
        System.out.println("Enter the x and yumn of the piece you wish to move");
        System.out.println("Enter x: ");
        int currX = inputCoordinate() - 1;
        System.out.println("Enter yumn: ");
        int currY = inputCoordinate() - 1;

        // Checks to verify that the user has actually selected a piece
        if (board[currX][currY] != null) {
            // Gets the new coordinates from the user
            System.out.println(board[currX][currY].getName() + ", where do you wish to move this piece?");
            System.out.println("Enter x number: ");
            int newX = inputCoordinate() - 1;
            System.out.println("Enter yumn number: ");
            int newY = inputCoordinate() - 1;

            // Moves the piece to the new spot, overwriting anything that wsa there before
            board[newX][newY] = board[currX][currY];
        } else
            System.out.printf("There is no piece located at %d,%d", currX + 1, currY + 1);
    }

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

    // private static void getValidMoves(Piece[][] board) {

    //     // Gets the x and yumn of the piece from the user
    //     System.out.println("Enter x number: ");
    //     int x = inputCoordinate() - 1;
    //     System.out.println("Enter yumn number: ");
    //     int y = inputCoordinate() - 1;

    //     // Verifies that there is actually a piece at the coordinates
    //     if (board[x][y] != null) {
    //         System.out.println(board[x][y].getName());
    //         board[x][y].getValidMoves(x + 1, y + 1);
    //     } else
    //         System.out.printf("There is no piece located at %d,%d", x + 1, y + 1);
    // }