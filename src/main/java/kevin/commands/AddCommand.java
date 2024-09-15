package kevin.commands;

import kevin.exceptions.StorageException;
import kevin.storage.Storage;
import kevin.task.Task;
import kevin.task.TaskList;
import kevin.ui.Ui;

/**
 * Represents an add command in the Duke application.
 */
public class AddCommand extends Command {

    private final Task task;


    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException {
        tasks.add(task);
        ui.showAddTask(tasks, task);
        storage.save(tasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws StorageException {
        tasks.add(task);
        String output = ui.showAddTaskGui(tasks, task);
        storage.save(tasks);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
