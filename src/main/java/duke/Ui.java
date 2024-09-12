package duke;

import java.util.Scanner;

/**
 * Encapsulates the UI the user uses to interact with the chatbot.
 */
public class Ui {
    /**
     * Initialises the UI, displaying options to the user.
     * @return scanner to get input from user
     */
    public static Scanner initialiseUi() {
        Scanner in = new Scanner(System.in);
        return in;
    }

    /**
     * Takes in user input.
     * @param in scanner to be used to get input
     * @return user input as a string
     */
    public static String getInput(Scanner in) {
        System.out.println("");
        String input = in.nextLine();
        System.out.println("You entered: " + input);
        return input;
    }
}
