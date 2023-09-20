package linus.command;

import linus.exception.LinusException;
import linus.task.TaskList;
import linus.util.Ui;

/**
 * Represents a command that unmarks a task in the task list.
 */
public class UnmarkCommand extends Command {
    private TaskList tasks = null;
    private String data = "";
    private Ui ui = null;

    /**
     * Constructs a command that unmarks a task in the task list.
     * @param tasks
     * @param data
     * @param ui
     */
    public UnmarkCommand(TaskList tasks, String data, Ui ui) {
        this.tasks = tasks;
        this.data = data;
        this.ui = ui;
    }

    /**
     * Unmarks the task in the task list as done.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        int index = Integer.parseInt(data) - 1;
        tasks.unmark(index);
        ui.printUnmarkSuccessMessage(tasks.get(index), tasks.getSize());
    }
}
