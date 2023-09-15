package dude.command;

import dude.Storage;
import dude.Ui;
import dude.task.TaskList;

/**
 * Command to list all tasks.
 */
public class ListTasksCommand extends DudeCommand {
    /**
     * Prints task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.toString();
    }

    /**
     * {@inheritDoc}
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
