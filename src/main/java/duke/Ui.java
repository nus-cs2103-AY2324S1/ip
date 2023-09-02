package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class deals wuthe interactions with the user.
 *
 * @author Inez Kok
 */
public class Ui {
    private static final String NAME = "Beep Boop Bot";
    private Scanner sc;

    /**
     * The constructor for a Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * This method is used to print the line separator.
     */
    public void printLine() {
        System.out.println("─".repeat(100));
    }

    /**
     * This method is used to print the message and a line separator.
     *
     * @param message The string representation of the message to be printed.
     */
    public void printMessage(String message) {
        System.out.printf("\t%s\n", message);
        printLine();
    }

    /**
     * This method is used to print the greeting message.
     */
    public void printGreetingMessage() {
        printLine();
        String greetingMessage = String.format("Hello! I'm %s!\n\tHow can I help you?\n", NAME);
        printMessage(greetingMessage);
    }

    /**
     * This method is used to print the exit message.
     */
    public void printExitMessage() {
        String exitMessage = "Bye Bye! Hope to see you again soon! Beep Boop!";
        printMessage(exitMessage);
    }

    /**
     * This method is used to print the list of tasks.
     *
     * @param list The array list of tasks.
     */
    public void printList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("\t\t%d. %s\n", i + 1, list.get(i));
        }
        printLine();
    }

    /**
     * This method is used to print the message that should appear when a task is successfully added.
     *
     * @param task The task that was successfully added.
     * @param list The updated list of tasks.
     */
    public void printAddSuccessMessage(Task task, ArrayList<Task> list) {
        System.out.println("\tGot it. I've added this task:");
        System.out.printf("\t\t%s\n\tNow you have %d tasks in the list.\n", task.toString(), list.size());
        printLine();
    }

    /**
     * This method is used to print the message that should appear when a task is successfully deleted.
     *
     * @param index The zero-based index of the task that was successfully deleted.
     * @param list The list of tasks before being updated.
     */
    public void printDeleteSuccessMessage(int index, ArrayList<Task> list) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.printf("\t\t%s\n\tNow you have %d tasks in the list.\n",
                list.get(index).toString(), list.size() - 1);
        printLine();
    }

    /**
     * This method is used to print the error message that show appearing when there is an error loading the tasks.
     */
    public void showLoadingError() {
        printMessage("Boop Beep OOPS! I seem to have troubles loading the file :(");
    }

    /**
     * This method is used to print error messages.
     *
     * @param error The error message to be printed.
     */
    public void showError(String error) {
        printMessage(error);
    }

    /**
     * This method is used to read the next line of the input.
     *
     * @return Returns the string representation of the next line.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
