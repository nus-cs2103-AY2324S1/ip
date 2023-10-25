package duke.command;

import duke.exception.DukeException;
import duke.exception.ManipulateException;
import duke.util.Keyword;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to delete a task. A <code>DeleteCommand</code>
 * object corresponds to an executable command to delete a task.
 */
public class DeleteCommand extends Command {

    private final String commandBody;

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
     * @param ui       the user interface to print messages to the user
     * @param storage  the storage to save the task list to
     * @throws DukeException if the command is invalid
     */
    @Override
    public Response execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null : "taskList should not be null";
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";

        String err = "OOPS!!! The command for delete task is invalid.";
        int taskNum;
        try {
            if (!commandBody.equals("all")) {
                taskNum = Integer.parseInt(commandBody);
            } else {
                taskList.manipulateAllTask(Keyword.DELETE);
                storage.saveTasks(taskList.saveTaskList());
                return ui.showManipulateAllTask(Keyword.DELETE.getKeyword());
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, Keyword.DELETE.getKeyword());
        }
        Response respond = taskList.deleteTask(taskNum - 1, ui);
        storage.saveTasks(taskList.saveTaskList());
        return respond;
    }
}
