package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * Unmark class, is a Command that unmarks a task
 */
public class Unmark extends Command {
    private Task task;

    /**
     * Creates an Unmark command
     *
     * @param ui    Ui instance
     * @param tasks Current tasklist
     * @param index Index of the task to be unmarked
     */
    public Unmark(Ui ui, TaskList tasks, int index) {
        super(ui, tasks);
        assert index > 0 || index <= tasks.size() : "Index out of range";
        task = tasks.get(index - 1);
    }

    @Override
    public String execute() {
        task.setUndone();
        return ui.unmarkTask(task);
    }
}
