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
        Ui.print("Hello I'm GBot!\nWhat can I do for you?");
    }

    /**
     * Prints the closing message to user.
     */
    public void end() {
        Ui.print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out an error message.
     *
     * @param message The error message to be printed.
     */
    public static void showError(String message) {
        Ui.print(message);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    /**
     * Prints out the empty command error message.
     */
=======
>>>>>>> branch-A-CodingStandard
=======
>>>>>>> branch-Level-9
    public static void showEmptyCommandError() {
        print("Please enter a command.");
    }

<<<<<<< HEAD
<<<<<<< HEAD
    /**
     * Prints out the task number error message.
     */
=======
>>>>>>> branch-A-CodingStandard
=======
>>>>>>> branch-Level-9
    public static void showTaskNumberError() {
        print("Please enter a task number.");
    }

    /**
     * Prints the empty keyword error.
     */
    public static void showEmptyKeywordError() {
        print("Please enter a keyword.");
    }
}
