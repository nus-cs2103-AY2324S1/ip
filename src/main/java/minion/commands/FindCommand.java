package minion.commands;

import minion.common.Messages;
import minion.data.TaskList;
import minion.storage.Storage;
import minion.ui.Ui;

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
     * @param ui Ui of chatbot.
     * @param storage Storage of chatbot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksFound = tasks.search(query);
        if (tasksFound.size() == 0) {
            ui.print(Messages.MESSAGE_TASK_NOT_FOUND);
            return;
        }
        ui.print(tasksFound.toStringMatching());
    }
}
