package duke.commands;

import java.io.IOException;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * The MarkCommand class represents a command to mark a task as completed,
 * update the task list in memory and the .txt file, and
 * display a task marked message when executed.
 */
public class MarkCommand extends Command {

    /** The ID of the task to be marked as completed. */
    private final int taskID;

    /**
     * Constructs a new MarkCommand with the specified task ID.
     *
     * @param taskID         The ID of the task to be marked as completed.
     * @throws DukeException If the provided task ID is invalid (less than 1).
     */
    public MarkCommand(int taskID) throws DukeException {
        if (taskID < 1) {
            throw new DukeException("☹ OOPS!!! Invalid Task ID.");
        }
        this.taskID = taskID;
    }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws DukeException, IOException {
        taskList.markTask(taskList.getTask(taskID - 1));
        Storage.save(taskList);
        return message.showMarked(taskList.getTask(taskID - 1));
    }
}
