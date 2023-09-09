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
    private String currentMessage = "";

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        currentMessage =  "Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?";
    }

    /**
     * Prints the error message associated with loading data
     */

    public void showLoadingError() {
        currentMessage = "There was an error loading the data!";
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
        currentMessage = "Bye hope to see you again soon!";
    }

    /**
     * Prints a message that is taken in from its argument
     * @param message String representing the message to be printed to the ui
     */
    public void showMessage(String message) {
        currentMessage = message;
    }

    /**
     * Prints a message that tells the user that a task has been marked
     * @param task
     */
    public void showTaskMarked(Task task) {
        currentMessage = "Nice! I've marked this task as done:\n " + task;
    }

    /**
     * Prints the tasks that have been filtered down by the keyword
     * @param tasks ArrayList of tasks that have been filtered down by the keyword
     */
    public void showFilteredTasksList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list: \n") ;
        int count = 1;
        for (Task task : tasks) {
            sb.append(count).append(". ").append(task.toString()).append("\n");
            count++;
        }
        currentMessage = sb.toString();
    }

    public String getCurrentMessage() {
        return this.currentMessage;
    }
}
