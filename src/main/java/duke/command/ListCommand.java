package duke.command;

import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. List all the tasks in the task list.
 */
public class ListCommand extends Command {
    private String commandMessage = "";

    public ListCommand() {
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        if (taskList.getLength() == 0) {
            commandMessage = "Wow! You have no tasks!";
        } else {
            commandMessage = "Here are your tasks: " + "\n" + taskList.toString();
        }
    }
}
