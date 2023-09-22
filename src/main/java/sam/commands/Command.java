package sam.commands;

import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;

/**
 * Represents an executable command.
 * Based on AddressBook2
 */
public abstract class Command {

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Flag that it is the end of the conversation.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
