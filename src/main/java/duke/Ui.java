package duke;

import java.util.Scanner;
import duke.task.Task;

/**
 * This class handles the texts that are printed for the users to view
 */
public class Ui {
    public Scanner sc = new Scanner(System.in);
    private String CHATBOT_NAME = "Richie";
    public void showWelcome() {
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("There was an error loading the data!");
    }

    public String scanUserInput() {
        return sc.nextLine();
    }

    public void showBye() {
        System.out.println("Bye hope to see you again soon!");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n " + task);
    }
}
