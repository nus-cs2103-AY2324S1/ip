package duke.command;

import duke.Keyword;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.ManipulateException;

/**
 * Represents a command to delete a task. A <code>DeleteCommand</code>
 * object corresponds to an executable command to delete a task.
 */
public class DeleteCommand extends Command {

    private String commandBody;

    /**
     * Constructs a <code>DeleteCommand</code> object.
     *
     * @param commandBody the body of the command
     */
    public DeleteCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
    }

    /**
     * Executes the command to delete a task.
     *
     * @param taskList the task list to which the task is added
     * @param ui the user interface to print messages to the user
     * @param storage the storage to save the task list to
     * @throws DukeException if the command is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String err = "OOPS!!! The command for delete task is invalid.";
        int taskNum;
        try {
            if (!commandBody.equals("all")) {
                taskNum = Integer.parseInt(commandBody);
            } else {
                taskList.manipulateAllTask(Keyword.DELETE, ui);
                storage.changeFile(Keyword.DELETE, -1);
                return;
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, "delete");
        }
        taskList.deleteTask(taskNum - 1, ui);
        storage.changeFile(Keyword.DELETE, taskNum - 1);
    }
}
