import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    public static void printWelcomeMessage() {
        System.out.println("Hello! I'm BbabBBB");
        System.out.println("What can I do for you?");
    }

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

}
