package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class handles the user interface and interacts with the user in the Duke application.
 */
public class Ui {

    private static final String LINE = "──────────────────────────────"
            + "────────────────────────────────────────────";
    private static final String LOGO = " _____   __                 _____ _           _   _           _  ___\n"
            + "|  _\\ \\ / /                /  __ \\ |         | | | |         | ||_  |\n"
            + "| |  \\ V /___  _   _ _ __  | /  \\/ |__   __ _| |_| |__   ___ | |_ | |\n"
            + "| |   \\ // _ \\| | | | '__| | |   | '_ \\ / _` | __| '_ \\ / _ \\| __|| |\n"
            + "| |   | | (_) | |_| | |    | \\__/\\ | | | (_| | |_| |_) | (_) | |_ | |\n"
            + "| |_  \\_/\\___/ \\__,_|_|     \\____/_| |_|\\__,_|\\__|_.__/ \\___/ \\__|| |\n"
            + "|___|                                                           |___|\n";
    private static final String greetPhrase = LINE
            + "\nHello! I'm\n"
            + LOGO
            + "What can I do for you?\n"
            + LINE + "\n";

    private static final String sendOffPhrase = LINE
            + "\nBye. Hope to see you again soon!\n"
            + LINE + "\n";

    /**
     * Displays a greeting message.
     */
    public void greet() {
        System.out.println(greetPhrase);
    }

    /**
     * Displays a farewell message.
     */
    public void sendOff() {
        System.out.println(sendOffPhrase);
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The TaskList to be displayed.
     */
    public void printList(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println(LINE);
            System.out.println("There are no tasks on your list.\n"
                    + "Use todo, event or deadline command to add tasks to your list.");
            System.out.println(LINE + "\n");
            return;
        }
        System.out.println(LINE);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(Integer.toString(i + 1)
                    + ". "
                    + taskList.get(i));
        }
        System.out.println(LINE + "\n");
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param taskAdded The Task that was added.
     * @param listSize The size of the task list after the addition.
     */
    public void printTaskAdded(Task taskAdded, int listSize) {
        System.out.println(LINE
                + "\nGot it. I've added this task:\n"
                + taskAdded
                + "\nNow you have " + listSize + " tasks in the list.\n"
                + LINE
                + "\n");
    }

    /**
     * Displays a message confirming the marking of a task as done.
     *
     * @param task The Task that was marked as done.
     */
    public void printTaskMarked(Task task) {
        System.out.println(LINE
                + "\nNice! I've marked this task as done:\n"
                + task + "\n"
                + LINE + "\n");
    }

    /**
     * Displays a message confirming the marking of a task as not done.
     *
     * @param task The Task that was marked as not done.
     */
    public void printTaskUnmarked(Task task) {
        System.out.println(LINE + "\n"
                + "OK, I've marked this task as not done yet:\n"
                + task + "\n"
                + LINE);
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param task The Task that was deleted.
     * @param listSize The size of the task list after the deletion.
     */
    public void printTaskDeleted(Task task, int listSize) {
        System.out.println(LINE + "\n"
                + "Noted. I've removed this task:\n"
                + task
                + "\nNow you have " + listSize + " tasks in the list.\n"
                + LINE + "\n");
    }

    /**
     * Displays an exception message to the console.
     *
     * @param message The exception message to be displayed.
     */
    public void printException(String message) {
        System.out.println(LINE + "\n"
                + message + "\n"
                + LINE + "\n");
    }


    /**
     * Displays a horizontal line as a separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads a user command from the console.
     *
     * @return The user command entered by the user.
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Prints the list of found tasks to the console.
     *
     * @param taskList The TaskList to be displayed.
     */
    public void printFind(TaskList taskList) {
        System.out.println(LINE);
        if (taskList.size() == 0) {
            System.out.println("There are no matching tasks in your list.");
            System.out.println(LINE);
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(Integer.toString(i + 1)
                    + ". "
                    + taskList.get(i));
        }
        System.out.println(LINE + "\n");
    }
}
