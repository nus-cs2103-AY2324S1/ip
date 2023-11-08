package linus.command;

import linus.exception.LinusException;
import linus.task.TaskList;
import linus.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private TaskList tasks = null;
    private String data = "";
    private Ui ui = null;

    /**
     * Constructs a command to delete a task from the task list.
     * @param tasks
     * @param data
     * @param ui
     */
    public DeleteCommand(TaskList tasks, String data, Ui ui) {
        this.tasks = tasks;
        this.data = data;
        this.ui = ui;
    }

    /**
     * Deletes the task from the task list.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        int index = Integer.parseInt(data) - 1;
        tasks.delete(index);
        ui.printDeleteSuccessMessage(tasks.get(index), tasks.getSize());
    }

}



