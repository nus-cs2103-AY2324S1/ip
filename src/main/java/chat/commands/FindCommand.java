package chat.commands;

import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Storage;

/**
 * @author juzzztinsoong
 */
public class FindCommand extends Command {

    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasklist, Storage storage) throws ChatException {
        return tasklist.find(description);
    }
}
