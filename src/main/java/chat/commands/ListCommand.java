package chat.commands;

import chat.tasks.TaskList;
import chat.utils.Storage;

/**
 * Command used to perform list operations.
 * @author juzzztinsoong
 */
public class ListCommand extends Command {

    public ListCommand() {
    };

    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return tasklist.toString();
    }
}
