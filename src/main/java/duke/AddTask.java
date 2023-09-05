package duke;

/**
 * AddTask class with a task field which we want to add
 *
 * @author wj331
 */
public class AddTask extends Command {
    private Task task;

    /**
     * Constructor for an AddTask command
     * @param task the task which we are adding
     */
    public AddTask(Task task) {
        this.task = task;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(this.task);
            storage.saveTasks(tasks);
            return ui.addedMessage(this.task, tasks);
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }
    @Override
    public String toString() {
        return this.task.toString();
    }
}
