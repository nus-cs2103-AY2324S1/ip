package duke.util;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hi! I'm TaskMate.\n"
                + "What can I do for you today?");
        printLine();
    }

    public void showGoodbye() {
        System.out.println("Goodbye! See you next time!");
        printLine();
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
        printLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getUserInput() {
        System.out.println("Enter command: ");
        return this.scanner.nextLine();
    }

    public void printLine() {
        System.out.println("___________________________________________________________________");
    }
}
