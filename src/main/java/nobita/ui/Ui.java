package nobita.ui;

import java.util.Scanner;

/**
 *  Class that encapsulates Ui.
 *  Ui is used to display message to the user.
 *
 *  @author Zheng Chenglong
 */
public class Ui {

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Nobita");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads user input.
     *
     * @return A String that represents the user input.
     */
    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Print the exit message.
     */
    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("________________________________________________________");
    }

    /**
     * Prints a error message.
     *
     * @param message The error message to be display to user.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
