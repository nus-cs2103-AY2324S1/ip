package duke.command;

import duke.task.TaskStorage;

/**
 * Unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskStorage taskStorage) {
        return taskStorage.unmarkAsDone(index);
    }
}
