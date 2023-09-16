package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * Mark class, is a Command that marks a task
 */
public class Mark extends Command {
    private Task task;

    /**
     * Creates a Mark command
     *
     * @param ui    Ui instance
     * @param tasks Current Tasklist
     * @param index Index of the task to be marked
     */
    public Mark(Ui ui, TaskList tasks, int index) {
        super(ui, tasks);
        assert index > 0 || index <= tasks.size() : "Index out of range";
        task = tasks.get(index - 1);
    }

    @Override
    public String execute() {
        task.setDone();
        return ui.markTask(task);
    }
}
