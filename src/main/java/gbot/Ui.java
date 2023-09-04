package gbot;

/**
 * The user interface of the chatbot.
 *
 * @author Gallen Ong
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Prints a message in between two lines.
     *
     * @param message The message to be printed.
     */
    public static void print(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Prints the initial greeting to user.
     */
    public void ask() {
        print("Hello I'm GBot!\nWhat can I do for you?");
    }

    /**
     * Prints the closing message to user.
     */
    public void end() {
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out an error message.
     *
     * @param message The error message to be printed.
     */
    public static void showError(String message) {
        print(message);
    }
}
