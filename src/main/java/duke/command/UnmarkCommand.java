package duke.command;

import duke.*;
import duke.task.Task;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task currentTask = tasks.markAsUndone(index);
        ui.showTaskUndone(currentTask);
        storage.saveTasks(tasks);
    }
}
