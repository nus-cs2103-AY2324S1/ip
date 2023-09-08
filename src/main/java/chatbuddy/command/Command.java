package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.ui.Ui;

/** Command is an abstract class that represents commands that can be executed. */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The ui used to handle interactions with the user.
     * @param storage The storage used to load and save tasks in a file.
     * @return A string representing the message to display to the user.
     * @throws ChatBuddyException If the execution of the command has an error.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException;
}
