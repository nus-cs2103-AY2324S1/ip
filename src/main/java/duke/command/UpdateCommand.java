package duke.command;

import duke.exception.InvalidIndexException;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to update a specific task in the task list.
 * <p>
 * This command allows updating various attributes of a task based on its index. The attributes
 * that can be updated include the task's description, start time/date ('from'), end time/date ('to'),
 * and the deadline ('by'). Only provided attributes will be updated, while the others remain unchanged.
 * </p>
 */
public class UpdateCommand extends Command {

    /**
     * Starting time/date of the event.
     */
    private final String from;

    /**
     * Ending time/date of the event.
     */
    private final String to;

    /**
     * Description of the event.
     */
    private final String description;

    /**
     * Due date of the deadline task.
     */
    private final String by;

    /**
     * Index of the task to be updated.
     */
    private final int index;

    /**
     * Constructs an UpdateCommand with specified parameters.
     *
     * @param index Index of the task in the task list.
     * @param description Updated description of the task. Null if unchanged.
     * @param from Updated starting time/date of the event. Null if unchanged.
     * @param to Updated ending time/date of the event. Null if unchanged.
     * @param by Updated due date of the deadline task. Null if unchanged.
     */
    public UpdateCommand(int index, String description, String from, String to, String by) {
        this.index = index;
        this.description = description;
        this.from = from;
        this.to = to;
        this.by = by;
    }

    /**
     * Executes the update command, updating the specified task attributes and saving the changes.
     *
     * @param tasks The list of tasks to be updated.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save tasks after updating.
     * @return A confirmation message indicating the success of the update operation.
     * @throws InvalidIndexException If the provided index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        String output = tasks.updateTask(index, description, from, to, by);
        storage.save(tasks.getTasks());
        return output;
    }

    /**
     * Indicates that this command doesn't terminate the application.
     *
     * @return {@code false} since this command doesn't cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
