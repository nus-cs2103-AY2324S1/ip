package command;

import duke.*;

import java.util.ArrayList;

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
    public DeleteCommand(ArrayList<String> params) {
        super(params);
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
