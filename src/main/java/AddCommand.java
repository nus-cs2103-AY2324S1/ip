public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(taskToAdd);
        ui.displayAddedTask(taskToAdd, tasks);
        storage.saveTasks(tasks);
    }
}
