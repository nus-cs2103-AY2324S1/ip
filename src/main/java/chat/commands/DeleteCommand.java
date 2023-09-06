package chat.commands;

import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Storage;

/**
 * @author juzzztinsoong
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasklist, Storage storage) throws ChatException {
        String deleteString = tasklist.delete(index);
        try {
            storage.writeToFile(tasklist);
            return deleteString;
        } catch (ChatException e) {
            return e.getMessage();
        }
    }
}
