package duke.commands;

import java.io.IOException;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.Message;

/**
 * The UnmarkCommand unmark a task as completed, update the .txt file
 * and display a task unmarked message when it is executed.
 */
public class UnmarkCommand extends Command {

    /** ID of task to be unmarked. */
    private final int taskID;

    /**
     * Constructor to initialize UnmarkCommand.
     *
     * @param taskID ID of task to be unmarked.
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
