package duke.command;

import duke.task.TaskStorage;

/**
 * The ListCommand class encapsulates the list command.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskStorage taskStorage) {
        return taskStorage.toString();
    }
}
