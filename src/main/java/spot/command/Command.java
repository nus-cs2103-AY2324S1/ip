package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public abstract class Command {

    /**
     * Executes the Command.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException;

    /**
     * Checks if the Command is an ExitCommand.
     *
     * @return Boolean representing whether the Command is an ExitCommand.
     */
    public abstract boolean isExit();

}
