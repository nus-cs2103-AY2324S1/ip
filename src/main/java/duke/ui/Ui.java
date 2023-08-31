package duke.ui;

import java.util.Scanner;

/**
 * Handles User Interface output
 */
public class Ui {

    /** The Indentation Level to format text. */
    public static final String INDENTATION = "    ";

    /** Scanner to read user input. */
    private Scanner sc = new Scanner(System.in);


    /**
     * Returns the input from the user.
     *
     * @return The input from the user as a String.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Formats and Returns the given input.
     *
     * @param output The raw output to be formatted.
     * @return The formatted output as a String.
     */
    private static String formatOutput(String output) {
        String horizontalLine = "____________________________________________________________";
        return INDENTATION + horizontalLine + "\n " +
                INDENTATION + output + '\n' + INDENTATION + horizontalLine + '\n';
    }

    /**
     * Prints the Greeting of the Chatbot.
     */
    public void printGreeting() {
        System.out.println(formatOutput("Hello! I'm Nano\n" + INDENTATION + " What can I do for you?"));
    }

    /**
     * Prints the Goodbye of the Chatbot.
     */
    public void printBye() {
        System.out.println(formatOutput("Bye. Hope to see you again soon!"));
    }

    /**
     * Prints the Invalid Command Output of the Chatbot.
     */
    public void printInvalidCommandError() {
        System.out.println(formatOutput("I don't understand what you're saying."));
    }


    /**
     * Prints the formatted output of the Chatbot.
     *
     * @param rawOutput The raw output to be formatted and printed.
     */
    public void printOutput(String rawOutput) {
        System.out.println(formatOutput(rawOutput));
    }
}
