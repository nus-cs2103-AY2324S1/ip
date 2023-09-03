package bob.ui;

import java.util.Scanner;

import bob.exception.BobInvalidTaskNumberException;
import bob.task.Task;
import bob.task.TaskList;

/**
 * Represents the text user interface at which the user reads and writes
 * commands.
 */
public class TextUi {

    private static final String DIVIDER = "\n____________________________________________________________\n";
    private static final String LOGO =
            ".-. .-')              .-. .-')   \n"
        + "\\  ( OO )             \\  ( OO )  \n"
        + " ;-----.\\  .-'),-----. ;-----.\\  \n"
        + " | .-.  | ( OO'  .-.  '| .-.  |  \n"
        + " | '-' /_)/   |  | |  || '-' /_) \n"
        + " | .-. `. \\_) |  |\\|  || .-. `.  \n"
        + " | |  \\  |  \\ |  | |  || |  \\  | \n"
        + " | '--'  /   `'  '-'  '| '--'  / \n"
        + " `------'      `-----' `------'  ";

    private Scanner scanner;

    /**
     * Constructor of the text UI.
     */
    public TextUi() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a divider acting as a horizontal line break to standard output.
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a welcome message to greet the user to standard output.
     */
    public void printWelcomeMessage() {
        String welcomeMessage = "Hi, I'm Bob. How can I help you?";
        System.out.println(DIVIDER + LOGO + "\n" + welcomeMessage + DIVIDER);
    }

    /**
     * Prints a farewell message to user to standard output.
     */
    public void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye! Bob signing out!";
        System.out.println(goodbyeMessage);
    }

    /**
     * Prints out a message to notify user how many tasks are left currently.
     * Prints out lists of task with numbering based on order of creation.
     *
     * @param taskList List of tasks to be counted
     */
    public void printListEndMessage(TaskList taskList) {
        int numOfTasks = taskList.size();
        if (numOfTasks == 0) {
            System.out.println("You currently have no tasks! Good Job!");
        } else {
            if (numOfTasks == 1) {
                System.out.printf("\nNow you have %d task in your list!%n", numOfTasks);
            } else {
                System.out.printf("\nNow you have %d tasks in your list!%n", numOfTasks);
            }
        }
    }

    /**
     * Prints out an error message based on error occurred to standard output.
     *
     * @param e Exception caught
     */
    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints out the list of currently saved tasks to standard output.
     *
     * @param taskList Lists of tasks to be printed
     * @throws BobInvalidTaskNumberException If an error occurs when accessing a task
     */
    public void printListMessage(TaskList taskList) throws BobInvalidTaskNumberException {
        printRawList(taskList);
        printListEndMessage(taskList);
    }

    /**
     * Prints a message to notify the user that a task has been mark/unmark as done to
     * standard output.
     *
     * @param task Task to be mark/unmark
     * @param isDone Completion status of task
     */
    public void printMarkMessage(Task task, boolean isDone) {
        System.out.println();
        if (isDone) {
            System.out.println("Great Job! I've helped mark this task as done:\n" + task.toString());
        } else {
            System.out.println("No worries! I will help you unmark this task:\n" + task.toString());
        }
    }

    /**
     * Prints a message to standard output to notify the user when a task has
     * been successfully deleted.
     *
     * @param task Deleted task
     */
    public void printDeleteMessage(Task task) {
        System.out.println("Foosh! Let it be gone! I've helped delete the task:\n"
                + task.toString());
    }

    /**
     * Prints a message to standard output notifying the user the required task
     * has been added to current list of tasks.
     *
     * @param task Added task
     */
    public void printAddMessage(Task task) {
        String displayMessage = "I gotchu. New task added to the list:\n";
        System.out.println(displayMessage + task.toString());
    }

    /**
     * Prints a list of filtered tasks after find command is executed.
     *
     * @param taskList List of filtered tasks
     * @throws BobInvalidTaskNumberException If an error occurs when trying to access a task
     */
    public void printFindMessage(TaskList taskList) throws BobInvalidTaskNumberException {
        String displayMessage = "These are the matching tasks in your list:\n";
        System.out.println(displayMessage);
        printRawList(taskList);
    }

    private void printRawList(TaskList taskList) throws BobInvalidTaskNumberException {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i).toString());
        }
    }

    /**
     * Reads the user input in the Command Line Interface as a whole line.
     *
     * @return A String representing user input
     */
    public String readTextInput() {
        return scanner.nextLine();
    }
}
