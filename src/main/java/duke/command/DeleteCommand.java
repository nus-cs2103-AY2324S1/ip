package duke.command;

import duke.task.TaskStorage;

/**
 * The DeleteCommand class encapsulates the information to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskStorage taskStorage) {
        return taskStorage.delete(index);
    }
}
