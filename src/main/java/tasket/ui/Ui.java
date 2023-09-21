package tasket.ui;

import java.util.Scanner;

/**
 * The class for ui.
 */
public class Ui {

    private static final String TEXT_SEPARATION = "\t____________________________________________________________";
    private static final String TEXT_GREETING = "Hello, I'm Tasket\nWhat can I do for you?";
    private static final String TEXT_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String TEXT_ADDED = "Got it. We have added this task:";
    private static final String TEXT_LIST = "Here are the tasks in your list:";
    private static final String TEXT_TASK_LENGTH = "Now you have %d tasks in the list";
    private static final String TEXT_MARKED_TASK = "Nice! I've marked this task as done:";
    private static final String TEXT_UNMARKED_TASK = "Ok, I've marked this task as undone:";
    private static final String TEXT_DELETED_TASK = "Noted, I've deleted this task:";
    private static final String TEXT_MATCHING_TASKS = "Here are the matching tasks in your list:";
    private static final String TEXT_ERROR = "OOPS!!! ";

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
        return TEXT_GREETING;
    }

    /**
     * Show goodbye message.
     */
    public String showGoodBye() {
        return TEXT_GOODBYE;
    }

    /**
     * Show added task message.
     *
     * @param task The added task in string format.
     * @param len The number of tasks after insertion.
     */
    public String showAddedTask(String task, int len) {
        return TEXT_ADDED + "\n" + task + "\n" + String.format(TEXT_TASK_LENGTH, len);
    }

    /**
     * Show all the tasks.
     *
     * @param tasks The tasks to be shown in string format.
     */
    public String showTaskList(String[] tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(TEXT_LIST + "\n");
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
        return TEXT_MARKED_TASK + "\n" + task;
    }

    /**
     * Show unmarked task message.
     *
     * @param task The task to be unmarked in string format.
     */
    public String showUnmarkedTask(String task) {
        return TEXT_UNMARKED_TASK + "\n" + task;
    }

    /**
     * Show deleted task message.
     *
     * @param task The task to be deleted.
     * @param len The number of tasks after deletion.
     */
    public String showDeletedTask(String task, int len) {
        return TEXT_DELETED_TASK + "\n" + task + "\n" + String.format(TEXT_TASK_LENGTH, len);
    }

    /**
     * Show task that match the keyword.
     *
     * @param tasks The matching tasks in string format.
     */
    public String showMatchingTasks(String[] tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(TEXT_MATCHING_TASKS + "\n");
        for (String taskString : tasks) {
            sb.append(taskString + "\n");
        }

        return sb.toString();
    }

    /**
     * Show error message.
     *
     * @param errorMessage The error message to be shown.
     */
    public String showError(String errorMessage) {
        return TEXT_ERROR + errorMessage;
    }
}
