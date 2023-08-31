package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command to find tasks in the task list. A <code>FindCommand</code> object
 * corresponds to an executable find command. Upon execution, it will find tasks in the task list
 * that contain the task keyword specified by the user.
 */
public class FindCommand extends Command {

    private String commandBody;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.findTask(commandBody, ui);
    }
}
