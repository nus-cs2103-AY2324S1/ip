package linus.command;

import linus.exception.LinusException;
import linus.task.TaskList;
import linus.util.Ui;

/**
 * Represents a command that marks a task in the task list as done.
 */
public class MarkCommand extends Command {
    private TaskList tasks = null;
    private String data = "";
    private Ui ui = null;

    /**
     * Constructs a command that marks a task in the task list as done.
     * @param tasks
     * @param data
     * @param ui
     */
    public MarkCommand(TaskList tasks, String data, Ui ui) {
        this.tasks = tasks;
        this.data = data;
        this.ui = ui;
    }

    /**
     * Marks the task in the task list as done.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        int index = Integer.parseInt(data) - 1;
        tasks.mark(index);
        ui.printMarkSuccessMessage(tasks.get(index), tasks.getSize());
    }
}
