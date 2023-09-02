package duke.command;

import duke.main.Storage;
import duke.task.TaskList;

/**
 * An abstract class for command based on user input.
 */
public abstract class Command {

    /**
     * Runs the actions of the command based on user input.
     * @param taskList The list of tasks under the user.
     * @param storage The storage responsible for saving and load the taskList
     *                into a file.
     */
    public abstract void execute(TaskList taskList, Storage storage);

    /**
     * Prints the actions taken by the command based on user input.
     * @param taskList The list of tasks under the user.
     */
    public abstract void printCommand(TaskList taskList);

    /**
     * Returns true or false based on whether the command will cause the program
     * to terminate and stop accepting user input.
     */
    public abstract boolean isContinue();
}
