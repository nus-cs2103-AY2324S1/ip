package duke.parse.command.update;

import duke.Duke;
import duke.parse.command.Command;

/**
 * Represents an update command.
 */
public abstract class UpdateCommand implements Command {
    protected final int index;

    /**
     * Instantiates a new update command.
     * @param type the type of the update.
     * @param index the index of the task in the bot's task list.
     */
    public UpdateCommand(int index) {
        this.index = index;
    }
}
