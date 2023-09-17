package tasket.ui;

import java.util.Scanner;

/**
 * The class for ui.
 */
public class Ui {

    private static final String TEXT_SEPARATION = "\t____________________________________________________________";
    private static final String TEXT_GREETING = "\tHello, I'm Tasket\n\tWhat can I do for you?";
    private static final String TEXT_GOODBYE = "\tBye. Hope to see you again soon!";
    private static final String TEXT_ADDED = "\tGot it. We have added this task:";
    private static final String TEXT_LIST = "\tHere are the tasks in your list:";
    private static final String TEXT_TASK_LENGTH = "\tNow you have %d tasks in the list";
    private static final String TEXT_MARKED_TASK = "\tNice! I've marked this task as done:";
    private static final String TEXT_UNMARKED_TASK = "\tOk, I've marked this task as undone:";
    private static final String TEXT_DELETED_TASK = "\tNoted, I've deleted this task:";
    private static final String TEXT_MATCHING_TASKS = "\tHere are the matching tasks in your list:";
    private static final String TEXT_ERROR = "\tOOPS!!! ";

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
    public void showWelcome() {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_GREETING);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    /**
     * Show goodbye message.
     */
    public void showGoodBye() {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_GOODBYE);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    /**
     * Show added task message.
     *
     * @param task The added task in string format.
     * @param len The number of tasks after insertion.
     */
    public void showAddedTask(String task, int len) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_ADDED);
        System.out.println("\t" + task);
        System.out.printf(TEXT_TASK_LENGTH + "\n", len);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    /**
     * Show all the tasks.
     *
     * @param tasks The tasks to be shown in string format.
     */
    public void showTaskList(String[] tasks) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_LIST);
        for (int i = 1; i <= tasks.length; i++) {
            System.out.printf("\t%d. %s\n", i, tasks[i - 1]);
        }
        System.out.println(TEXT_SEPARATION + "\n");
    }

    /**
     * Show marked task message.
     *
     * @param task The task to be marked in string format.
     */
    public void showMarkedTask(String task) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_MARKED_TASK);
        System.out.println("\t" + task);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    /**
     * Show unmarked task message.
     *
     * @param task The task to be unmarked in string format.
     */
    public void showUnmarkedTask(String task) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_UNMARKED_TASK);
        System.out.println("\t" + task);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    /**
     * Show deleted task message.
     *
     * @param task The task to be deleted.
     * @param len The number of tasks after deletion.
     */
    public void showDeletedTask(String task, int len) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_DELETED_TASK);
        System.out.println("\t" + task);
        System.out.printf(TEXT_TASK_LENGTH + "\n", len);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    /**
     * Show task that match the keyword.
     *
     * @param tasks The matching tasks in string format.
     */
    public void showMatchingTasks(String[] tasks) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_MATCHING_TASKS);
        for (String taskString : tasks) {
            System.out.println("\t" + taskString);
        }
        System.out.println(TEXT_SEPARATION + "\n");
    }

    /**
     * Show error message.
     *
     * @param errorMessage The error message to be shown.
     */
    public void showError(String errorMessage) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_ERROR + errorMessage);
        System.out.println(TEXT_SEPARATION + "\n");
    }
}
