package duke.command;

import duke.data.task.TaskList;
import duke.storage.Storage;

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
