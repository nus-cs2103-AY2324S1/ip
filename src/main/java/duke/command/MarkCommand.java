package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark or unMark a task list.
 */
public class MarkCommand extends Command {
    private final String type;
    private final String pos;

    /**
     * Constructs a MarkCommand object.
     *
     * @param type Indicates whether this is a mark or unMark command.
     * @param pos  The position of the task to be marked in the list.
     */
    public MarkCommand(String type, String pos) {
        assert type != null;
        assert pos != null;
        this.type = type;
        this.pos = pos;
    }

    /**
     * Executes the mark command, marking or unMarking the task and displaying relevant messages.
     *
     * @param list    The TaskList object managing the list of tasks.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to manage data storage.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task markedTask = list.markTask(this.type, this.pos);
        return ui.showMark(this.type, markedTask);
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return False, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
