package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.JarvisException;
import jarvis.exception.JarvisInvalidIndexException;

public class DeleteCommand extends Command {
    private int taskIndexToDelete;

    public DeleteCommand(int taskIndexToDelete) {
        this.taskIndexToDelete = taskIndexToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException {
        if (taskIndexToDelete <= 0 || taskIndexToDelete > tasks.size()) {
            throw new JarvisInvalidIndexException(taskIndexToDelete);
        }

        Task deletedTask = tasks.get(taskIndexToDelete - 1); // Lists are 0-indexed, but users see a 1-indexed list.
        tasks.remove(taskIndexToDelete - 1);

        ui.displayDeletedTask(deletedTask, tasks);

        storage.saveTasks(tasks);
    }
}
