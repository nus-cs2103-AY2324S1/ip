package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.task.UpdateType;

/**
 * Represents a command to update the tasks in the task list.
 */
public class UpdateCommand extends Command {

    /**
     * The index in the task list to update.
     */
    private int index;

    /**
     * The part of the task to be updated.
     */
    private UpdateType updateType;

    /**
     * The new value to update to (as a String).
     */
    private String newValue;

    /**
     * Initialises an UpdateCommand object.
     *
     * @param index The index in the task list to update.
     * @param updateType The portion of the task to update.
     * @param newValue The new value to update to (as a String).
     */
    public UpdateCommand(int index, UpdateType updateType, String newValue) {
        this.index = index;
        this.updateType = updateType;
        this.newValue = newValue;
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

            tasks.updateTask(index, updateType, newValue);
            responseBuilder.append("OK, I've updated this task with the following details:\n");
            responseBuilder.append("\u2022 " + tasks.getTaskString(index) + "\n");
            storage.saveTasks(tasks);

            return responseBuilder.toString();
        } else {
            throw new DukeException("Cannot update a task that is out of range!");
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
