package robert.ui;

import robert.exception.RobertException;

import java.util.Scanner;

/**
 * A Ui class that is used to display messages from Robert to CLI.
 *
 * @author Lee Zhan Peng
 */
public class Ui {

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        String logo = "    ____        __              __ \n"
                + "   / __ \\____  / /_  ___  _____/ /_\n"
                + "  / /_/ / __ \\/ __ \\/ _ \\/ ___/ __/\n"
                + " / _, _/ /_/ / /_/ /  __/ /  / /_  \n"
                + "/_/ |_|\\____/_.___/\\___/_/   \\__/  \n"
                + "\n";

        String text = "Hello! I'm Robert, your Personal Assistant Chatbot.\n"
                + "What can I do for you today?";

        this.showLine();
        System.out.print(formatOutput(logo + text));
        this.showLine();
    }

    /**
     * Prints an error message regarding the inability to load saved tasks.
     */
    public void showLoadingError() {
        System.out.print(formatOutput("WARNING: Your previously stored tasks seems to be corrupted.\n"
                + "As a result, your previous lists of tasks will now be cleared. Apologies!"));
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.print("\t____________________________________________________________\n");
    }

    /**
     * Prints a message.
     *
     * @param message the text to be shown in CLI.
     */
    public void showMessage(String message) {
        System.out.print(formatOutput(message));
    }

    /**
     * Prints a error message.
     *
     * @param errorMessage the error text to be shown in CLI.
     */
    public void showError(String errorMessage) {
        System.out.print(formatOutput(errorMessage));
    }

    /**
     * Reads user input.
     *
     * @return string of the user input from the CLI.
     */
    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }


    /**
     * Format a string to suit the structure of the message to be printed.
     *
     * @param output the text to be formatted.
     * @return formatted string.
     */
    private static String formatOutput(String output) {
        String[] outputLines = output.split("\n");
        for (int i = 0; i < outputLines.length; i++) {
            outputLines[i] = "\t" + outputLines[i] + "\n";
        }
        return String.join("", outputLines);
    }
}
