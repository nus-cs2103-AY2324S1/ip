package duke.command;

import duke.task.TaskStorage;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskStorage taskStorage) {
        return taskStorage.markAsDone(index);
    }
}
