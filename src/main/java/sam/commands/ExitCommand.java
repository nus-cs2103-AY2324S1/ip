package sam.commands;

import sam.constants.Message;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;

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
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {

        ui.shutDown();
        return new CommandResult(Message.BYE);
    }
}
