package bongo.command;
import bongo.task.TaskList;
import bongo.helper.BongoException;
import bongo.helper.Ui;
import bongo.helper.Storage;
import bongo.task.Task;

public class DeleteCommand extends Command {
    int taskIndex;

    /**
     * A constructor for a DeleteCommand, with a taskIndex.
     * @param taskIndex
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BongoException {
        if (tasks.getTotalTasks() == 0) throw new BongoException("There are currently no tasks.");
        if (taskIndex < 0 || taskIndex >= tasks.getTotalTasks()) throw new BongoException("Task does not exist.");
        Task taskToBeDeleted = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        ui.showDeleteTask(taskToBeDeleted, tasks.getTotalTasks());
        storage.edit(Storage.FileAction.DELETE_TASK, this.taskIndex + 1);
    }
}
