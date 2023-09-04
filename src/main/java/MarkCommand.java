public class MarkCommand extends Command {
    int index;
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.mark(this.index);
            ui.taskMark(tasks.get(this.index));
        } catch (IndexOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
        storage.saveTaskList(tasks);
    }
}
