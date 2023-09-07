package duke.command;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    private static final String COMMAND_RESPONSE = "Bye. Hope to see you again soon!";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return COMMAND_RESPONSE;
    }
}
