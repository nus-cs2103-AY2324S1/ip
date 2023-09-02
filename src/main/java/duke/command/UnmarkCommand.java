package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
/**
 * Command that marks the given task as incomplete.
 */
public class UnmarkCommand extends Command{
    private final int TASK_NUMBER;

    /**
     * Constructor for an unmark command.
     * @param taskToMark The position of task in the list to be deleted.
     */
    public UnmarkCommand(int taskToMark){
        super();
        this.TASK_NUMBER = taskToMark;
    }

    /**
     * Executes the current unmark command.
     * @param tasklst current list of tasks
     * @param ui instance of user interface
     * @param storage instance of storage to read and write files
     * @throws DukeException Exception thrown whilst executing the command
     */
    @Override
    public void execute(TaskList tasklst, Ui ui, Storage storage) throws DukeException {
        tasklst.unmarkTask(TASK_NUMBER, ui);
        storage.rewriteFile(tasklst);
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
