package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.TaskList;
import chatbuddy.ui.Ui;

/** ExitCommand represents a command to exit the chatbot. */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        storage.save(tasks);
        ui.showExit();
    }

    /**
     * Returns whether the command is an exit command.
     * Returns true if the command is an exit command, false otherwise.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
