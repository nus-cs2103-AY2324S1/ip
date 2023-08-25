public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showMessage(String.format("Got it. I've added this task:\n\t%s\n", tasks.addTask(this.task)));
        ui.showMessage(tasks.status());
        storage.save(tasks);
    }
}
