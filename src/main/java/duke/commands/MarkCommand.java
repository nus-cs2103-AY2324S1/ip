package duke.commands;

import java.io.IOException;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * The MarkCommand marks a task as completed, update the .txt file
 * and display a task marked message when it is executed.
 */
public class MarkCommand extends Command {

    /** ID of task to be marked. */
    private final int taskID;

    /**
     * Constructor to initialize MarkCommand.
     *
     * @param taskID ID of task to be marked.
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
