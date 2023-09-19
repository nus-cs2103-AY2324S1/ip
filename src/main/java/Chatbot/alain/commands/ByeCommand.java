package chatbot.alain.commands;

import chatbot.alain.AlainException;
import chatbot.alain.Storage;
import chatbot.alain.TaskList;

/**
 * Represents an abstract command that can be executed. This serves as the base class
 * for various specific command implementations in the system.
 *
 * <p>Each command is associated with a {@code TaskList} to keep track of tasks,
 * a textual representation or description of the command itself, and a {@code Storage}
 * instance for persistence concerns.</p>
 */
public class ByeCommand extends Command {
    public ByeCommand(TaskList list, String text, Storage storage) {
        super(list, text, storage);
    }

    @Override
    public String processCommand() throws AlainException {
        if (storage != null) {
            storage.sayBye();
        }
        return null;
    }
}
