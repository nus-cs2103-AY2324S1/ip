package minion.commands;

import minion.data.TaskList;
import minion.storage.Storage;
import minion.ui.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the exit command.
     * @param tasks Task list.
     * @param ui Ui of chatbot.
     * @param storage Storage of chatbot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.tearDown();
    }
}
