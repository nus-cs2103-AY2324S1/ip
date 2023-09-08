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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printMessage(taskList.toString());
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
