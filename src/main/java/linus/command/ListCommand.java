package linus.command;

import linus.exception.LinusException;
import linus.task.TaskList;
import linus.util.Ui;

/**
 * Represents a command that lists tasks in the task list.
 */
public class ListCommand extends Command {
    private TaskList tasks = null;
    private Ui ui = null;

    /**
     * Constructs a command that lists tasks in the task list.
     * @param tasks
     * @param ui
     */
    public ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Lists the tasks in the task list.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        ui.printList(tasks.getList(), "Here are the tasks in your list:\n");
    }
}
