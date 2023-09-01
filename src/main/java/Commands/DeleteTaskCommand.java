package Commands;
import Duke.DukeException;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Task;
public class DeleteTaskCommand implements Command {
    /** The index of the task to be deleted upon execution of this command.*/
    int id;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.deleteTask(id);
            ui.printTaskDeletedMessage(removedTask);
        } catch (RuntimeException e) {
            throw new DukeException("\tIndex out of bounds. There are " + tasks.getSize() + " tasks currently.");
        }
    }
}
