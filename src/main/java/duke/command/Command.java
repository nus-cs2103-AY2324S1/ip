package duke.command;

import duke.main.Storage;
import duke.task.Task;
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
     * @param commandList The list of previous commands by user
     * @param write To control whether to write the lists into the files or not
     */
    public abstract String execute(TaskList taskList, Storage storage, CommandList commandList, boolean write);

    /**
     * Prints the actions taken by the command based on user input.
     * @param taskList The list of tasks under the user.
     */
    public abstract String printCommand(TaskList taskList);

    /**
     * Returns the task of the command if relevant
     */
    public Task getTask() {
        return null;
    }
}
