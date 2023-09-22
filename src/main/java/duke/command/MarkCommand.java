package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
/**
 * Command that marks the given task as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;
    /**
     * Constructs a command containing the tasknumber in list to be deleted.
     * @param taskNumber The position of task in the list to be deleted.\
     */
    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the mark command.
     * @param taskList list of tasks
     * @param ui ui component of the program
     * @param storage storage componenet of the program
     * @throws DukeException Error if the task number is outside the task list
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String s = taskList.markTask(taskNumber, ui);
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
