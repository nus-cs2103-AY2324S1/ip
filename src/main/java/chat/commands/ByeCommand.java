package chat.commands;

import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Storage;

/**
 * @author juzzztinsoong
 */
public class ByeCommand extends Command {

    public ByeCommand() {
    };

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasklist, Storage storage) throws ChatException {
        return "Aw goodbye.. ಠ_ಠ";
    }
}
