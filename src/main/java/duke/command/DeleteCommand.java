package duke.command;

import duke.*;
import duke.task.Task;

/**
 * Represents the command of removing a task from the list.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task removed = tasks.remove(index);
        ui.showTaskDeleted(removed, tasks.size());
        storage.saveTasks(tasks);
    }
}
