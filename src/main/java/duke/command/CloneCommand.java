package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.task.UpdateType;

/**
 * Represents a command to clone tasks in the task list.
 */
public class CloneCommand extends Command {

    /**
     * The index in the task list to update.
     */
    private int index;

    /**
     * Initialises an UpdateCommand object.
     *
     * @param index The index in the task list to clone.
     */
    public CloneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the given UpdateCommand using the specified TaskList and Storage.
     *
     * @param tasks The task list to update a task in.
     * @param storage The storage to save and update tasks.
     * @return The String that will be passed to the GUI when the UpdateCommand has finished executing.
     * @throws DukeException If index is out of range for the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index >= 1 && index <= tasks.getSize()) {
            StringBuilder responseBuilder = new StringBuilder();

            tasks.cloneTask(index);
            responseBuilder.append("OK, I've cloned this task:\n");
            responseBuilder.append("\u2022 " + tasks.getTaskString(index) + "\n");
            storage.saveTasks(tasks);

            return responseBuilder.toString();
        } else {
            throw new DukeException("Cannot clone a task that is out of range!");
        }
    }

    /**
     * Gets the command type for the UpdateCommand.
     *
     * @return Update.
     */
    @Override
    public String getCommandType() {
        return "Update";
    }
}
