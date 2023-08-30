import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        System.out.println(Simon.SPACEN + SimonAscii.toStr());
        System.out.println("Hello! I'm Simon\nWhat can I do for you?\n" + Simon.SPACE);
    }

    public void showLoadingError() {
        System.out.println("Data file not found. Starting with an empty task list.");
    }

    public void showMessage(String message) {
        System.out.println(message + Simon.NSPACE);
    }

    public String readInput(Scanner scanner) {
        return scanner.nextLine();
    }
}
