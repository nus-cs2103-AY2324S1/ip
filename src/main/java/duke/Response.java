package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class handles the user interface and interacts with the user in the Duke application.
 */
public class Response {

    private static final String LOGO = " _____   __                 _____ _           _   _           _  ___\n"
            + "|  _\\ \\ / /                /  __ \\ |         | | | |         | ||_  |\n"
            + "| |  \\ V /___  _   _ _ __  | /  \\/ |__   __ _| |_| |__   ___ | |_ | |\n"
            + "| |   \\ // _ \\| | | | '__| | |   | '_ \\ / _` | __| '_ \\ / _ \\| __|| |\n"
            + "| |   | | (_) | |_| | |    | \\__/\\ | | | (_| | |_| |_) | (_) | |_ | |\n"
            + "| |_  \\_/\\___/ \\__,_|_|     \\____/_| |_|\\__,_|\\__|_.__/ \\___/ \\__|| |\n"
            + "|___|                                                           |___|\n";
    private static final String greetPhrase = "\nHello! I'm\n"
            + LOGO
            + "What can I do for you?\n"
            + "\n";

    private static final String sendOffPhrase = "\nBye. Hope to see you again soon!\n";


    /**
     * Displays a greeting message.
     */
    public String greet() {
        return greetPhrase;
    }

    /**
     * Displays a farewell message.
     */
    public String sendOff() {
        return sendOffPhrase;
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The TaskList to be displayed.
     */
    public String printList(TaskList taskList) {
        StringBuilder response = new StringBuilder();

        if (taskList.size() == 0) {
            response.append("There are no tasks on your list.\n")
                    .append("Use the 'todo', 'event', or 'deadline' command to add tasks to your list.\n");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                response.append(i + 1)
                        .append(". ")
                        .append(taskList.get(i))
                        .append("\n");
            }
        }
        return response.toString();
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param taskAdded The Task that was added.
     * @param listSize The size of the task list after the addition.
     */
    public String printTaskAdded(Task taskAdded, int listSize) {
        return ("\nGot it. I've added this task:\n"
                + taskAdded
                + "\nNow you have " + listSize + " tasks in the list.\n");
    }

    /**
     * Displays a message confirming the marking of a task as done.
     *
     * @param task The Task that was marked as done.
     */
    public String printTaskMarked(Task task) {
        return ("Nice! I've marked this task as done:\n"
                + task + "\n");
    }

    /**
     * Displays a message confirming the marking of a task as not done.
     *
     * @param task The Task that was marked as not done.
     */
    public String printTaskUnmarked(Task task) {
        return ("OK, I've marked this task as not done yet:\n"
                + task + "\n");
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param task The Task that was deleted.
     * @param listSize The size of the task list after the deletion.
     */
    public String printTaskDeleted(Task task, int listSize) {
        return ("Noted. I've removed this task:\n"
                + task
                + "\nNow you have " + listSize + " tasks in the list.\n");
    }

    /**
     * Displays an exception message.
     *
     * @param message The exception message to be displayed.
     */
    public String printException(String message) {
        return (message + "\n");
    }

    /**
     * Reads a user command.
     *
     * @return The user command entered by the user.
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Displays the list of found tasks.
     *
     * @param taskList The TaskList to be displayed.
     */
    public String printFind(TaskList taskList) {
        StringBuilder response = new StringBuilder();
        if (taskList.size() == 0) {
            response.append("There are no matching tasks in your list.");
        } else {
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                response.append(Integer.toString(i + 1))
                        .append(". ")
                        .append(taskList.get(i))
                        .append("\n");
            }
        }
        return response.toString();
    }

    /**
     * Displays the list of tasks.
     *
     * @param task The task that was updated.
     */
    public String printUpdated(Task task) {
        return ("OK, I've updated this task:\n"
                + task + "\n");
    }
}
