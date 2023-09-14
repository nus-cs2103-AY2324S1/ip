package duke.command;

import duke.task.TaskStorage;

/**
 * Represents a command to sort the task list.
 */
public class SortCommand extends Command {
    /**
     * Executes the command.
     */
    @Override
    public String execute(TaskStorage taskStorage) {
        taskStorage.sort();
        return "Sorted!";
    }
}
