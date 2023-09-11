package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param storage The storage
     * @return The output string
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
