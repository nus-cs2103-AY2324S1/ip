package duke;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the user interface for interacting with the chatbot.
 */
public class Ui {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    private Scanner scanner;

    /**
     * Constructs a new UI instance with a scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message.
     */
    public static void showGreeting() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello! I'm yourChatBot");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Retrieves user input from the scanner.
     *
     * @return The user's input as a string.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the list of tasks in the task list.
     *
     * @param taskList The task list containing the tasks to be displayed.
     */
    public static void showList(TaskList taskList) {
        String todolistoutput = "";
        for (int i = 0; i < taskList.getTotalTasks(); i++) {
            todolistoutput += i + 1 + ". " + taskList.getTasks().get(i) + "\n";
        }
        System.out.println(LINE_SEPARATOR);
        System.out.println(todolistoutput);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public static void showMarkedAsDone(Task task) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public static void showUnmarked(Task task) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task       The task that has been removed.
     * @param totalTasks The total number of tasks remaining.
     */
    public static void showRemoved(Task task, int totalTasks) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public static void showError(String errorMessage) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(errorMessage);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a confirmation message after adding a task.
     *
     * @param task       The task that has been added.
     * @param totalTasks The total number of tasks in the list.
     */
    public static void showAddConfirmation(Task task, int totalTasks) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a farewell message.
     */
    public static void showByeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a list of tasks that match the given keyword to the user.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     */
    public static void showMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(i + 1 + ". " + matchingTasks.get(i));
        }
        System.out.println(LINE_SEPARATOR);
    }

}
