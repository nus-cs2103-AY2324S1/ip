package sam.services;

import sam.constants.Message;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user's input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Generates and prints the divider.
     */
    public void showLine() {
        System.out.println("______________________________________________________" +
                "______\n");
    }

    /**
     * Generates and prints the message with dividers.
     *
     * @param args full result args string
     */
    public void printMessage(String... args) {
        this.showLine();
        for (String arg : args) {
            System.out.printf(arg + "\n");
        }
        this.showLine();
    }

    /**
     * Generates and prints the ending message upon the end of the application.
     */
    public void shutDown() {
        this.printMessage(Message.BYE);
        scanner.close();
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        this.printMessage(Message.WELCOME);
    }

    /**
     * Generates and prints the error message upon each error faced(if any).
     *
     * @param error the error message from the exception
     */
    public void showError(String error) {
        this.printMessage(Message.ERROR + " " + error);
    }

}
