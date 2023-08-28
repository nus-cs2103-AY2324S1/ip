package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public class ListCommand extends Command {

    /**
     * Executes the ListCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(ui, tasks);
    }

    /**
     * Checks if the ListCommand is an ExitCommand.
     *
     * @return Boolean representing whether the ListCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
