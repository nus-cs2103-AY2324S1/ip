package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that deals with interactions with the user.
 */
public class Ui {
    /**
     * Shows a loading error if failing to initialise starting classes.
     */
    public static void showLoadingError() {
        System.out.println("Unable to initialise duke.Duke.");
    }

    /**
     * Shows welcome message.
     */
    public static void showWelcome() {
        System.out.println("Hello I'm iP");
    }

    /**
     * Shows a list of tasks.
     * @param tasks Tasks to show.
     */
    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("List of tasks:");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        System.out.println("You have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Requests input from the user.
     */
    public static String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Prints a given message from another component.
     * @param message Message to print.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Shows a list of given tasks to the user.
     * @param tasks Tasks to be shown to the user.
     */
    public static void showFoundTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }
}
