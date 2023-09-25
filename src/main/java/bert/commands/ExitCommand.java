package bert.commands;

import bert.common.Messages;
import bert.storage.Storage;
import bert.tasks.TaskList;

import java.io.IOException;

/**
 * Represents a command that causes the program to exit.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            storage.save(tasks);
            return new CommandResult(Messages.MESSAGE_GOODBYE);
        } catch (IOException e) {
            return new CommandResult(String.format(Messages.MESSAGE_ERROR, e.getMessage()));
        }
    }
}
