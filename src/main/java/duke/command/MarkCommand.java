package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command to mark tasks as completed in the task list.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Initialises a MarkCommand object.
     *
     * @param index The index in the task list to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the given MarkCommand using the specified TaskList and Storage.
     *
     * @param tasks The task list to mark a task in.
     * @param storage The storage to save and update tasks.
     * @return The String that will be passed to the GUI when the MarkCommand has finished executing.
     * @throws DukeException If index is out of range for the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index >= 1 && index <= tasks.getSize()) {
            StringBuilder responseBuilder = new StringBuilder();

            tasks.markAsDone(index);
            responseBuilder.append("Nice! I've marked this task as done:\n");
            responseBuilder.append("\u2022 " + tasks.getTaskString(index) + "\n");
            storage.saveTasks(tasks);

            return responseBuilder.toString();
        } else {
            throw new DukeException("Cannot mark a task that is out of range!");
        }
    }

    /**
     * Gets the command type for the MarkCommand.
     *
     * @return Mark.
     */
    @Override
    public String getCommandType() {
        return "Mark";
    }
}
