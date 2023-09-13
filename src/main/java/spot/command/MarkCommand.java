package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

/**
 * Represents a command to mark a certain task as done.
 */
public class MarkCommand extends Command {

    private int position;

    /**
     * Constructs a new MarkCommand object.
     *
     * @param position Position of the task to be marked as done.
     */
    public MarkCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the MarkCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.markTask(ui, position);
    }

    /**
     * Checks if the MarkCommand is an ExitCommand.
     *
     * @return Boolean representing whether the MarkCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
