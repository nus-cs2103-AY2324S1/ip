package sillybot.commands;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.tasks.TaskList;

/**
 * Represents a ByeCommand object that handles the bye command.
 */
public class ByeCommand extends Command {
    /**
     * Creates a ByeCommand object.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Executes the ByeCommand object.
     *
     * @return The String representation of the ByeCommand object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }
}
