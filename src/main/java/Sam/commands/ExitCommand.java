package sam.commands;

import sam.services.Storage;
import sam.services.TaskList;
import sam.services.UI;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.shutDown();
    }
}
