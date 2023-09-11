package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;

/**
 * Represents a command to sort tasks.
 */
public class SortCommand extends Command {
    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param storage The storage
     * @return The output string
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list to sort!";
        }
        tasks.sortTasks();
        return "Your tasks have been sorted alphabetically!";
    }
}
