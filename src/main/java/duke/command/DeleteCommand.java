package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.Storage;
import duke.DukeException;


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
