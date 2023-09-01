package command;

import duke.*;

import java.util.ArrayList;

public class MarkCommand extends Command {
    /**
     * Command to mark a task.
     */
    public static final String COMMAND_MARK = "mark";

    /**
     * Constructor for the MarkCommand class.
     *
     * @param params Parsed user input.
     */
    public MarkCommand(ArrayList<String> params) throws InvalidCommandException {
        super(params);
        if (params.size() != 2) {
            throw new InvalidCommandException("Add todo command format is wrong");
        }
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
        Task task = tasks.mark(index);
        ui.printTaskMarkedMessage(task);
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
