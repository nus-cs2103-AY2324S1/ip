public class AddCommand extends Command {
    String taskType;
    Task task;

    public AddCommand(String taskType, Task task) {
        super(false);
        this.taskType = taskType;
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.taskAdd(task, tasks);
        storage.saveTaskList(tasks);
    }
}
