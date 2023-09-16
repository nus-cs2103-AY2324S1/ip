package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * AddTask class, is a Command that adds a task to the tasklist
 */
public class AddTask extends Command {
    private Task task;

    /**
     * Creates an AddTask command
     *
     * @param ui    Ui instance
     * @param tasks Current tasklist
     * @param task  Task to be added
     */
    public AddTask(Ui ui, TaskList tasks, Task task) {
        super(ui, tasks);
        this.task = task;
    }

    @Override
    public String execute() {
        tasks.add(task);
        return ui.newTask(task);
    }
}
