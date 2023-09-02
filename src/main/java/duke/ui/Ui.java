package duke.ui;

import java.util.Scanner;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles the interaction with the user.
 */
public class Ui {

    String logo = " ___  __    ________  ________  ________   ________  ________      \r\n" + //
            "|\\  \\|\\  \\ |\\   __  \\|\\   __  \\|\\   ___  \\|\\   __  \\|\\   ____\\     \r\n" + //
            "\\ \\  \\/  /|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\___|_    \r\n" + //
            " \\ \\   ___  \\ \\   _  _\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\_____  \\   \r\n" + //
            "  \\ \\  \\\\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\|____|\\  \\  \r\n" + //
            "   \\ \\__\\\\ \\__\\ \\__\\\\ _\\\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\____\\_\\  \\ \r\n" + //
            "    \\|__| \\|__|\\|__|\\|__|\\|_______|\\|__| \\|__|\\|_______|\\_________\\\r\n" + //
            "                                                       \\|_________|";

    String divider = "\n____________________________________________________________\n";

    Scanner sc;

    /**
     * Initialises the Ui by creating a scanner object for user inputs.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the divider.
     */
    public void printDivider() {
        System.out.println(divider);
    }

    /**
     * Prints message for invalid command.
     */
    public void invalidCommandMessage() {
        printDivider();
        showError("Do not test my patience, mortal. Speak clearly.");
        printDivider();
    }

    /**
     * Prints the welcome message.
     */
    public void startMessage() {
        System.out.println("Greetings, puny mortal. This is \n" + logo
                + "\nThe Lord of Time. \nWhat foolish errand do you seek to accomplish with my immense powers?");
        printDivider();
    }

    /**
     * Prints the error message.
     * 
     * @param errorMessage The error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Takes in one user input as a String.
     * 
     * @return The user input as a String.
     */
    public String commandPrompt() {
        return sc.nextLine();
    }

    /**
     * Prints the list of tasks.
     * 
     * @param taskList The list of tasks to be printed.
     */
    public void listTasks(TaskList taskList) {
        printDivider();
        System.out.println(
                "You have somehow found the audacity to conjure up this laughable list of inconsequential endeavours:\n");
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(i + ". " + taskList.getTask(i - 1));
        }
        printDivider();
    }

    /**
     * Prints the message for when a task is marked as done.
     * 
     * @param task The task that was marked as done.
     */
    public void markTaskMessage(Task task) {
        printDivider();
        System.out.println(
                "Astonishingly enough, you have managed to triumph over this mind-bogglingly simple task:\n");
        System.out.println(task.toString());
        printDivider();
    }

    /**
     * Prints the message for when a task is unmarked.
     * 
     * @param task The task that was unmarked.
     */
    public void unmarkTaskMessage(Task task) {
        printDivider();
        System.out.println(
                "You have somehow managed to fail this mind-bogglingly simple task:\n");
        System.out.println(task.toString());
        printDivider();
    }

    /**
     * Prints the message for when a todo task is added.
     * 
     * @param task The todo task that was added.
     */
    public void todoMessage(Task task) {
        printDivider();
        System.out.println("This task has been reluctantly bestowed upon your ever-growing list:\n");
        System.out.println(task.toString());
    }

    /**
     * Prints the message for when a deadline task is added.
     * 
     * @param task The deadline task that was added.
     */
    public void deadlineMessage(Task task) {
        printDivider();
        System.out.println(
                "With your constant mediocrity, it is entirely unlikely that you will be able to meet this deadline I have just added: \n");
        System.out.println(task.toString());
    }

    /**
     * Prints the message for when an event task is added.
     * 
     * @param task The event task that was added.
     */
    public void eventMessage(Task task) {
        printDivider();
        System.out.println(
                "Looks like I will have to slow time down myself if you wish to make it to this event I just added:\n");
        System.out.println(task.toString());
    }

    /**
     * Prints the message for when a task is deleted.
     * 
     * @param task The task that was deleted.
     */
    public void deleteMessage(Task task) {
        printDivider();
        System.out.println(
                "One less annoyance to plague your feeble list. This task has been banished:\n");
        System.out.println(task.toString());
    }

    /**
     * Prints the end message.
     */
    public void endMessage() {
        printDivider();
        System.out.println("Is that all? I have better things to do than to listen to lesser beings. Farewell.");
        printDivider();
    }

    /**
     * Prints the message for when the task list size changes.
     * 
     * @param size    The new size of the task list.
     * @param growing Whether a task was added or removed (true for added).
     */
    public void taskListSizeMessage(int size, boolean growing) {
        if (growing) {
            System.out.println("Congratulations, your pile of tasks has swelled to a whopping " + size + ".");
        } else {
            System.out.println("Your pile of tasks has shrunk to a measly " + size + ".");
        }
        printDivider();
    }
}
