public class UnmarkAsDoneCommand extends Command {
    private int index;

    public UnmarkAsDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.unmarkAsDone(this.index);
        ui.showUnmarkTaskAsDone(tasks.get(this.index));
        storage.save(tasks.toFileString());
    }
}
