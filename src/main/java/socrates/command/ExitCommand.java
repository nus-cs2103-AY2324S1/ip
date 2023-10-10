package socrates.command;

import socrates.data.task.TaskList;
import socrates.storage.Storage;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Exits the chatbot.";
    private static final String COMMAND_RESPONSE = "Bye. Hope to see you again soon!";

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(COMMAND_RESPONSE, true);
    }

}
