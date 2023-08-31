package duke;
import java.util.Scanner;

/**
 * Interface that users interact with.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints greeting message when users start up the bot.
     */
    public void greet() {
        System.out.println("______________________________________\n"
                + "Hi, I'm Chatty\n"
                + "What do you need to do today?\n"
                + "______________________________________");
    }

    /**
     * Displays a line for presentation.
     */
    public void showLine() {
        System.out.println("______________________________________\n");
    }

    /**
     * Prints a goodbye message when users close the bot.
     */
    public void goodbye() {
        System.out.println("Bye. Don't come back!\n");
    }

    /**
     * Awaits user input.
     * @return User input for procesing
     */
    public String awaitCommand() {
        return sc.nextLine();
    }

    /**
     * Prints lines to inform user that there are no saved tasks in the data.
     */
    public void showLoadingError() {
        System.out.println("Unable to find tasks in saved data.");
        System.out.println("Creating new data file with empty list of tasks.");
        System.out.println("Aren't you glad you have me to keep track of everything your tiny mind can't?");
    }

    /**
     * Prints error message of any Exceptions.
     * @param e Error message of exception
     */
    public void showError(String e) {
        System.out.println(e);
    }

}
