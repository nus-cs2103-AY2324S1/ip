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
    public static void greetUser() {
        print("Hello I'm GBot.\nHow may I assist you today?");
    }

    /**
     * Prints the closing message to user.
     */
    public static void displayExitMessage() {
        print("Thank you for your time G. I'll be of your service at any time.");
    }

    /**
     * Prints a help guide with commands to user.
     */
    public static String getHelpMessage() {
        return "Here are some commands for me to better assist you with:\n" +
                  "1) list (eg. list): display the current tasks you have\n" +
                  "2) todo (eg. todo read book): adds a Todo task\n" +
                  "3) deadline (eg. deadline return book /by 2023-09-21): adds a Deadline task\n" +
                  "4) event (eg. event meeting /from 2023-09-21 /to 2023-09-22): adds an Event task\n" +
                  "5) mark (eg. mark 1): marks a task based on index as done\n" +
                  "6) markAll (eg. markAll): marks all existing tasks as done\n" +
                  "7) unmark (eg. unmark 1): marks a task based on index as NOT done\n" +
                  "8) unmarkAll (eg. unmarkAll): unmarks all existing tasks\n" +
                  "9) delete (eg. delete 1): deletes a task based on index\n" +
                  "10) deleteAll (eg. deleteAll): deletes all existing tasks in the list\n" +
                  "11) find (eg. find book): displays a list of tasks containing the provided keyword\n" +
                  "12) bye (eg. bye): exits the program";
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
