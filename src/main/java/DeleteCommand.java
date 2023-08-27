public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printDeleteSuccessMessage(index, tasks.getAllTasks());
        tasks.delete(index);
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
