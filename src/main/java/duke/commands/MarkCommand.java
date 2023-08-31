package duke.commands;

import java.io.IOException;

import duke.data.exception.DukeException;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        taskList.markTask(taskList.getTask(taskID - 1));
        ui.showMarked(taskList.getTask(taskID - 1));
        Storage.save(taskList);
    }
}
