package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Duke.DukeException;
public class MarkTaskCommand implements Command {
    /** The index of the task to be marked as done within the TaskList. */
    private int id;

    /**
     * Constructs a command that marks a task as done when executed.
     * @param id The index of the task to be marked as done.
     */
    public MarkTaskCommand(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     * Executes the command to mark the task with the encapsulated id as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(id);
            ui.printTaskMarkedMessage(tasks.getTask(id));
        } catch (RuntimeException e) {
            throw new DukeException("\tIndex out of bounds. There are " + tasks.getSize() + " tasks currently.");
        }
    }
}
