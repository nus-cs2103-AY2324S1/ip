package sam.commands;

import sam.services.Storage;
import sam.services.TaskList;
import sam.services.UI;

/**
 * Shows help instructions.
 * Based on AddressBook2
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printMessage(
                "List of possible commands: \n"
                        + AddTodoCommand.MESSAGE_USAGE
                        + "\n\n" + AddEventCommand.MESSAGE_USAGE
                        + "\n\n" + AddDeadlineCommand.MESSAGE_USAGE
                        + "\n\n" + DeleteTaskCommand.MESSAGE_USAGE
                        + "\n\n" + MarkTaskCommand.MESSAGE_USAGE
                        + "\n\n" + UnmarkTaskCommand.MESSAGE_USAGE
                        + "\n\n" + ListCommand.MESSAGE_USAGE
                        + "\n\n" + HelpCommand.MESSAGE_USAGE
                        + "\n\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
