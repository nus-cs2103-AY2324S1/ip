package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * Delete class, is a Command that deletes a task
 */
public class Delete extends Command {
    private Task task;

    /**
     * Creates a Delete command
     *
     * @param ui    Ui instance
     * @param tasks Current tasklist
     * @param index Index of the task to be deleted
     */
    public Delete(Ui ui, TaskList tasks, int index) {
        super(ui, tasks);
        assert index > 0 || index <= tasks.size() : "Index out of range";
        task = tasks.get(index - 1);
    }

    @Override
    public String execute() {
        tasks.remove(task);
        return ui.deleteTask(task, tasks.size());
    }
}
