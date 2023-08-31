package duke;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;

/**
 * Handles user input and output interactions.
 */
public class Ui {
    Scanner uiScanner = new Scanner(System.in);

    /**
     * Prints the message to the console.
     *
     * @param thingToPrint The message to be printed.
     */
    public static void print(String thingToPrint) {
        System.out.println(thingToPrint);
    }


    /**
     * Retrieves the next word from the user input.
     *
     * @return The next word from user input.
     */
    public String next() {
        return uiScanner.next();
    }

    /**
     * Retrieves the next line from the user input.
     *
     * @return The next line from user input.
     */
    public String nextLine() {
        return uiScanner.nextLine();
    }
}
