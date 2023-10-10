package commands;
import duke.DukeException;
import oop.Storage;
import oop.TaskList;
import oop.Ui;
import tasks.Task;

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
        return ui.getTaskDeletedMessage(removedTask);
    }
}
