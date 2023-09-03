public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        if (this.index < 0 || this.index >= tasks.getSize()) {
            throw new JoException("This task does not exist.");
        } else {
            Task removedTask = tasks.getTask(this.index);
            tasks.deleteTask(this.index);
            storage.update(tasks);
            ui.modifyListResult(removedTask, tasks, false);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
