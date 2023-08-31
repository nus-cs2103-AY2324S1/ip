package duke;
import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void greet() {
        System.out.println("______________________________________\n"
                + "Hi, I'm Chatty\n"
                + "What do you need to do today?\n"
                + "______________________________________");
    }

    public void showLine() {
        System.out.println("______________________________________\n");
    }

    public void goodbye() {
        System.out.println("Bye. Don't come back!\n");
    }

    public String awaitCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Unable to find tasks in saved data.");
        System.out.println("Creating new data file with empty list of tasks.");
        System.out.println("Aren't you glad you have me to keep track of everything your tiny mind can't?");
    }

    public void showError(String e) {
        System.out.println(e);
    }

}
