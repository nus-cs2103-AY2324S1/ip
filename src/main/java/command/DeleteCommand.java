package command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.UI;

/**
 * A command to delete a task from a list of tasks.
 */
public class DeleteCommand extends Command {
    /**
     * Command to delete a task.
     */
    public static final String COMMAND_DELETE = "delete";

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param params Parsed user input.
     */
    public DeleteCommand(ArrayList<String> params) throws InvalidCommandException {
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
     * @throws DukeException If index is out of range.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(params.get(1)) - 1;
        Task task = tasks.remove(index);
        ui.printTaskDeletedMessage(task, tasks.getTaskCount());
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
