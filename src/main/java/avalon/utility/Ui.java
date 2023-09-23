package avalon.utility;

import avalon.task.Task;
import avalon.task.TaskList;

import java.util.Scanner;

/**
 * Represents the User Interface (UI) for interacting with the task list.
 */
public class Ui {
    private Scanner scanner;

    private StringBuilder output;

    /**
     * Constructs a new UI instance with a scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.output = new StringBuilder();
    }

    /**
     * Appends a message to the output buffer.
     *
     * @param message The message to be appended.
     */
    public void printMessage(String message) {
        this.output.append(message);
    }

    /**
     * Retrieves the contents of the output buffer as a string.
     *
     * @return The contents of the output buffer.
     */
    public String getOutput() {
        return this.output.toString();
    }

    /**
     * Clears the contents of the output buffer.
     */
    public void clearOutput() {
        this.output = new StringBuilder();
    }

    /**
     * Displays a farewell message.
     */
    public void byeMessage() {
        printMessage("Farewell. We will meet again!\n");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The task list to be displayed.
     */
    public void showTasksList(TaskList tasks) {
        if (tasks.isEmpty()) {
            printMessage("You haven't added anything, my sire.\n");
        } else {
            printMessage("Here are the quests in thy list:" + "\n");
            for (int i = 0; i < tasks.size(); i++) {
                printMessage("    " + (i + 1) + "." + tasks.get(i) + "\n");
            }
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks     The task list containing the marked task.
     * @param taskIndex The index of the marked task.
     */
    public void showMarkMessage(TaskList tasks, int taskIndex) {
        printMessage("Very well. I have marked this task as accomplished:\n  " + "  "
                + tasks.get(taskIndex).getStatusIcon() + " "
                + tasks.get(taskIndex).getDescription() + "\n");
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param tasks     The task list containing the unmarked task.
     * @param taskIndex The index of the unmarked task.
     */
    public void showUnmarkMessage(TaskList tasks, int taskIndex) {
        printMessage("By the heavens! I have declared this task as yet to be completed:\n  "
                + "  " + tasks.get(taskIndex).getStatusIcon()
                + " " + tasks.get(taskIndex).getDescription() + "\n");
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param tasks The task list containing the newly added task.
     */
    public void showAddTaskMessage(TaskList tasks) {
        printMessage("Understood. I have included this quest:\n  "
                + "  " + tasks.get(tasks.size() - 1) + "\n");
        printMessage("Now you have " + tasks.size() + " task(s) in the list.\n");
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param tasks      The task list from which the task was deleted.
     * @param deletedTask The deleted task.
     */
    public void showDeleteTaskMessage(TaskList tasks, Task deletedTask) {
        printMessage("Noted. I've removed this quest:\n");
        printMessage("    " + deletedTask + "\n");
        printMessage("Now you have " + tasks.size() + " task(s) in the list.\n");
    }

    /**
     * Displays a message with the matching tasks found by a search operation.
     *
     * @param matchingTasks The TaskList containing matching tasks to be displayed.
     */
    public void showFindMessage(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            printMessage("No matching tasks found.\n");
        } else {
            printMessage("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                printMessage("    " + (i + 1) + "." + matchingTasks.get(i) + "\n");
            }
        }
    }

    public void showPriorityMessage(TaskList tasks, int taskIndex) {
        printMessage("Very well. I have marked the priority of the task:\n  "
                + " [" + tasks.get(taskIndex).getPriority() + "] "
                + tasks.get(taskIndex).getDescription() + "\n");
    }
}
