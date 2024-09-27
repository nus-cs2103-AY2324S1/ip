package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        storage.saveTasks(tasks);
        ui.setGoodbye();
    }

    /**
     * Checks if the ExitCommand is an ExitCommand.
     *
     * @return Boolean representing whether the ExitCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
