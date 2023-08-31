package eva.command;

import eva.Ui;
import eva.task.TaskList;
import eva.Storage;
import eva.DukeException;

public class UnmarkCommand extends Command {
    private int taskIndexToUnmark;

    public UnmarkCommand(int taskIndexToMark) {
        this.taskIndexToUnmark = taskIndexToMark;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsUndone(taskIndexToUnmark);
        ui.showUnmarked(tasks.getTask(taskIndexToUnmark));
        storage.saveTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}