package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        printMessage("Error while loading tasks from file.");
    }

    public void showError(String errorMessage) {
        printMessage(errorMessage);
    }

    public void close() {
        scanner.close();
    }
}
