package minion.commands;

import minion.common.Messages;
import minion.data.TaskList;
import minion.storage.Storage;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the exit command.
     * @param tasks Task list.
     * @param storage Storage of chatbot.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(Messages.MESSAGE_GOODBYE, true);
    }
}
