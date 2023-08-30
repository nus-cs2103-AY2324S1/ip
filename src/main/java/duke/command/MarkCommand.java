package duke.command;

import duke.*;
import duke.task.Task;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException(Ui.LINE + Messages.INVALID_INDEX_MESSAGE + Ui.LINE);
        }

        Task currentTask = tasks.markAsDone(index);
        ui.showTaskDone(currentTask);
        storage.saveTasks(tasks);
    }
}
