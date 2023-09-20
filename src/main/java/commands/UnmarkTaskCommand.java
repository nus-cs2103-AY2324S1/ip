package commands;
import duke.DukeException;
import oop.Storage;
import oop.TaskList;
import oop.Ui;

/**
 * The command which unmarks a task when executed.
 */
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
        if (id >= tasks.getSize()) {
            throw new DukeException("Index out of bounds. There are "
                    + tasks.getSize()
                    + " tasks currently.");
        }
        tasks.unmarkTask(id);
        assert !tasks.getTask(id).isDone();
        return ui.getTaskUnmarkedMessage(tasks.getTask(id));
    }
}
