package jeo.command;

import jeo.storage.Storage;
import jeo.task.TaskList;
import jeo.ui.Ui;

/**
 * Represents the Command List that lists the tasks in the task list.
 *
 * @author Joseph Oliver Lim
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super(true);
    }

    /**
     * Executes the ListCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.taskList(tasks, tasks.getCountTasks());
    }
}
