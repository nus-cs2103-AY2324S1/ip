package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find a task from the task list in the Duke application.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs an FindCommand object with the specified keyword to be searched.
     *
     * @param keyword The keyword to be searched in the description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the "find" command, adding the stored task to the task list and updating storage.
     *
     * @param tasks   The task list that the command may operate on.
     * @param ui      The user interface to interact with the user or display messages.
     * @param storage The storage handler to manage data persistence.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.findTask(keyword, ui);
    }
}
