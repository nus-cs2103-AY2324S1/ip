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

    public void printMessage(String message) {
        this.output.append(message);
    }

    public String getOutput() {
        return this.output.toString();
    }

    public void clearOutput() {
        this.output = new StringBuilder();
    }

    /**
     * Prints a line separator to the console.
     */
    public void printLineSeparator() {
        printMessage("   _________________________________________"
                + "________________________________________\n");

    }

    /**
     * Styles and prints a message with a line separator above and below it.
     *
     * @param message The message to be styled and printed.
     */
    public void styleMessage(String message) {
        printLineSeparator();
        printMessage(message);
        printLineSeparator();
    }

    /**
     * Displays a greeting message.
     */
    public void greetMessage() {
        styleMessage("    Hello! I'm Arthur Pendragon.\n    What can I do for you?\n");
    }

    /**
     * Displays a farewell message.
     */
    public void byeMessage() {
        printMessage("    Farewell. We will meet again!\n");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The task list to be displayed.
     */
    public void showTasksList(TaskList tasks) {
        if (tasks.isEmpty()) {
            styleMessage("    You haven't added anything, my sire.\n");
        } else {
            printLineSeparator();
            printMessage("   Here are the quests in thy list:" + "\n");
            for (int i = 0; i < tasks.size(); i++) {
                printMessage("    " + (i + 1) + "." + tasks.get(i) + "\n");
            }
            printLineSeparator();
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks     The task list containing the marked task.
     * @param taskIndex The index of the marked task.
     */
    public void showMarkMessage(TaskList tasks, int taskIndex) {
        printLineSeparator();
        printMessage("   Very well. I have marked this task as accomplished:\n  " + "  "
                + tasks.get(taskIndex).getStatusIcon() + " "
                + tasks.get(taskIndex).getDescription() + "\n");
        printLineSeparator();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param tasks     The task list containing the unmarked task.
     * @param taskIndex The index of the unmarked task.
     */
    public void showUnmarkMessage(TaskList tasks, int taskIndex) {
        printLineSeparator();
        printMessage("   By the heavens! I have declared this task as yet to be completed:\n  "
                + "  " + tasks.get(taskIndex).getStatusIcon()
                + " " + tasks.get(taskIndex).getDescription() + "\n");
        printLineSeparator();
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param tasks The task list containing the newly added task.
     */
    public void showAddTaskMessage(TaskList tasks) {
        printLineSeparator();
        printMessage("   Understood. I have included this quest:\n  "
                + "  " + tasks.get(tasks.size() - 1) + "\n");
        printMessage("   Now you have " + tasks.size() + " task(s) in the list.\n");
        printLineSeparator();
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param tasks      The task list from which the task was deleted.
     * @param deletedTask The deleted task.
     */
    public void showDeleteTaskMessage(TaskList tasks, Task deletedTask) {
        printLineSeparator();
        printMessage("   Noted. I've removed this quest:\n");
        printMessage("    " + deletedTask + "\n");
        printMessage("   Now you have " + tasks.size() + " task(s) in the list.\n");
        printLineSeparator();
    }

    /**
     * Displays a message with the matching tasks found by a search operation.
     *
     * @param matchingTasks The TaskList containing matching tasks to be displayed.
     */
    public void showFindMessage(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            styleMessage("    No matching tasks found.\n");
        } else {
            printLineSeparator();
            printMessage("    Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                printMessage("    " + (i + 1) + "." + matchingTasks.get(i) + "\n");
            }
            printLineSeparator();
        }
    }
}
