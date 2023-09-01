package command;

import duke.*;

import java.util.ArrayList;

public class UnmarkCommand extends Command{
    /**
     * Command to unmark a task.
     */
    public static final String COMMAND_UNMARK = "unmark";

    /**
     * Constructor for the UnmarkCommand class.
     *
     * @param params Parsed user input.
     */
    public UnmarkCommand(ArrayList<String> params) {
        super(params);
    }

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui UI of the application.
     * @param storage Object to handle data storage.
     * @throws DukeException If error encountered when saving data.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(params.get(1)) - 1;
        Task task = tasks.unmark(index);
        ui.printTaskUnmarkedMessage(task);
        tasks.saveState(storage);
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
