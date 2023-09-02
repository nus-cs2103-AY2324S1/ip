package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Meep.");
        System.out.println("What can I do for you?");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye! Hope to see you again soon.");
    }

    public void closeScanner() {
        scanner.close();
    }
}

