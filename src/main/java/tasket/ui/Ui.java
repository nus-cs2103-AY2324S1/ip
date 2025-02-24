package tasket.ui;

import static tasket.commons.Messages.MESSAGE_ADDED;
import static tasket.commons.Messages.MESSAGE_DELETED_TASK;
import static tasket.commons.Messages.MESSAGE_ERROR;
import static tasket.commons.Messages.MESSAGE_GOODBYE;
import static tasket.commons.Messages.MESSAGE_LIST;
import static tasket.commons.Messages.MESSAGE_MARKED_TASK;
import static tasket.commons.Messages.MESSAGE_MATCHING_TASKS;
import static tasket.commons.Messages.MESSAGE_TASK_LENGTH;
import static tasket.commons.Messages.MESSAGE_UNMARKED_TASK;
import static tasket.commons.Messages.MESSAGE_WELCOME;

import java.util.Scanner;

/**
 * The class for ui.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructs a ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Shows welcome message.
     *
     * @return Welcome message.
     */
    public String showWelcome() {
        return MESSAGE_WELCOME;
    }

    /**
     * Shows goodbye message.
     *
     * @return Goodbye message.
     */
    public String showGoodBye() {
        return MESSAGE_GOODBYE;
    }

    /**
     * Shows added task message.
     *
     * @param task The added task in string format.
     * @param len The number of tasks after insertion.
     * @return The added task and the current number of tasks.
     */
    public String showAddedTask(String task, int len) {
        return MESSAGE_ADDED + "\n" + task + "\n" + String.format(MESSAGE_TASK_LENGTH, len);
    }

    /**
     * Shows all the tasks.
     *
     * @param tasks The tasks to be shown in string format.
     * @return The tasks.
     */
    public String showTaskList(String[] tasks) {
        assert tasks != null;

        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_LIST + "\n");
        for (int i = 1; i <= tasks.length; i++) {
            sb.append(String.format("%d. %s\n", i, tasks[i - 1]));
        }

        return sb.toString();
    }

    /**
     * Shows marked task message.
     *
     * @param task The task to be marked in string format.
     * @return The marked task.
     */
    public String showMarkedTask(String task) {
        return MESSAGE_MARKED_TASK + "\n" + task;
    }

    /**
     * Shows unmarked task message.
     *
     * @param task The task to be unmarked in string format.
     * @return The unmarked task.
     */
    public String showUnmarkedTask(String task) {
        return MESSAGE_UNMARKED_TASK + "\n" + task;
    }

    /**
     * Shows deleted task message.
     *
     * @param task The task to be deleted.
     * @param len The number of tasks after deletion.
     * @return The deleted task and remaining number of tasks.
     */
    public String showDeletedTask(String task, int len) {
        return MESSAGE_DELETED_TASK + "\n" + task + "\n" + String.format(MESSAGE_TASK_LENGTH, len);
    }

    /**
     * Shows task that match the keyword.
     *
     * @param tasks The matching tasks in string format.
     * @return Matching tasks.
     */
    public String showMatchingTasks(String[] tasks) {
        assert tasks != null;

        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_MATCHING_TASKS + "\n");
        for (String taskString : tasks) {
            sb.append(taskString).append("\n");
        }

        return sb.toString();
    }

    /**
     * Shows error message.
     *
     * @param errorMessage The error message to be shown.
     * @return The error message.
     */
    public String showError(String errorMessage) {
        return MESSAGE_ERROR + errorMessage;
    }
}
