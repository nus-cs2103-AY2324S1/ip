package duke;

import java.util.Scanner;

/**
 * Encapsulates the UI the user uses to interact with the chatbot.
 */
public class Ui {
    public static void greet() {
        System.out.println("Hello! I'm Eepy 😸");
    }

    /**
     * Displays list of options that the chatbot can perform.
     */
    public static void displayOptions() {
        System.out.println("Options:");
        System.out.println("Add todo - todo name");
        System.out.println("Add deadline - deadline name /by date (in yyyy-mm-dd format)");
        System.out.println("Add event - event name /from date /to date");
        System.out.println("Mark task as done - mark index");
        System.out.println("Mark task as not done - unmark index");
        System.out.println("Delete task - delete index");
        System.out.println("List tasks - list");
        System.out.println("Exit program - bye");
    }

    /**
     * Initialises the UI, displaying options to the user.
     * @return scanner to get input from user
     */
    public static Scanner initialiseUi() {
        System.out.println("");
        System.out.println("What can I do for you?");
        displayOptions();
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
        String s = in.nextLine();
        System.out.println("You entered: " + s);
        return s;
    }
}
