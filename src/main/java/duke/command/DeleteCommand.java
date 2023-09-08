package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.parser.Validate;
import duke.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified parameters.
     *
     * @param params The parameters associated with the command.
     * @throws DukeException If there's an issue validating or retrieving the parameter.
     */
    public DeleteCommand(Params params) throws DukeException {
        super("delete <index> | rm <index>");
        this.index = Validate.validateNumber(params.getArgumentIfSet("index", usageText));
    }

    /**
     * Executes the delete command, removing the specified task from the task list. Notifies the user interface and
     * storage handler about the deletion.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws DukeException If there's an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showMessage(String.format("Noted. I've removed this task:\n\t%s\n", tasks.deleteTask(this.index)));
        ui.showMessage(tasks.status());
        storage.save(tasks);
    }
}
