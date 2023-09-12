package sam.commands;

import sam.constants.Message;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;

/**
 * Shows help instructions.
 * Based on AddressBook2
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String ALL_COMMAND_USAGES = "List of possible commands: \n"
                                                        + AddToDoCommand.MESSAGE_USAGE
                                                        + "\n\n" + AddEventCommand.MESSAGE_USAGE
                                                        + "\n\n" + AddDeadlineCommand.MESSAGE_USAGE
                                                        + "\n\n" + DeleteTaskCommand.MESSAGE_USAGE
                                                        + "\n\n" + MarkTaskCommand.MESSAGE_USAGE
                                                        + "\n\n" + UnmarkTaskCommand.MESSAGE_USAGE
                                                        + "\n\n" + AddTagCommand.MESSAGE_USAGE
                                                        + "\n\n" + ListCommand.MESSAGE_USAGE
                                                        + "\n\n" + HelpCommand.MESSAGE_USAGE
                                                        + "\n\n" + ExitCommand.MESSAGE_USAGE;

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(ALL_COMMAND_USAGES);
        return new CommandResult(ALL_COMMAND_USAGES);
    }
}
