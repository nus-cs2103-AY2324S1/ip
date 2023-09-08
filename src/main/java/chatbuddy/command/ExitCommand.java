package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.ui.Ui;

/** ExitCommand represents a command to exit the chatbot. */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        storage.save(tasks);
        return ui.showExit();
    }
}
