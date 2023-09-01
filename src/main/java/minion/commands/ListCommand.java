package minion.commands;

import minion.data.TaskList;
import minion.storage.Storage;
import minion.ui.Ui;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Executes a list command.
     * @param tasks Task list.
     * @param ui Ui of chatbot.
     * @param storage Storage of chatbot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks.toString());
    }
}
