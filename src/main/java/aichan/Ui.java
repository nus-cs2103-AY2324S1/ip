package aichan;

import java.util.Scanner;

/**
 * Represents the user interface.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a ui object.
     * Initializes the scanner.
     */
    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println("_______________________________________________________________________");
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        String greet = "Hiya! I'm Ai-chan~\n" +
                "Hey there, dear viewer, what's on your mind?\n" +
                "Is there anything I can do to sprinkle some magic into your day?";
        this.showLine();
        System.out.println(greet);
        this.showLine();
    }

    /**
     * Displays error message.
     *
     * @param errMsg The message indicating the details of the error.
     */
    public void showError(String errMsg) {
        System.out.println(errMsg);
    }

    /**
     * Displays the message.
     *
     * @param msg The message indicating what has been done.
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Reads the user's input.
     *
     * @return String of user's input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
