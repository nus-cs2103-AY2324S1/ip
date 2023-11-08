package linus.command;

import linus.exception.LinusException;
import linus.task.TaskList;
import linus.util.Ui;

/**
 * Represents a command that finds tasks in the task list.
 */
public class FindCommand extends Command {

    private TaskList tasks = null;
    private String data = "";
    private Ui ui = null;

    /**
     * Constructs a command that finds tasks in the task list.
     * @param tasks
     * @param data
     * @param ui
     */
    public FindCommand(TaskList tasks, String data, Ui ui) {
        this.tasks = tasks;
        this.data = data;
        this.ui = ui;
    }

    /**
     * Finds the tasks in the task list.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        ui.printFindSuccessMessage(tasks.find(data));
    }
}
