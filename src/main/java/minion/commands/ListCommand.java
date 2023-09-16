package minion.commands;

import minion.data.TaskList;
import minion.storage.Storage;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Executes a list command.
     * @param tasks Task list.
     * @param storage Storage of chatbot.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(tasks.toString());
    }
}
