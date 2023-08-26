package didier;

import didier.exception.DidierException;
import didier.exception.TaskNumberException;
import didier.task.Task;

import java.util.Scanner;

/**
 * Deals with all interaction between the bot and the user.
 * A UI object corresponds to a user interface with the bot.
 */
public class UI {

    private Scanner scanner;

    /**
     * Constructor for the UI object.
     * Initialises the Scanner used to receive user input.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the message to the standard output, formatted in a specific way emulate the bot.
     *
     * @param message The message to be printed.
     */
    public void botPrintMessage(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints a horizontal line break to the standard output.
     */
    public void botPrintBr() {
        String lineBreak = "-----------------------------------------------------------------------------";
        botPrintMessage(lineBreak);
    }

    /**
     * Prints a message to the user informing them that their task has been either marked done
     * or marked undone.
     *
     * @param task The task that has been marked or unmarked.
     * @param isDone Whether the task is done or undone.
     */
    public void botPrintTaskMarkedDone(Task task, boolean isDone) {
        if (isDone) {
            botPrintMessage("Okay! I've marked the following task as done:");
        } else {
            botPrintMessage("Okay! I've marked the following task as undone:");
        }
        botPrintMessage(task.toString());
    }

    /**
     * Prints a message to the user informing them of the current tasks in their list.
     *
     * @param taskList The list of tasks in their list.
     * @throws TaskNumberException If the task list is accessed at an invalid task index.
     */
    public void botPrintTaskList(TaskList taskList) throws TaskNumberException {
        botPrintMessage("The tasks in your list are as follows:");
        for (int i = 1; i <= taskList.getSize(); i++) {
            botPrintMessage(String.format("%d.%s", i, taskList.getTask(i)));
        }
    }

    /**
     * Prints a message to the user informing them that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param size The remaining size of the task list.
     */
    public void botPrintTaskRemoved(Task task, int size) {
        botPrintMessage("Okay! I've removed this task:");
        botPrintMessage(task.toString());
        botPrintMessage(String.format("There are now %d tasks in your list", size));
    }

    /**
     * Prints a message greeting the user at the start of the user-bot interaction.
     */
    public void botGreet() {
        botPrintBr();
        botPrintMessage("Greetings user, I'm didier.Didier. How can I help you?");
        botPrintBr();
    }

    /**
     * Prints a message informing the user that a task has been added to the list.
     *
     * @param task The task that has been added.
     * @param size The new size of the task list.
     */
    public void botPrintTaskAdded(Task task, int size) {
        botPrintMessage("Okay! I've added your task:");
        botPrintMessage(task.toString());
        botPrintMessage(String.format("There are now %d tasks in your list", size));
    }

    /**
     * Prints a message that indicates the end of a command executed by the bot.
     */
    public void botEndCommand() {
        botPrintBr();
    }

    /**
     * Prints a message informing the user the details of the error that occured during
     * their last interaction with the bot.
     *
     * @param exception The exception encountered by the bot.
     */
    public void botPrintError(DidierException exception) {
        botPrintMessage(exception.getMessage() + "Please try again.");
    }

    /**
     * Prints a message bidding the user farewell before the bot leaves and the program exits.
     */
    public void botGoodBye() {
        botPrintMessage("Goodbye! If you need more help in the future don't hesitate to ask me.");
        botPrintBr();
        this.scanner.close();
    }

    /**
     * Returns the String at the next line of the scanner.
     * @return next scanner line String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
