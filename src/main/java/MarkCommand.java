public class MarkCommand extends Command {

    private int index;
    private boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        if (this.index < 0 || this.index >= tasks.getSize()) {
            throw new JoException("This task does not exist.");
        } else {
            tasks.markTask(this.index, this.isDone);
            storage.update(tasks);
            ui.markResult(tasks.getTask(this.index), this.isDone);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
