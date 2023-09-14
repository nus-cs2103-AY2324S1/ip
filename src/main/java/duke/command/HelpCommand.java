package duke.command;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to list all existing commands
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Displays all commands.";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return ClearCommand.COMMAND_DESCRIPTION
                + "\n" + AddDeadlineCommand.COMMAND_DESCRIPTION
                + "\n" + DeleteCommand.COMMAND_DESCRIPTION
                + "\n" + ExitCommand.COMMAND_DESCRIPTION
                + "\n" + FindCommand.COMMAND_DESCRIPTION
                + "\n" + HelpCommand.COMMAND_DESCRIPTION
                + "\n" + ListCommand.COMMAND_DESCRIPTION
                + "\n" + MarkCommand.COMMAND_DESCRIPTION
                + "\n" + AddTodoCommand.COMMAND_DESCRIPTION
                + "\n" + UnmarkCommand.COMMAND_DESCRIPTION;
    }

}
