package duke.command;

import duke.exception.DukeException;
import duke.exception.ManipulateException;
import duke.util.Keyword;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to mark or unmark a task.
 * A <code>MarkCommand</code> object corresponds to an executable command
 * to mark or unmark a task.
 */
public class MarkCommand extends Command {

    private final Keyword key;
    private final String commandBody;

    /**
     * Constructs a <code>MarkCommand</code> object.
     *
     * @param key the operation to be done on the task
     * @param commandBody the body of the command
     */
    public MarkCommand(Keyword key, String commandBody) {
        super(false);
        this.key = key;
        this.commandBody = commandBody;
    }

    /**
     * Executes the command to mark or unmark a task.
     *
     * @param taskList the task list to be manipulated
     * @param ui       the ui to interact with the user
     * @param storage  the storage to save the task list
     * @throws DukeException if the command is invalid
     */
    @Override
    public Response execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null : "taskList should not be null";
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";

        String err = String.format("OOPS!!! The command for %s task is invalid.", key.getKeyword());
        int taskNum;
        try {
            if (!commandBody.equals("all")) {
                taskNum = Integer.parseInt(commandBody);
            } else {
                taskList.manipulateAllTask(key);
                storage.saveTasks(taskList.saveTaskList());
                return ui.showManipulateAllTask(key.getKeyword());
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, key.getKeyword());
        }
        Response respond = taskList.markTask(taskNum - 1, key, ui);
        storage.saveTasks(taskList.saveTaskList());
        return respond;
    }
}
