package chatbot.alain.commands;

import chatbot.alain.AlainException;
import chatbot.alain.Assertions;
import chatbot.alain.uis.GuiUi;
import chatbot.alain.Storage;
import chatbot.alain.TaskList;
import chatbot.alain.uis.Ui;
import chatbot.alain.tasks.Task;

/**
 * Represents an abstract command that can be executed. This serves as the base class
 * for various specific command implementations in the system.
 *
 * <p>Each command is associated with a {@code TaskList} to keep track of tasks,
 * a textual representation or description of the command itself, and a {@code Storage}
 * instance for persistence concerns.</p>
 */
public class DeleteCommand extends Command {

    public DeleteCommand(TaskList list, String text, Storage storage) {
        super(list, text, storage);
    }

    @Override
    public String processCommand() throws AlainException {
        String numericPart = text.substring(7);
        int pos = Integer.parseInt(numericPart) - 1;
        if (pos >= 0 && pos < list.size()) {
            Task removedTask = list.removeTask(pos);
            Assertions.assertDelete(list, removedTask);
            Ui.showRemoveTask(removedTask, list);
            return GuiUi.showRemoveTask(removedTask, list);
        } else {
            throw new AlainException("Invalid task index.");
        }
    }
}
