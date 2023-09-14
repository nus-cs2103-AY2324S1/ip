package command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.UI;

/**
 * A command to unmark a task.
 */
public class UnmarkCommand extends Command {
    /**
     * Command to unmark a task.
     */
    public static final String COMMAND_UNMARK = "unmark";

    /**
     * Constructor for the UnmarkCommand class.
     *
     * @param params Parsed user input.
     */
    public UnmarkCommand(ArrayList<String> params) throws InvalidCommandException {
        super(params);
        if (params.size() != 2) {
            throw new InvalidCommandException("Add todo command format is wrong");
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks   List of tasks.
     * @param ui      UI of the application.
     * @param storage Object to handle data storage.
     * @return Message to be shown to the user.
     * @throws DukeException If error encountered when saving data.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(params.get(1)) - 1;
        Task task = tasks.unmark(index);
        tasks.saveState(storage);
        return ui.getTaskUnmarkedMessage(task);
    }

    /**
     * Returns a boolean representing whether the command requires the application to exit.
     *
     * @return Boolean representing whether the command exits the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
