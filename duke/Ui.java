package duke;
import java.util.Scanner;
public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    protected void greeting() {
        System.out.println("Hello! I'm Lakinta "  + "\nWhat can I do for you?");
    }

    protected void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    protected String getUserInput() {
        return scanner.nextLine();
    }

    protected void closeScanner() {
        scanner.close();
    }
    protected void showMessage(String message) {
        System.out.println(message);
    }
}
