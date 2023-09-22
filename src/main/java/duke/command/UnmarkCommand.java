package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
/**
 * Command that marks the given task as incomplete.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructor for an unmark command.
     * @param taskToMark The position of task in the list to be deleted
     */
    public UnmarkCommand(int taskToMark) {
        super();
        this.taskNumber = taskToMark;
    }

    /**
     * Executes the current unmark command.
     * @param taskList current list of tasks
     * @param ui instance of user interface
     * @param storage instance of storage to read and write files
     * @throws DukeException Exception thrown whilst executing the command
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String s = taskList.unmarkTask(taskNumber, ui);
        storage.rewriteFile(taskList);
        return s;
    }

    /**
     * Checks if the current task is an exit task.
     * @return false since task is not an exit task
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
