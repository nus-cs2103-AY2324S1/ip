package bob.data.command;

import bob.data.task.TaskList;

/**
 * Closes the GUI.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    @Override
    public String execute(TaskList list) {
        return list.close();
    }
}
