package sam.commands;

import sam.services.Storage;
import sam.services.TaskList;
import sam.services.UI;

/**
 * Represents an executable command.
 * Based on AddressBook2
 */
public abstract class Command {

    /**
     * Executes the command and returns the result.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    /**
     * Flag that it is the end of the conversation.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
