package bert.commands;

import bert.storage.Storage;
import bert.tasks.TaskList;

/**
 * Represent a command that can be executed to produce effects on a task list and storage.
 */
public abstract class Command {
    /**
     * Executes the command on a task list and storage.
     *
     * @param tasks A TaskList instance for the command to execute on.
     * @param storage A Storage instance for the command to execute on.
     * @return A CommandResult instance containing user feedback on the results of the command execution.
     */
    public abstract CommandResult execute(TaskList tasks, Storage storage);
}
