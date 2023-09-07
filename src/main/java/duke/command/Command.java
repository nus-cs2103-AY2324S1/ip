package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command given by the user.
 */
public abstract class Command {

    /**
     * Executes the command with the given tasks, and prints the necessary
     * results to the ui. If the list of tasks is modified, the storage file
     * is saved again.
     *
     * @param tasks The list of tasks in the application.
     * @param storage The storage file of the application.
     * @return The response by the bot.
     * @throws DukeException If there is any issue with the constructed command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
