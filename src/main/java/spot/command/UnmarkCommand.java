package spot.command;

import spot.exception.SpotException;
import spot.Storage;
import spot.TaskList;
import spot.Ui;

public class UnmarkCommand extends Command {

    private int position;

    /**
     * Constructs a new UnmarkCommand object.
     *
     * @param position Position of the task to be marked as not done.
     */
    public UnmarkCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the UnmarkCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.unmarkTask(position);
    }

    /**
     * Checks if the UnmarkCommand is an ExitCommand.
     *
     * @return Boolean representing whether the UnmarkCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
