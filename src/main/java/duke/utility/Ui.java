package duke.utility;

import duke.exception.BobiException;
import duke.task.Task;

import java.util.Scanner;

/**
 * Ui class encapsulates all system messages Bobi displays in different scenarios.
 *
 * @author ruo-x
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________\n";
    private static final String GREETING = "Hello! I'm Bobi >.<, what can I do for you?\n";
    private static final String EXIT = "Bye! Hope you have a good day today :)\n";
    private static final String ADD_TASK = "New task added to your list:\n";
    private static String COUNT_TASK;
    private static final String LIST_TASKS = "Things you need to do: \n";
    private static final String UPDATE_TASK = "OK! Your task has now been updated to: \n";
    private static final String DELETE_TASK = "Alright! I have deleted this task from the list:\n";

    private final Scanner sc;
    private final TaskList taskList;

    /**
     * Constructor of an Ui object.
     *
     * @param taskList Current task list.
     */
    public Ui(TaskList taskList) {
        this.sc = new Scanner(System.in);
        this.taskList = taskList;
    }

    /**
     * Retrieves user's input from the keyboard.
     */
    public String getInput() {
        return this.sc.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void stopScanner() {
        this.sc.close();
    }

    /**
     * Returns a system message greeting the user.
     */
    public static String greeting() {
        return SEPARATOR + GREETING + SEPARATOR;
    }

    /**
     * Returns a system message informing user a task has been added to Bobi task list
     * and the number of tasks in the task list after the action.
     *
     * @param task Task added into Bobi task list.
     */
    public String printAddTask(Task task) {
        COUNT_TASK = "You currently have " + this.taskList.getLength() + " tasks to do.\n";
        return SEPARATOR + ADD_TASK + task.toString() + "\n" + COUNT_TASK + SEPARATOR;
    }

    /**
     * Returns a system message listing all tasks currently in the task list.
     */
    public String listTasks() {
        return SEPARATOR + LIST_TASKS + this.taskList.toString() + SEPARATOR;
    }

    /**
     * Returns a system message informing the user that the status of a task has been updated.
     *
     * @param task Task that has updated its status.
     */
    public static String printUpdateTask(Task task) {
        return SEPARATOR + UPDATE_TASK + task.toString() + "\n" + SEPARATOR;
    }

    /**
     * Returns a system error message of a specific BobiException.
     *
     * @param e BobiException to throw the message.
     */
    public static String printError(BobiException e) {
        return SEPARATOR + e.getMessage() + "\n" + SEPARATOR;
    }

    /**
     * Returns a system message to inform user that a task has been deleted.
     *
     * @param task Task deleted from the task list.
     */
    public String printDeleteTask(Task task) {
        COUNT_TASK = "You currently have " + this.taskList.getLength() + " tasks to do.\n";
        return SEPARATOR + DELETE_TASK + task.toString() + "\n" + COUNT_TASK + SEPARATOR;
    }

    /**
     * Returns a system message to say goodbye to the user.
     */
    public static String exit() {
        return SEPARATOR + EXIT + SEPARATOR;
    }
}
