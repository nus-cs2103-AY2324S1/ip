package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete tasks from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Initialises a DeleteCommand object.
     *
     * @param index The index in the task list to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the given DeleteCommand using the specified TaskList, Ui and Storage.
     *
     * @param tasks The task list to delete a task on.
     * @param storage The storage to save and update tasks.
     * @throws DukeException If index is out of range for the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index >= 1 && index <= tasks.getSize()) {
            Task deletedTask = tasks.remove(index);
            storage.saveTasks(tasks);

            return "Noted. I've removed this task:\n"
                    + "\u2022 " + deletedTask.toString() + "\n"
                    + "Now you have " + tasks.getSize() + " tasks in the list.";
        } else {
            throw new DukeException("Cannot delete a task that is out of range!");
        }
    }

    /**
     * Gets the command type for the DeleteCommand.
     *
     * @return Delete.
     */
    @Override
    public String getCommandType() {
        return "Delete";
    }
}
