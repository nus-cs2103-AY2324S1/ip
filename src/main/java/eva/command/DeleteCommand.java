package eva.command;

import eva.Ui;
import eva.task.TaskList;
import eva.task.Task;
import eva.Storage;
import eva.DukeException;


public class DeleteCommand extends Command {
    private int taskIndexToDelete;

    public DeleteCommand(int taskIndexToDelete) {
        this.taskIndexToDelete = taskIndexToDelete;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.getTask(taskIndexToDelete);
        tasks.deleteTask(taskIndexToDelete);
        ui.showDeleted(deletedTask, tasks.listSize());
        storage.saveTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
