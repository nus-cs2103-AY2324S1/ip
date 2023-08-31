package duke.command;

import duke.Keyword;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.ManipulateException;

/**
 * Represents a command to mark or unmark a task.
 * A <code>MarkCommand</code> object corresponds to an executable command
 * to mark or unmark a task.
 */
public class MarkCommand extends Command {

    private Keyword key;
    private String commandBody;

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
     * @param ui the ui to interact with the user
     * @param storage the storage to save the task list
     * @throws DukeException if the command is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String err = String.format("OOPS!!! The command for %s task is invalid.", key.getKeyword());
        int taskNum;
        try {
            if (!commandBody.equals("all")) {
                taskNum = Integer.parseInt(commandBody);
            } else {
                taskList.manipulateAllTask(key, ui);
                storage.changeFile(key, -1);
                return;
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, key.getKeyword());
        }
        taskList.markTask(taskNum - 1, key, ui);
        storage.changeFile(key, taskNum - 1);
    }
}
