public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.task);
        ui.showAddTask(this.task, tasks.size());
        storage.save(tasks.toFileString());
    }
}
