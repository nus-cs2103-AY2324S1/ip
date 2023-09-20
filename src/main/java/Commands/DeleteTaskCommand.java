package Commands;
import Duke.DukeException;
import OOP.Storage;
import OOP.TaskList;
import OOP.Ui;
import Tasks.Task;

/**
 * This command class encapsulates the data for deleting a task.
 */
public class DeleteTaskCommand implements Command {
    /** The index of the task to be deleted upon execution of this command.*/
    private int id;

    /**
     * Constructs a command that deletes the task with the specified index upon execution.
     * @param id The index of the task that is to be deleted.
     */
    public DeleteTaskCommand(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     * Executes the command to delete the task with the encapsulated id from the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (id >= tasks.getSize()) {
            throw new DukeException("Index out of bounds. There are "
                    + tasks.getSize()
                    + " tasks currently.");
        }
        Task removedTask = tasks.deleteTask(id);
//        assert !removedTask.equals(tasks.getTask(id));
        return ui.getTaskDeletedMessage(removedTask);
    }
}
