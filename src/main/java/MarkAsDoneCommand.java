public class MarkAsDoneCommand extends Command {
    private int index;

    public MarkAsDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markAsDone(this.index);
        ui.showMarkTaskAsDone(tasks.get(this.index));
        storage.save(tasks.toFileString());
    }
}
