package duke.commands;

import java.io.IOException;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * The UnmarkCommand class represents a command to unmark a
 * task as completed, update the task list in memory and the
 * .txt file, and display a task unmarked message when executed.
 */
public class UnmarkCommand extends Command {

    /** The ID of the task to be unmarked as incomplete. */
    private final int taskID;

    /**
     * Constructs a new UnmarkCommand with the specified task ID.
     *
     * @param taskID         The ID of the task to be unmarked as incomplete.
     * @throws DukeException If the provided task ID is invalid (less than 1).
     */
    public UnmarkCommand(int taskID) throws DukeException {
        if (taskID < 1) {
            throw new DukeException("☹ OOPS!!! Invalid Task ID.");
        }
        this.taskID = taskID;
    }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws DukeException, IOException {
        taskList.unmarkTask(taskList.getTask(taskID - 1));
        Storage.save(taskList);
        return message.showUnmarked(taskList.getTask(taskID - 1));
    }
}
