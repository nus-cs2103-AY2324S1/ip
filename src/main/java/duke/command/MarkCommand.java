package duke.command;

import duke.exception.DukeException;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as completed in the Duke application.
 * Extends the base Command class.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.markTask(this.index);
        storage.write(taskList);
        return ui.showMarkMessage(task);
    }
}
