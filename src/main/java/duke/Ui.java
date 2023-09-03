package duke;

import java.util.Scanner;
import duke.task.Task;
import java.util.ArrayList;

/**
 * This class handles the texts that are printed for the users to view
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    private String CHATBOT_NAME = "Richie";

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?");
    }

    /**
     * Prints the error message associated with loading data
     */

    public void showLoadingError() {
        System.out.println("There was an error loading the data!");
    }

    /**
     * Uses the Scanner object to scan for the next user input
     * @return String that the user inputted
     */
    public String scanUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints the bye message
     */
    public void showBye() {
        System.out.println("Bye hope to see you again soon!");
    }

    /**
     * Prints a message that is taken in from its argument
     * @param message String representing the message to be printed to the ui
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message that tells the user that a task has been marked
     * @param task
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n " + task);
    }

    public void showFilteredTasksList(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list: ");
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count + ". " + task.toString());
            count++;
        }
    }
}
