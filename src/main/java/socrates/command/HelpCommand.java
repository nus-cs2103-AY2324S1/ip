package socrates.command;

import socrates.data.task.TaskList;
import socrates.storage.Storage;

/**
 * Represents a command to list all existing commands
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Displays all commands.";

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String responseToUser = ClearCommand.COMMAND_DESCRIPTION
                + "\n" + AddDeadlineCommand.COMMAND_DESCRIPTION
                + "\n" + DeleteCommand.COMMAND_DESCRIPTION
                + "\n" + ExitCommand.COMMAND_DESCRIPTION
                + "\n" + FindCommand.COMMAND_DESCRIPTION
                + "\n" + HelpCommand.COMMAND_DESCRIPTION
                + "\n" + ListCommand.COMMAND_DESCRIPTION
                + "\n" + MarkCommand.COMMAND_DESCRIPTION
                + "\n" + AddTodoCommand.COMMAND_DESCRIPTION
                + "\n" + UnmarkCommand.COMMAND_DESCRIPTION;
        return new CommandResult(responseToUser);
    }

}
