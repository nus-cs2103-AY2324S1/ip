package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit application.
 */
public class ClearCommand extends Command {

    /**
     * Creates ExitCommand object.
     */
    public ClearCommand() {
    }

    /**
     * Executes exit command. Exits application.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        tasks.clear();
        return ui.showClearedMessage();
    }

}
