package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.TaskList;
import chatbuddy.ui.Ui;

/** Command is an abstract class that represents commands that can be executed. */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The ui used to handle interactions with the user.
     * @param storage The storage used to load and save tasks in a file.
     * @throws ChatBuddyException If the execution of the command has an error.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException;

    /**
     * Returns whether the command is an exit command.
     * Returns true if the command is an exit command, false otherwise.
     *
     * @return False by default.
     */
    public boolean isExit() {
        return false;
    }
}
