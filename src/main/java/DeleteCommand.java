public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showMessage(String.format("Noted. I've removed this task:\n\t%s\n", tasks.deleteTask(this.index)));
        ui.showMessage(tasks.status());
        storage.save(tasks);
    }
}
