package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command to mark tasks as not completed in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Initialises an UnmarkCommand object.
     *
     * @param index The index in the task list to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the given UnmarkCommand using the specified TaskList, Ui and Storage.
     *
     * @param tasks The task list to unmark a task in.
     * @param storage The storage to save and update tasks.
     * @throws DukeException If index is out of range for the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index >= 1 && index <= tasks.getSize()) {
            StringBuilder responseBuilder = new StringBuilder();

            tasks.unmarkAsDone(index);
            responseBuilder.append("OK, I've marked this task as not done yet:\n");
            responseBuilder.append("\u2022 " + tasks.getTaskString(index) + "\n");
            storage.saveTasks(tasks);

            return responseBuilder.toString();
        } else {
            throw new DukeException("Cannot unmark a task that is out of range!");
        }
    }

    /**
     * Gets the command type for the UnmarkCommand.
     *
     * @return Unmark.
     */
    @Override
    public String getCommandType() {
        return "Unmark";
    }
}
