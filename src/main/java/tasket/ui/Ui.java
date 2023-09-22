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
     * The constructor of ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Read the user input and return it.
     * Preprocess the input by removing additional spaces.
     *
     * @return The user input.
     */
    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    /**
     * Show welcome message.
     */
    public String showWelcome() {
        return MESSAGE_WELCOME;
    }

    /**
     * Show goodbye message.
     */
    public String showGoodBye() {
        return MESSAGE_GOODBYE;
    }

    /**
     * Show added task message.
     *
     * @param task The added task in string format.
     * @param len The number of tasks after insertion.
     */
    public String showAddedTask(String task, int len) {
        return MESSAGE_ADDED + "\n" + task + "\n" + String.format(MESSAGE_TASK_LENGTH, len);
    }

    /**
     * Show all the tasks.
     *
     * @param tasks The tasks to be shown in string format.
     */
    public String showTaskList(String[] tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_LIST + "\n");
        for (int i = 1; i <= tasks.length; i++) {
            sb.append(String.format("%d. %s\n", i, tasks[i - 1]));
        }

        return sb.toString();
    }

    /**
     * Show marked task message.
     *
     * @param task The task to be marked in string format.
     */
    public String showMarkedTask(String task) {
        return MESSAGE_MARKED_TASK + "\n" + task;
    }

    /**
     * Show unmarked task message.
     *
     * @param task The task to be unmarked in string format.
     */
    public String showUnmarkedTask(String task) {
        return MESSAGE_UNMARKED_TASK + "\n" + task;
    }

    /**
     * Show deleted task message.
     *
     * @param task The task to be deleted.
     * @param len The number of tasks after deletion.
     */
    public String showDeletedTask(String task, int len) {
        return MESSAGE_DELETED_TASK + "\n" + task + "\n" + String.format(MESSAGE_TASK_LENGTH, len);
    }

    /**
     * Show task that match the keyword.
     *
     * @param tasks The matching tasks in string format.
     */
    public String showMatchingTasks(String[] tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_MATCHING_TASKS + "\n");
        for (String taskString : tasks) {
            sb.append(taskString).append("\n");
        }

        return sb.toString();
    }

    /**
     * Show error message.
     *
     * @param errorMessage The error message to be shown.
     */
    public String showError(String errorMessage) {
        return MESSAGE_ERROR + errorMessage;
    }
}
