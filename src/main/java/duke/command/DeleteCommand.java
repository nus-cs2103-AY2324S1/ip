package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
/**
 * Deletes a task from the list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a command containing the tasknumber in list to be deleted.
     * @param taskNumber The position of task in the list to be deleted
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command.
     * @param taskList list of tasks
     * @param ui ui component of the program
     * @param storage storage component of the program
     * @throws DukeException Errors that occur when trying to delete the task
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(taskNumber, ui);
        storage.rewriteFile(taskList);
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
