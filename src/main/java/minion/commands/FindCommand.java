package minion.commands;

import minion.common.Messages;
import minion.data.TaskList;
import minion.storage.Storage;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String query;

    /**
     * Constructs a find command.
     * @param query Query word.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes a find command.
     * @param tasks Task list.
     * @param storage Storage of chatbot.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        TaskList tasksFound = tasks.search(query);
        if (tasksFound.size() == 0) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_FOUND);
        }
        return new CommandResult(tasksFound.toStringMatching());
    }
}
