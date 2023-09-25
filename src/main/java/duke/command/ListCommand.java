package duke.command;

import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. List all the tasks in the task list.
 */
public class ListCommand extends Command {
    private String commandMessage = "";

    /**
     * Class constructor of ListCommand.
     */
    public ListCommand() {
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * {@inheritDoc}
     *
     * Lists out all tasks in current task list.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        if (taskList.getLength() == 0) {
            commandMessage = "Wow! You have no tasks!";
        } else {
            commandMessage = "Here are your tasks: " + "\n" + taskList.toString();
        }
    }
}
