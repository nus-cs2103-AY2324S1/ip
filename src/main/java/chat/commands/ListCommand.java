package chat.commands;

import chat.tasks.TaskList;
import chat.utils.Storage;

/**
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
