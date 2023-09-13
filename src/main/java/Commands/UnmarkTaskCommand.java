package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Duke.DukeException;
public class UnmarkTaskCommand implements Command {
    /** The index of the task to be marked as not done within the TaskList.*/
    private int id;

    /**
     * Constructs a command that marks a task as not done when executed.
     * @param id The index of the task to be marked as not done.
     */
    public UnmarkTaskCommand(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     * Executes the command to mark the task with the encapsulated id as not done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmarkTask(id);
            assert !tasks.getTask(id).isDone();
            return ui.printTaskUnmarkedMessage(tasks.getTask(id));
        } catch (RuntimeException e) {
            throw new DukeException("\tIndex out of bounds. There are "
                                        + tasks.getSize()
                                            + " tasks currently.");
        }
    }
}
