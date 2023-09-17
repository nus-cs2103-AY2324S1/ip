package bob.data.command;

import bob.data.task.TaskList;

/**
 * Lists out all the task in the list.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super();
    }
    @Override
    public String execute(TaskList list) {
        return list.toString();
    }
}
