package duke.command;

import duke.exception.DukeException;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to find tasks in the task list. A <code>FindCommand</code> object
 * corresponds to an executable find command. Upon execution, it will find tasks in the task list
 * that contain the task keyword specified by the user.
 */
public class FindCommand extends Command {

    private final String commandBody;

    /**
     * Constructs a <code>FindCommand</code> object.
     *
     * @param commandBody the command body
     */
    public FindCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
    }

    /**
     * Executes the command to find tasks in the task list.
     *
     * @param taskList the task list to find tasks in
     * @param ui       the user interface to print messages to the user
     * @param storage  the storage
     * @throws DukeException if no tasks are found
     */
    @Override
    public Response execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null : "taskList should not be null";
        assert ui != null : "ui should not be null";

        return taskList.findTask(commandBody, ui);
    }
}
