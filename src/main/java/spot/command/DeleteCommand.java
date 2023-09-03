package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {

    private int position;

    /**
     * Constructs a new DeleteCommand object.
     *
     * @param position Position of the task to be deleted.
     */
    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the DeleteCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.deleteTask(ui, position);
    }

    /**
     * Checks if the DeleteCommand is an ExitCommand.
     *
     * @return Boolean representing whether the DeleteCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
