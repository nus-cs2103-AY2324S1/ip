package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.parser.Validate;
import duke.task.TaskList;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified parameters.
     *
     * @param params The parameters associated with the command.
     * @throws DukeException If there's an issue validating or retrieving the parameter.
     */
    public MarkCommand(Params params) throws DukeException {
        super("mark <index> | m <index>");
        this.index = Validate.validateNumber(params.getArgumentIfSet("index", usageText));
    }

    /**
     * Executes the mark command, marking the specified task as done and updating the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws DukeException If there's an error marking the task or saving the task list.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showMessage(String.format("Nice! I've marked this task as done:\n\t%s\n", tasks.markTask(index)));
        storage.save(tasks);
    }
}
